package org.example;

import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.lodicalNodes.function_protection.RDIR;
import org.example.iec61850.lodicalNodes.hmi.NHMI;
import org.example.iec61850.lodicalNodes.hmi.other.NHMISignal;
import org.example.iec61850.lodicalNodes.measurement.MMXU;
import org.example.iec61850.lodicalNodes.measurement.MSQI;
import org.example.iec61850.lodicalNodes.protection.PTOC;
import org.example.iec61850.lodicalNodes.supervisory_control.CSWI;
import org.example.iec61850.lodicalNodes.switchgear.XCBR;
import org.example.iec61850.lodicalNodes.system_logic_nodes.EthernetListener;
import org.example.iec61850.lodicalNodes.system_logic_nodes.LSVC;
import org.example.iec61850.lodicalNodes.system_logic_nodes.LSVS_SV;
import org.example.iec61850.node_parameters.DataObject.status_information.ACD;
import org.pcap4j.core.PacketListener;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<LN> logicalNode = new ArrayList<>();
    private static String path = "C:\\Users\\Aglomiras\\Изображения\\Рабочий стол\\AlgoritmRZAProgrammRealize\\Лабораторная №2\\Опыты\\";
    private static String name = "KZ4"; //1,2,3,4,5,6,7

    public static void main(String[] args) throws Exception {
        LSVS_SV lsvc = new LSVS_SV();
        lsvc.setDatasetSize(64); //Размер данных (как в прошлом году)
        lsvc.setNicName("VirtualBox Host-Only Ethernet Adapter"); //Сетевая карта
        logicalNode.add(lsvc);

        /**Создаем узел LSVC и добавляем узел в лист узлов*/
//        LSVC lsvc = new LSVC();
//        lsvc.setPath(path);
//        lsvc.setFileName(name);
//        logicalNode.add(lsvc);

        /**Создаем узел MMXU и добавляем узел в лист узлов*/
        MMXU mmxu = new MMXU();
//        mmxu.UaInst = lsvc.getOut().get(0);
//        mmxu.UbInst = lsvc.getOut().get(1);
//        mmxu.UcInst = lsvc.getOut().get(2);
//        mmxu.IaInst = lsvc.getOut().get(3);
//        mmxu.IbInst = lsvc.getOut().get(4);
//        mmxu.IcInst = lsvc.getOut().get(5);

        mmxu.UaInst = lsvc.getPhsA_U();
        mmxu.UbInst = lsvc.getPhsB_U();
        mmxu.UcInst = lsvc.getPhsC_U();
        mmxu.IaInst = lsvc.getPhsA_I();
        mmxu.IbInst = lsvc.getPhsB_I();
        mmxu.IcInst = lsvc.getPhsC_I();
        logicalNode.add(mmxu);

        /**MSQI*/
        MSQI msqi = new MSQI();
        msqi.I = mmxu.getA();
        msqi.U = mmxu.getPNV();
        logicalNode.add(msqi);

        /**RDIR*/
        RDIR rdir = new RDIR();
        rdir.SeqA = msqi.getSeqA();
        rdir.SeqV = msqi.getSeqV();
        logicalNode.add(rdir);

        /**Ступени защиты ТЗНП и ТНЗНП*/
        /**Ненаправленные ступени*/
        //I ступень
        PTOC ptocND1 = new PTOC();
        ptocND1.getStrVal().getSetMag().getFloatVal().setValue(960.0); //Уставка по току
        ptocND1.getOpDlOpTmms().getSetVal().setValue(50); //Уставка по времени
        ptocND1.getTmMult().getSetMag().getFloatVal().setValue(20.0 / 80); //Модификатор времени
        ptocND1.getDirMod().getSetVal().setValue(
                rdir.getDir().getDirGeneralAttribute().getValue()
        );
        ptocND1.SeqA = rdir.SeqA; //Передаем значение тока
        logicalNode.add(ptocND1);

        //II ступень
        PTOC ptocND2 = new PTOC();
        ptocND2.getStrVal().getSetMag().getFloatVal().setValue(738.5); //Уставка по току
        ptocND2.getOpDlOpTmms().getSetVal().setValue(5500); //Уставка по времени
        ptocND2.getTmMult().getSetMag().getFloatVal().setValue(20.0 / 80); //Модификатор времени
        ptocND2.getDirMod().getSetVal().setValue(
                rdir.getDir().getDirGeneralAttribute().getValue()
        );
        ptocND2.SeqA = rdir.SeqA; //Передаем значение тока
        logicalNode.add(ptocND2);

        /**Направленные ступени*/
        //I ступень
        PTOC ptocD1 = new PTOC();
        ptocD1.getStrVal().getSetMag().getFloatVal().setValue(960.0); //Уставка по току
        ptocD1.getOpDlOpTmms().getSetVal().setValue(0); //Уставка по времени
        ptocD1.getTmMult().getSetMag().getFloatVal().setValue(20.0 / 80); //Модификатор времени
        ptocD1.getDirMod().getSetVal().setValue(
                rdir.getDir().getDirGeneralAttribute().getValue()
        );
        ptocD1.SeqA = rdir.SeqA; //Передаем значение тока
        logicalNode.add(ptocD1);

        //II ступень
        PTOC ptocD2 = new PTOC();
        ptocD2.getStrVal().getSetMag().getFloatVal().setValue(738.5); //Уставка по току
        ptocD2.getOpDlOpTmms().getSetVal().setValue(500); //Уставка по времени
        ptocD2.getTmMult().getSetMag().getFloatVal().setValue(20.0 / 80); //Модификатор времени
        ptocD2.getDirMod().getSetVal().setValue(
                rdir.getDir().getDirGeneralAttribute().getValue()
        );
        ptocD2.SeqA = rdir.SeqA; //Передаем значение тока
        logicalNode.add(ptocD2);


        /**Узел контроля сигналов на отключение*/
        CSWI cswi = new CSWI();
        /**Добавляем информацию о сигнала на отключение оборудования от защит*/
        cswi.getOpOpnList().add(ptocND1.getOp());
        cswi.getOpOpnList().add(ptocND2.getOp());
        cswi.getOpOpnList().add(ptocD1.getOp());
        cswi.getOpOpnList().add(ptocD2.getOp());
        logicalNode.add(cswi);

        XCBR xcbr = new XCBR();
        xcbr.setPos(cswi.getPos());
        logicalNode.add(xcbr);


        /**Вывод самих сигналов всех фаз*/
        NHMI nhmiMMXU = new NHMI();
        nhmiMMXU.addSignals("SignalIA", new NHMISignal("ia", mmxu.IaInst.getInstMag().getFloatVal()));
        nhmiMMXU.addSignals("SignalIB", new NHMISignal("ib", mmxu.IbInst.getInstMag().getFloatVal()));
        nhmiMMXU.addSignals("SignalIC", new NHMISignal("ic", mmxu.IcInst.getInstMag().getFloatVal()));

        nhmiMMXU.addSignals("SignalUA", new NHMISignal("ua", mmxu.UaInst.getInstMag().getFloatVal()));
        nhmiMMXU.addSignals("SignalUB", new NHMISignal("ub", mmxu.UbInst.getInstMag().getFloatVal()));
        nhmiMMXU.addSignals("SignalUC", new NHMISignal("uc", mmxu.UcInst.getInstMag().getFloatVal()));
        logicalNode.add(nhmiMMXU);


        /**Вывод действующих значений фаз, угол и уставки защит*/
        NHMI nhmiPTOC = new NHMI();
        nhmiPTOC.addSignals("Действующее, угол и уставки I_A",
                new NHMISignal("RMS_IA", mmxu.getA().getPhsA().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("Angular_IA", mmxu.getA().getPhsA().getInstCVal().getAng().getFloatVal()),
                new NHMISignal("Dir_1", ptocND1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Dir_2", ptocND2.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Not_Dir_1", ptocD1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Not_Dir_2", ptocD2.getStrVal().getSetMag().getFloatVal())
        );
        nhmiPTOC.addSignals("Действующее, угол и уставки I_B",
                new NHMISignal("Phase_IB", mmxu.getA().getPhsB().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("Angular_IB", mmxu.getA().getPhsB().getInstCVal().getAng().getFloatVal()),
                new NHMISignal("Dir_1", ptocND1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Dir_2", ptocND2.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Not_Dir_1", ptocD1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Not_Dir_2", ptocD2.getStrVal().getSetMag().getFloatVal())
        );
        nhmiPTOC.addSignals("Действующее, угол и уставки I_C",
                new NHMISignal("Phase_IC", mmxu.getA().getPhsC().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("Angular_IC", mmxu.getA().getPhsC().getInstCVal().getAng().getFloatVal()),
                new NHMISignal("Dir_1", ptocND1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Dir_2", ptocND2.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Not_Dir_1", ptocD1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("Not_Dir_2", ptocD2.getStrVal().getSetMag().getFloatVal())
        );
        nhmiPTOC.addSignals("Действующее, угол и уставки U_A",
                new NHMISignal("RMS_UA", mmxu.getPNV().getPhsA().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("Angular_UA", mmxu.getPNV().getPhsA().getInstCVal().getAng().getFloatVal())
        );
        nhmiPTOC.addSignals("Действующее, угол и уставки U_B",
                new NHMISignal("RMS_UB", mmxu.getPNV().getPhsA().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("Angular_UB", mmxu.getPNV().getPhsA().getInstCVal().getAng().getFloatVal())
        );
        nhmiPTOC.addSignals("Действующее, угол и уставки U_C",
                new NHMISignal("RMS_UC", mmxu.getPNV().getPhsA().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("Angular_UC", mmxu.getPNV().getPhsA().getInstCVal().getAng().getFloatVal())
        );
        logicalNode.add(nhmiPTOC);

        /**Последовательности*/
        NHMI nhmiSeq = new NHMI();
        nhmiSeq.addSignals("Ток ПП",
                new NHMISignal("C1_I", msqi.getSeqA().getC1().getInstCVal().getMag().getFloatVal())
        );
        nhmiSeq.addSignals("Ток ОП",
                new NHMISignal("C2_I", msqi.getSeqA().getC2().getInstCVal().getMag().getFloatVal())
        );
        nhmiSeq.addSignals("Ток НП",
                new NHMISignal("C3_I", msqi.getSeqA().getC3().getInstCVal().getMag().getFloatVal())
        );
        nhmiSeq.addSignals("Напряжение ПП",
                new NHMISignal("C1_U", msqi.getSeqV().getC1().getInstCVal().getMag().getFloatVal())
        );
        nhmiSeq.addSignals("Напряжение ОП",
                new NHMISignal("C2_U", msqi.getSeqV().getC2().getInstCVal().getMag().getFloatVal())
        );
        nhmiSeq.addSignals("Напряжение НП",
                new NHMISignal("C3_U", msqi.getSeqV().getC3().getInstCVal().getMag().getFloatVal())
        );
        logicalNode.add(nhmiSeq);


        NHMI nhmiDiscret = new NHMI();
        nhmiDiscret.addSignals(
                "I ступень ТЗНП",
                new NHMISignal("OpDir1", ptocND1.getOp().getGeneral()),
                new NHMISignal("StrDir1", ptocND1.getStr().getGeneral())
        );
        nhmiDiscret.addSignals(
                "II ступень ТЗНП",
                new NHMISignal("OpDir2", ptocND2.getOp().getGeneral()),
                new NHMISignal("StrDir2", ptocND2.getStr().getGeneral())
        );
        nhmiDiscret.addSignals(
                "I ступень ТНЗНП",
                new NHMISignal("Op1", ptocD1.getOp().getGeneral()),
                new NHMISignal("Str1", ptocD1.getStr().getGeneral())
        );
        nhmiDiscret.addSignals(
                "II ступень ТНЗНП",
                new NHMISignal("Op2", ptocD2.getOp().getGeneral()),
                new NHMISignal("Str2", ptocD2.getStr().getGeneral())
        );
        logicalNode.add(nhmiDiscret);

//        /**Направление мощности*/
//        NHMI rdirNhmi = new NHMI();
//        rdirNhmi.addSignals(
//                "rdir1", new NHMISignal("21", rdir.S)
//        );
//        logicalNode.add(rdirNhmi);


//        int i = 0; //Счетчик операций
//        while (lsvc.hasNext()) {
//            logicalNode.forEach(LN::process);
//            System.out.println(i);
//            i++;
//        }

        lsvc.addListener(new EthernetListener() {
            @Override
            public void listen() {
                logicalNode.forEach(el -> el.process());
            }
        });
        lsvc.process();

    }
}
