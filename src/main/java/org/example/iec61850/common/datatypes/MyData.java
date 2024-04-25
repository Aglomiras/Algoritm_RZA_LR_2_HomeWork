package org.example.iec61850.common.datatypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MyData {
    /**
     * Data
     */
    private String name;
    private String reference;
}
