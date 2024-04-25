package org.example.iec61850.node_parameters.DataObject.measured_and_metered_values;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.AnalogueValue;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Quality;
import org.example.iec61850.common.modelData.TimeStamp;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class MV extends MyData {
    /**
     * Measured value (Измеренное значение)
     * */
    /**
     * Measured attributes
     * */
    private AnalogueValue instMag = new AnalogueValue();
    private AnalogueValue mag = new AnalogueValue();
    private Attribute<range> rangeAttribute = new Attribute<>();

    private enum range {
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
    private Attribute<AnalogueValue> subVal = new Attribute<>();
    private Quality subQ = new Quality();
    private Attribute<String> subID = new Attribute<>();
    private Attribute<Boolean> blkEna = new Attribute<>();
    /**
     * Configuration, description and extension
     * ...*/
}
