package org.example.iec61850.node_parameters.DataObject.status_information;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Quality;
import org.example.iec61850.common.modelData.TimeStamp;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class ACD extends MyData {
    /**
     * Directional protection activation information
     * (Информация об активации направленной защиты)
     * */
    /**
     * Status
     */
    private Attribute<Boolean> general = new Attribute<>();
    private Attribute<dirGeneral> dirGeneralAttribute = new Attribute<>();

    private enum dirGeneral {
        UNKNOWN,
        FORWARD,
        BACKWARD,
        BOTH
    }

    private Attribute<Boolean> phsA = new Attribute<>();
    private Attribute<dirPhsA> dirPhsAAttribute = new Attribute<>();

    private enum dirPhsA {
        UNKNOWN,
        FORWARD,
        BACKWARD
    }

    private Attribute<Boolean> phsB = new Attribute<>();
    private Attribute<dirPhsB> dirPhsBAttribute = new Attribute<>();

    private enum dirPhsB {
        UNKNOWN,
        FORWARD,
        BACKWARD
    }

    private Attribute<Boolean> phsC = new Attribute<>();
    private Attribute<dirPhsC> dirPhsCAttribute = new Attribute<>();

    private enum dirPhsC {
        UNKNOWN,
        FORWARD,
        BACKWARD
    }

    private Attribute<Boolean> neut = new Attribute<>();
    private Attribute<dirNeut> dirNeutAttribute = new Attribute<>();

    private enum dirNeut {
        UNKNOWN,
        FORWARD,
        BACKWARD
    }

    private Quality q = new Quality();
    private TimeStamp t = new TimeStamp();
    /**
     * Substitution and blocked
     * ...*/

    /**
     * Configuration, description and extension
     */
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
