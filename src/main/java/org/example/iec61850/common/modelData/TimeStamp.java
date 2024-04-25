package org.example.iec61850.common.modelData;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class TimeStamp extends MyData {
    /**
     * TimeStamp (отметка времени)
     */
    private long value;
}
