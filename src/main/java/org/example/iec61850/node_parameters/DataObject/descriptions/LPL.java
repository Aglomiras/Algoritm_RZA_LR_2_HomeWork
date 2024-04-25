package org.example.iec61850.node_parameters.DataObject.descriptions;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;
import org.example.iec61850.common.modelData.Attribute;

@Getter
@Setter
public class LPL extends MyData {
    /**
     * Logical node name plate (Табличка с названием логического узла)
     * */
    /**
     * Configuration, description and extension
     */
    private Attribute<String> vendor = new Attribute<>();
    private Attribute<String> swRev = new Attribute<>();
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> configRev = new Attribute<>();
    private Attribute<Integer> paramRev = new Attribute<>();
    private Attribute<Integer> valRev = new Attribute<>();
    private Attribute<String> ldNs = new Attribute<>();
    private Attribute<String> lnNs = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
