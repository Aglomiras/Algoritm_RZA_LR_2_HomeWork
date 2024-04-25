package org.example.iec61850.node_parameters.DataObject.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.AnalogueValue;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Unit;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class ASG extends MyData {
    /**
     * Analogue setting (Аналоговая настройка)
     * */
    /**
     * Setting
     */
    private AnalogueValue setMag = new AnalogueValue();
    /**
     * Status
     * ...*/

    /**
     * Substitution and blocked
     * ...*/

    /**
     * Configuration, description and extension
     */
    private Unit units = new Unit();
    private AnalogueValue minVal = new AnalogueValue();
    private AnalogueValue maxVal = new AnalogueValue();
    private AnalogueValue stepSize = new AnalogueValue();
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
