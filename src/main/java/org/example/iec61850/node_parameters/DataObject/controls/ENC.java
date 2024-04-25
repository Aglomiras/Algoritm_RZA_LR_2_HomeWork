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
public class ENC extends MyData {
    /**
     * Controllable enumerated status (Управляемый перечисляемый статус)
     * */
    /**
     * Status and control mirror
     */
    private Originator origin = new Originator();
    private Attribute<Integer> ctlNum = new Attribute<>();
    private Attribute<stVal> stValAttribute = new Attribute<>();

    private enum stVal {

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

    private enum subVal {

    }

    private Quality subQ = new Quality();
    private Attribute<String> subID = new Attribute<>();
    private Attribute<Boolean> blkEna = new Attribute<>();
    /**
     * Configuration, description and extension
     * ...*/
}
