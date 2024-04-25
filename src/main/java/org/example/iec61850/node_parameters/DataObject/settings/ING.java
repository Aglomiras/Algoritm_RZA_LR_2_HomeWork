package org.example.iec61850.node_parameters.DataObject.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Unit;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class ING extends MyData {
    /**
     * Integer status setting (Настройка целочисленного статуса)
     * */
    /**
     * Setting
     */
    private Attribute<Integer> setVal = new Attribute<>();
    /**
     * Status
     * ...*/

    /**
     * Substitution and blocked
     * ...*/

    /**
     * Configuration, description and extension
     */
    private Attribute<Integer> minVal = new Attribute<>();
    private Attribute<Integer> maxVal = new Attribute<>();
    private Attribute<Integer> stepSize = new Attribute<>();
    private Unit units = new Unit();
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
