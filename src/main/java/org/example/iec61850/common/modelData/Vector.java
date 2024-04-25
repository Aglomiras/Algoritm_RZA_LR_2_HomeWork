package org.example.iec61850.common.modelData;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class Vector extends MyData {
    /**
     * Vector (Вектор: амплитуда и фаза)
     */
    private AnalogueValue mag = new AnalogueValue();
    private AnalogueValue ang = new AnalogueValue();
}
