package org.example.iec61850.common.modelData;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class AnalogueValue extends MyData {
    /**
     * INTEGER
     * FLOATING
     */
    private Attribute<Integer> intVal = new Attribute<>();
    private Attribute<Double> floatVal = new Attribute<>();
}
