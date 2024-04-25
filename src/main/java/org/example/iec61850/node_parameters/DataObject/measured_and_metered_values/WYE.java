package org.example.iec61850.node_parameters.DataObject.measured_and_metered_values;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.datatypes.MyData;
import org.example.iec61850.node_parameters.DataObject.CMV;

@Getter
@Setter
public class WYE extends MyData {
    /**
     * Phase to ground/neutral related measured values of a three-phase system
     * (Измеренные значения, относящиеся к фазе заземления/нейтрали трехфазной системы)
     * */
    /**
     * SubDataObject
     */
    private CMV phsA = new CMV();
    private CMV phsB = new CMV();
    private CMV phsC = new CMV();
    private CMV neut = new CMV();
    private CMV net = new CMV();
    private CMV res = new CMV();
    /**
     * Status
     * ...*/

    /**
     * Substitution and blocked
     * ...*/

    /**
     * Configuration, description and extension
     */
    private Attribute<angRef> angRefAttribute = new Attribute<>();

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

    private Attribute<Boolean> phsToNeut = new Attribute<>();
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
