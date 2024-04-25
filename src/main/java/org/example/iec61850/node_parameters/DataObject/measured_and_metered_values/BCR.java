package org.example.iec61850.node_parameters.DataObject.measured_and_metered_values;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Quality;
import org.example.iec61850.common.modelData.TimeStamp;
import org.example.iec61850.common.modelData.Unit;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class BCR extends MyData {
    /**
     * Binary counter reading (Считывание двоичного счетчика)
     * */
    /**
     * Status
     */
    private Attribute<Integer> actVal = new Attribute<>();
    private Attribute<Integer> frVal = new Attribute<>();
    private TimeStamp frTm = new TimeStamp();
    private Quality q = new Quality();
    private TimeStamp t = new TimeStamp();
    /**
     * Substitution and blocked
     * ...*/

    /**
     * Configuration, description and extension
     */
    private Unit units = new Unit();
    private Attribute<Float> pulsQty = new Attribute<>();
    private Attribute<Boolean> frEna = new Attribute<>();
    private TimeStamp strTm = new TimeStamp();
    private Attribute<Integer> frPd = new Attribute<>();
    private Attribute<Boolean> frRs = new Attribute<>();
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
