package org.example.iec61850.lodicalNodes;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.node_parameters.DataObject.controls.ENC;
import org.example.iec61850.node_parameters.DataObject.controls.SPC;
import org.example.iec61850.node_parameters.DataObject.descriptions.LPL;
import org.example.iec61850.node_parameters.DataObject.settings.ENG;
import org.example.iec61850.node_parameters.DataObject.settings.ING;
import org.example.iec61850.node_parameters.DataObject.settings.ORG;
import org.example.iec61850.node_parameters.DataObject.status_information.ENS;
import org.example.iec61850.node_parameters.DataObject.status_information.SPS;

@Getter
@Setter
public abstract class LN {
    /**
     * LN: common logical node   Name: Common LN
     *
     * Mandatory and conditional logical node information (shall be inherited by ALL LN but LPHD) */
    /**
     * Descriptions
     */
    private LPL NamePlt = new LPL();
    /**
     * Status information
     */
    private ENS Beh = new ENS();
    private ENS Health = new ENS();
    private SPS Blk = new SPS();
    /**
     * Controls
     */
    private ENC Mod = new ENC();
    private SPC CmdBlk = new SPC();
    /**
     * Settings
     */
    private ORG InRef1 = new ORG();
    private ORG BlkRef1 = new ORG();
    /**
     * Logical node information (statistical calculation specific – refer to Annex F)
     * */
    /**
     * Status information
     */
    private SPS ClcExp = new SPS();
    /**
     * Controls
     */
    private SPC ClcStr = new SPC();
    /**
     * Settings
     */
    private ENG ClcMth = new ENG();
    private ENG ClcMod = new ENG();
    private ENG ClcIntvTyp = new ENG();
    private ING ClcIntvPer = new ING();
    private ING NumSubIntv = new ING();
    private ENG ClcRfTyp = new ENG();
    private ING ClcRfPer = new ING();
    private ORG ClcSrc = new ORG();
    private ING ClcNxTmms = new ING();
    private ORG InSyn = new ORG();
    public abstract void process();
}
