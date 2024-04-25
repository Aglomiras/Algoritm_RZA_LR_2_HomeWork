package org.example.iec61850.node_parameters.DataObject.measured_and_metered_values;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.datatypes.MyData;
import org.example.iec61850.node_parameters.DataObject.CMV;

@Getter
@Setter
public class DEL extends MyData {
    /**
     * Phase to phase related measured values of a three-phase system
     * (Измеренные значения трехфазной системы, относящиеся к фазе)
     */
    /**
     * SubDataObject
     * */
    private CMV phsAB = new CMV();
    private CMV phsBC = new CMV();
    private CMV phsCA = new CMV();
    private Attribute<angRef> angRefAttribute = new Attribute<>();
    /**
     * Status
     * ...*/

    /**
     * Substitution and blocked
     * ...*/

    /**
     * Configuration, description and extension
     */
    private enum angRef {
        VA,
        VB,
        VC,
        AA,
        AB,
        AC,
        VAB,
        VBC,
        VCA,
        VOTHER,
        AOTHER,
        SYNCHROPHASOR
    }

    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
