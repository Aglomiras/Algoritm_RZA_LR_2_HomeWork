package org.example.iec61850.lodicalNodes.supervisory_control;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.node_parameters.DataObject.controls.DPC;
import org.example.iec61850.node_parameters.DataObject.controls.INC;
import org.example.iec61850.node_parameters.DataObject.controls.SPC;
import org.example.iec61850.node_parameters.DataObject.status_information.ACT;
import org.example.iec61850.node_parameters.DataObject.status_information.SPS;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CSWI extends LN {
    /**
     * LN: Switch controller Name: CSWI (LN: Название контроллера коммутатора: CSWI)
     *
     * Коммутатор управления LN обрабатывает все операции CSWI распределительного устройства
     * от операторов и связанной автоматики. Он проверяет авторизацию команд. Он контролирует выполнение команд
     * и подает сигнал тревоги в случае неправильного завершения команды. Он запрашивает освобождение от
     * блокировки, синхронизации, автоматического закрытия и т.д. если применимо.
     * */
    /**
     * Status information
     */
    private SPS LocKey = new SPS();
    private SPS Loc = new SPS();
    private ACT OpOpn = new ACT(); //Операция “Разомкнутый выключатель”
    private SPS SelOpn = new SPS(); //Выбор “Разомкнутый выключатель”
    private ACT OpCls = new ACT();
    private SPS SelCLs = new SPS(); //Выбор "Замкнутый выключатель"
    /**
     * Controls
     */
    private INC OpCntRs = new INC();
    private SPC LocSta = new SPC();
    /**Сигналы отключения фаз выключателя*/
    private DPC Pos = new DPC();
    private DPC PosA = new DPC();
    private DPC PosB = new DPC();
    private DPC PosC = new DPC();

    /**Лист хранящий значения срабатывания защит (сигналов на отключения), для каждой из ступеней*/
    private List<ACT> OpOpnList = new ArrayList<>();

    /**Конструктор для задания начальных значений*/
    public CSWI() {
        SelOpn.getStVal().setValue(false);
        SelCLs.getStVal().setValue(true);
    }

    @Override
    public void process() {
        /**Проверяем каждую из ступеней ТО на наличие отключающего сигнала*/
        for (int i = 0; i < OpOpnList.size(); i++) {
            if (OpOpnList.get(i).getGeneral().getValue()) {
                SelOpn.getStVal().setValue(true);
                SelCLs.getStVal().setValue(false);
                break;
            }
        }

        /**При получении сигнала на отключение, отключает все выключатели фаз*/
        if (SelOpn.getStVal().getValue()) {
            Pos.getStValAttribute().setValue(DPC.stVal.OFF);
            PosA.getStValAttribute().setValue(DPC.stVal.OFF);
            PosB.getStValAttribute().setValue(DPC.stVal.OFF);
            PosC.getStValAttribute().setValue(DPC.stVal.OFF);
        } else {
            Pos.getStValAttribute().setValue(DPC.stVal.ON);
            PosA.getStValAttribute().setValue(DPC.stVal.ON);
            PosB.getStValAttribute().setValue(DPC.stVal.ON);
            PosC.getStValAttribute().setValue(DPC.stVal.ON);
        }
    }
}
