package org.example;

import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.lodicalNodes.hmi.NHMI;
import org.example.iec61850.lodicalNodes.hmi.other.NHMISignal;
import org.example.iec61850.lodicalNodes.measurement.MMXU;
import org.example.iec61850.lodicalNodes.protection.PTOC;
import org.example.iec61850.lodicalNodes.supervisory_control.CSWI;
import org.example.iec61850.lodicalNodes.switchgear.XCBR;
import org.example.iec61850.lodicalNodes.system_logic_nodes.LSVS;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<LN> logicalNode = new ArrayList<>();
        private static String path = "C:\\Users\\Aglomiras\\Изображения\\Рабочий стол\\AlgoritmRZAProgrammRealize\\Начало линии\\";
//    private static String path = "C:\\Users\\Aglomiras\\Изображения\\Рабочий стол\\AlgoritmRZAProgrammRealize\\Конец линии\\";

    /**
     * Начало линии
     */
//    private static String name = "PhAB80";
    private static String name = "PhA80";

    /**
     * Конец линии
     */
//    private static String name = "PhABC80";
//    private static String name = "PhB80";

    public static void main(String[] args) throws Exception {
        LSVS lsvs = new LSVS(); //Создаем узел LSVS
        lsvs.setPath(path);
        lsvs.setFileName(name);
        logicalNode.add(lsvs); //Добавляем узел в лист узлов

        MMXU mmxu = new MMXU(); //Создаем узел MMXU
        mmxu.IaInst = lsvs.getOut().get(0);
        mmxu.IbInst = lsvs.getOut().get(1);
        mmxu.IcInst = lsvs.getOut().get(2);
        logicalNode.add(mmxu); //Добавляем узел в лист узлов

        /**I ступень: инициализация*/
        PTOC ptoc1 = new PTOC();
        ptoc1.setA(mmxu.getA());
        ptoc1.getStrVal().getSetMag().getFloatVal().setValue(2544.0); //Задание уставки по току
        ptoc1.getOpDlOpTmms().getSetVal().setValue(0); //Задание выдержки времени
        ptoc1.getTmMult().getSetMag().getFloatVal().setValue(20.0 / 80);
        logicalNode.add(ptoc1);

        /**II ступень: инициализация*/
        PTOC ptoc2 = new PTOC();
        ptoc2.setA(mmxu.getA());
        ptoc2.getStrVal().getSetMag().getFloatVal().setValue(833.3); //Задание уставки по току
        ptoc2.getOpDlOpTmms().getSetVal().setValue(120); //Задание выдержки времени
        ptoc2.getTmMult().getSetMag().getFloatVal().setValue(20.0 / 80);
        logicalNode.add(ptoc2);

        /**III ступень: инициализация*/
        PTOC ptoc3 = new PTOC();
        ptoc3.setA(mmxu.getA());
        ptoc3.getStrVal().getSetMag().getFloatVal().setValue(420.0); //Задание уставки по току
        ptoc3.getOpDlOpTmms().getSetVal().setValue(240); //Задание выдержки времени
        ptoc3.getTmMult().getSetMag().getFloatVal().setValue(20.0 / 80);
        logicalNode.add(ptoc3);

        /**Узел контроля сигналов на отключение*/
        CSWI cswi = new CSWI();
        /**Добавляем информацию о сигнала на отключение оборудования от защит*/
        cswi.getOpOpnList().add(ptoc1.getOp());
        cswi.getOpOpnList().add(ptoc2.getOp());
        cswi.getOpOpnList().add(ptoc3.getOp());
        logicalNode.add(cswi);

        XCBR xcbr = new XCBR();
        xcbr.setPos(cswi.getPos());
        logicalNode.add(xcbr);

        /**Вывод самих сигналов всех фаз*/
        NHMI nhmiMMXU = new NHMI();
        nhmiMMXU.addSignals("SignalIA", new NHMISignal("ia", mmxu.IaInst.getInstMag().getFloatVal()));
        nhmiMMXU.addSignals("SignalIB", new NHMISignal("ib", mmxu.IbInst.getInstMag().getFloatVal()));
        nhmiMMXU.addSignals("SignalIC", new NHMISignal("ic", mmxu.IcInst.getInstMag().getFloatVal()));
        logicalNode.add(nhmiMMXU);

        /**Вывод действующих значений фаз и уставок защит*/
        NHMI nhmiPTOC = new NHMI();
        nhmiPTOC.addSignals("The action of the steps: PhsA",
                new NHMISignal("Phase_A", mmxu.getA().getPhsA().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("protected_1", ptoc1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("protected_2", ptoc2.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("protected_3", ptoc3.getStrVal().getSetMag().getFloatVal()));
        nhmiPTOC.addSignals("The action of the steps: PhsB",
                new NHMISignal("Phase_B", mmxu.getA().getPhsB().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("protected_1", ptoc1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("protected_2", ptoc2.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("protected_3", ptoc3.getStrVal().getSetMag().getFloatVal()));
        nhmiPTOC.addSignals("The action of the steps: PhsC",
                new NHMISignal("Phase_C", mmxu.getA().getPhsC().getInstCVal().getMag().getFloatVal()),
                new NHMISignal("protected_1", ptoc1.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("protected_2", ptoc2.getStrVal().getSetMag().getFloatVal()),
                new NHMISignal("protected_3", ptoc3.getStrVal().getSetMag().getFloatVal()));
        logicalNode.add(nhmiPTOC);

        /**Дискретные сигналы действия всех ступеней защиты для каждой фазы*/
        /**Phs_A*/
        NHMI nhmiDS_A = new NHMI();
        nhmiDS_A.addSignals("Discrete signal PhsA: I",
                new NHMISignal("DS_protected_1", ptoc1.getOp().getPhsA()));
        nhmiDS_A.addSignals("Discrete signal PhsA: II",
                new NHMISignal("DS_protected_2", ptoc2.getOp().getPhsA()));
        nhmiDS_A.addSignals("Discrete signal PhsA: III",
                new NHMISignal("DS_protected_3", ptoc3.getOp().getPhsA()));
        logicalNode.add(nhmiDS_A);

        /**Phs_B*/
        NHMI nhmiDS_B = new NHMI();
        nhmiDS_B.addSignals("Discrete signal PhsB: I",
                new NHMISignal("DS_protected_1", ptoc1.getOp().getPhsB()));
        nhmiDS_B.addSignals("Discrete signal PhsB: II",
                new NHMISignal("DS_protected_2", ptoc2.getOp().getPhsB()));
        nhmiDS_B.addSignals("Discrete signal PhsB: III",
                new NHMISignal("DS_protected_3", ptoc3.getOp().getPhsB()));
        logicalNode.add(nhmiDS_B);

        /**Phs_C*/
        NHMI nhmiDS_C = new NHMI();
        nhmiDS_C.addSignals("Discrete signal PhsC: I",
                new NHMISignal("DS_protected_1", ptoc1.getOp().getPhsC()));
        nhmiDS_C.addSignals("Discrete signal PhsC: II",
                new NHMISignal("DS_protected_2", ptoc2.getOp().getPhsC()));
        nhmiDS_C.addSignals("Discrete signal PhsC: III",
                new NHMISignal("DS_protected_3", ptoc3.getOp().getPhsC()));
        logicalNode.add(nhmiDS_C);

        while (lsvs.hasNext()) {
            logicalNode.forEach(LN::process);
            System.out.println();
        }
    }
}