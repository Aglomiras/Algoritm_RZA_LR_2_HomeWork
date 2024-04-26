package org.example.iec61850.node_parameters.DataObject.measured_and_metered_values;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.node_parameters.DataObject.CMV;

@Getter
@Setter
public class SEQ extends MyData {
    private CMV c1 = new CMV();
    private CMV c2 = new CMV();
    private CMV c3 = new CMV();

    private Attribute<seqT> seqTAttribute = new Attribute<>();

    private enum seqT {
        POS_NEG_ZERO,
        DIR_QUAD_ZERO
    }

    private Attribute<phsRef> phsRefAttribute = new Attribute<>();

    private enum phsRef {
        A,
        B,
        C
    }

    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
