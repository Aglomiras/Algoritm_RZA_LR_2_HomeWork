package org.example.iec61850.lodicalNodes.system_logic_nodes;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.MV;
import org.pcap4j.core.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
@Slf4j
public class LSVS_SV extends LN {
    /**
     * Значения для записи и вывода на графике
     */
    public MV phsA_I = new MV();
    public MV phsB_I = new MV();
    public MV phsC_I = new MV();
    public MV phsA_U = new MV();
    public MV phsB_U = new MV();
    public MV phsC_U = new MV();

    /**
     * Имя сетевой карты к которой будет подсасываться для кражи пакетов
     */
    private String nicName;
    /**
     * Обработчик, который вешается на сетевую карту и позволяет перехватывать пакеты
     */
    private PcapHandle handle;

    private int datasetSize; //64
    private int selector = 0;

    private final List<EthernetListener> packetListeners = new CopyOnWriteArrayList<>(); //Через copy делается, чтобы избежать ошибок в многопоточной работе
    private final PacketListener defaultPacketListener = packet -> { //Метод для принятия сырых пакетов, обернутый в лямбда функцию.
        if (selector == 0) {
            decodeSVpack(packet);
            packetListeners.forEach(EthernetListener::listen);
        }
        selector++;
        if (selector == 2) {
            selector = 0;
        }
    };

    private final List<PacketListener> listeners = new CopyOnWriteArrayList<>(); //Делаем через copy, чтоб избежать ошибок при многопточной работе.

//    private final PacketListener defaultPacketListener = pcapPacket -> {
//        listeners.forEach(listeners -> listeners.gotPacket(pcapPacket));
////        System.out.println(pcapPacket);
//    };

    @Override
    @SneakyThrows
    public void process() {
        if (handle == null) {
            initalizeNetworkInterface();

            if (handle != null) {
                //Данным выражением мы говорим, что хотим получать только SV пакеты и только те, чей мак адрес соответствует 01:0C:CD:04:00:01
                String filter = "ether proto 0x88ba && ether dst 01:0C:CD:04:00:01";
                handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);

                Thread captureThread = new Thread(() -> { //Создание функционального интерфейса (функциональный интерфейс - интерфейс, у которого есть один метод). Такие интерфейсы можно записать в лямбда выражении
                    try {
                        log.info("Starting packet capture");
                        handle.loop(0, defaultPacketListener); //Принятие сырых пакетов (необработанных)
                    } catch (PcapNativeException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (NotOpenException e) {
                        throw new RuntimeException(e);
                    }
                    log.info("Packet capture finished");
                });
                captureThread.start();
            }
        }
    }

    //Создадим метод для поиска определенной сетевой карты
    @SneakyThrows
    private void initalizeNetworkInterface() {
        Optional<PcapNetworkInterface> nic = Pcaps.findAllDevs().stream()
                .filter(i -> nicName.equals(i.getDescription()))
                .findFirst();
        if (nic.isPresent()) { //Если сетевай карта найдена, то создадим область для ее работы
            //Создаем максимальное число памяти для пакета. Вторая переменная позволяет пропускать пакеты в операционную память, которые ей не предназначаются
            handle = nic.get().openLive(1500, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);
            log.info("Network handle created: {}", nic);
        } else {
            log.error("Network interface not found...");
        }
    }

    public void addListener(EthernetListener listener) {
        packetListeners.add(listener);
    }
//    public void addListener(PacketListener listener) {
//        listeners.add(listener);
//    }

    public void decodeSVpack(PcapPacket packet) {
        try {
            byte[] data = packet.getRawData(); //Принятие массива байт

            phsA_I.getInstMag().getFloatVal().setValue(
                    (byteArrayToInt(data, data.length - datasetSize) / 1000.0));

            phsB_I.getInstMag().getFloatVal().setValue(
                    (byteArrayToInt(data, data.length - datasetSize + 8) / 1000.0));

            phsC_I.getInstMag().getFloatVal().setValue(
                    (byteArrayToInt(data, data.length - datasetSize + 16) / 1000.0));

            phsA_U.getInstMag().getFloatVal().setValue(
                    (byteArrayToInt(data, data.length - datasetSize + 32) / 1000.0));

            phsB_U.getInstMag().getFloatVal().setValue(
                    (byteArrayToInt(data, data.length - datasetSize + 40) / 1000.0));

            phsC_U.getInstMag().getFloatVal().setValue(
                    (byteArrayToInt(data, data.length - datasetSize + 48) / 1000.0));

        } catch (Exception e) {
            log.error("Cannot parse sv packet");
        }
    }

    //Перевод в человекочитаемый вид. Побитовый сдвиг.
    public static int byteArrayToInt(byte[] b, int offset) {
        return b[offset + 3] & 0xFF | (b[offset + 2] & 0xFF) << 8 | (b[offset + 1] & 0xFF) << 16 | (b[offset] & 0xFF) << 24;
    }
}

