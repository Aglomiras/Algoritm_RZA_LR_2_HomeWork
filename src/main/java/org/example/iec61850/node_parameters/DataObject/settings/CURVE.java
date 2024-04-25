package org.example.iec61850.node_parameters.DataObject.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class CURVE extends MyData {
    /**
     * Setting curve (Установочная кривая)
     * */
    /**
     * Setting
     */
    private Attribute<setCharact> setCharactAttribute = new Attribute<>();

    private enum setCharact {

    }

    private Attribute<Float> setParA = new Attribute<>();
    private Attribute<Float> setParB = new Attribute<>();
    private Attribute<Float> setParC = new Attribute<>();
    private Attribute<Float> setParD = new Attribute<>();
    private Attribute<Float> setParE = new Attribute<>();
    private Attribute<Float> setParF = new Attribute<>();
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
