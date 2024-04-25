package org.example.iec61850.common.modelData;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class Originator extends MyData {
    private Attribute<orCat> orCatAttribute = new Attribute<>();

    public enum orCat {
        NOT_SUPPORTED,
        BAY_CONTROL,
        STATION_CONTROL,
        REMOTE_CONTROL,
        AUTOMATIC_BAY,
        AUTOMATIC_STATION,
        AUTOMATIC_REMOTE,
        MAINTENANCE,
        PROCESS
    }

    private Attribute<String> orldent = new Attribute<>();
}
