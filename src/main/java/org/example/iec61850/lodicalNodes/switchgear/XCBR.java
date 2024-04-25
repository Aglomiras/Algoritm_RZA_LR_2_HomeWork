package org.example.iec61850.lodicalNodes.switchgear;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.lodicalNodes.LN;
import org.example.iec61850.node_parameters.DataObject.controls.DPC;
import org.example.iec61850.node_parameters.DataObject.controls.INC;
import org.example.iec61850.node_parameters.DataObject.controls.SPC;
import org.example.iec61850.node_parameters.DataObject.descriptions.DPL;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.BCR;
import org.example.iec61850.node_parameters.DataObject.settings.ING;
import org.example.iec61850.node_parameters.DataObject.status_information.ENS;
import org.example.iec61850.node_parameters.DataObject.status_information.INS;
import org.example.iec61850.node_parameters.DataObject.status_information.SPS;

@Getter
@Setter
public class XCBR extends LN {
    /**
     * LN: Circuit breaker Name: XCBR (LN: Название автоматического выключателя: XCBR)
     *
     * Логические узлы этой группы предоставляют данные, необходимые для представления соответствующего
     * оборудования распределительного устройства в системе автоматизации. Существует
     * только два логических узла (XCBR, XSWI), поскольку все отключающие устройства
     * без тока моделируются XSWI. Каждый логический узел имеет сопутствующие
     * логические узлы в группах (например, SCBR, SSWI), предоставляющие
     * подробную информацию о контроле, если это необходимо.
     * */
    /**
     * Descriptions
     */
    private DPL EEName = new DPL();
    /**
     * Status information
     */
    private ENS EEHealth = new ENS();
    private SPS LocKey = new SPS();
    private SPS Loc = new SPS();
    private INC OpCnt = new INC();
    private ENS CBOpCap = new ENS();
    private ENS POWCap = new ENS();
    private INS MaxOpCAp = new INS();
    private SPS Dsc = new SPS();
    /**
     * Measured and metered values
     */
    private BCR SumSwARs = new BCR();
    /**
     * Controls
     * */
    private SPC LocSta = new SPC();
    private DPC Pos = new DPC(); //Положение выключателя
    /**Блокировки*/
    private SPC BlkOpn = new SPC(); //Открытие блока (блокировка на отключение)
    private SPC BlkCls = new SPC(); //Закрытие блока (блокировка на включение)
    private SPC ChaMotEna = new SPC();
    /**
     * Settings
     */
    private ING CBTmms = new ING();

    public XCBR() {
        /**Начальные параметры блокировок*/
        BlkOpn.getStVal().setValue(false);
        BlkCls.getStVal().setValue(true);
    }

    @Override
    public void process() {
        if (!Pos.getStValAttribute().getValue().equals(DPC.stVal.OFF) && !BlkOpn.getStVal().getValue()) {
            Pos.getStValAttribute().setValue(DPC.stVal.OFF);
            BlkCls.getStVal().setValue(true);
        } else {
            Pos.getStValAttribute().setValue(DPC.stVal.ON);
            BlkCls.getStVal().setValue(false);
        }
    }
}
