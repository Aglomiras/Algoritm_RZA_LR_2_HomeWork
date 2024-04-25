package org.example.iec61850.common.modelData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attribute<T> extends MyData {
    /**
     * Attribute (Атрибут: Дженерик)
     */
    private T value;
}
