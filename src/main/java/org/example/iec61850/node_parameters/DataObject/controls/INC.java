package org.example.iec61850.node_parameters.DataObject.controls;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Originator;
import org.example.iec61850.common.modelData.Quality;
import org.example.iec61850.common.modelData.TimeStamp;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class INC extends MyData {
    /**
     * Controllable integer status (Управляемый целочисленный статус)
     * */
    /**
     * Status and control mirror
     */
    private Originator origin = new Originator();
    private Attribute<Integer> ctlNum = new Attribute<>();
    private Attribute<Integer> stVal = new Attribute<>();
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
    private Attribute<Integer> subVal = new Attribute<>();
    private Quality subQ = new Quality();
    private Attribute<String> subID = new Attribute<>();
    private Attribute<Boolean> blkEna = new Attribute<>();
    /**
     * Configuration, description and extension
     * ...*/
}
