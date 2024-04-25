package org.example.iec61850.node_parameters.DataObject;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;
import org.example.iec61850.common.modelData.*;

@Getter
@Setter
public class CMV extends MyData {
    /**
     * Complex measured value (Комплексное измеряемое значение)
     * */
    /**
     * Measured attributes
     */
    private Vector instCVal = new Vector();
    private Vector cVal = new Vector();
    private Attribute<range> rangeAttribute = new Attribute<>();

    private enum range {
        NORMAL,
        HIGH,
        LOW,
        HIGH_HIGH,
        LOW_LOW
    }

    private Attribute<rangeAng> rangeAngAttribute = new Attribute<>();

    private enum rangeAng {
        NORMAL,
        HIGH,
        LOW,
        HIGH_HIGH,
        LOW_LOW
    }

    private Quality q = new Quality();
    private TimeStamp t = new TimeStamp();
    /**
     * Substitution and blocked
     */
    private Attribute<Boolean> subEna = new Attribute<>();
    private Attribute<Vector> subVal = new Attribute<>();
    private Quality subQ = new Quality();
    private Attribute<String> subID = new Attribute<>();
    private Attribute<Boolean> blkEna = new Attribute<>();
    /**
     * Configuration, description and extension
     * ...*/
}
