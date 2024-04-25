package org.example.iec61850.node_parameters.DataObject.controls;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Originator;
import org.example.iec61850.common.modelData.Quality;
import org.example.iec61850.common.modelData.TimeStamp;

@Getter
@Setter
public class DPC extends MyData {
    /**
     * Controllable double point (Управляемая двойная точка)
     */
    /**
     * Status and control mirror
     */
    private Originator origin = new Originator();
    private Attribute<stVal> stValAttribute = new Attribute<>();

    public enum stVal {
        INTERMEDIATE_STATE,
        OFF,
        ON,
        BAD_STATE
    }

    private Quality q = new Quality();
    private TimeStamp t = new TimeStamp();
    private Attribute<Boolean> stSeld = new Attribute<>();
    private Attribute<Boolean> opRcvd = new Attribute<>();
    private Attribute<Boolean> opOk = new Attribute<>();
    private TimeStamp tOpOk = new TimeStamp();

    /**
     * Substitution and blocked
     */
    private Attribute<Boolean> subEna = new Attribute<>();
    private Attribute<subVal> subValAttribute = new Attribute<>();

    public enum subVal {
        INTERMEDIATE_STATE,
        OFF,
        ON,
        BAD_STATE
    }

    private Quality subQ = new Quality();
    private Attribute<String> subID = new Attribute<>();
    private Attribute<Boolean> blkEna = new Attribute<>();

    /**
     * Configuration, description and extension
     * ...*/
}
