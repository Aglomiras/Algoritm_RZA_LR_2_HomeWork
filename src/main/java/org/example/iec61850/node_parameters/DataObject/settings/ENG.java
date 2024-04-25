package org.example.iec61850.node_parameters.DataObject.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class ENG extends MyData {
    /**
     * Enumerated status setting (Настройка перечисляемого статуса)
     * */
    /**
     * Setting
     */
    private Attribute<setVal> setValAttribute = new Attribute<>();

    private enum setVal {

    }
    /**
     * Status
     * ...*/

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
