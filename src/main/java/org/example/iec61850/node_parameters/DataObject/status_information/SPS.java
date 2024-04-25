package org.example.iec61850.node_parameters.DataObject.status_information;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Quality;
import org.example.iec61850.common.modelData.TimeStamp;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class SPS extends MyData {
    /**
     * Single point status (Статус единой точки)
     * */
    /**
     * Status
     */
    private Attribute<Boolean> stVal = new Attribute<>();
    private Quality q = new Quality();
    private TimeStamp t = new TimeStamp();
    /**
     * Substitution and blocked
     */
    private Attribute<Boolean> subEna = new Attribute<>();
    private Attribute<Boolean> subVal = new Attribute<>();
    private Quality subQ = new Quality();
    private Attribute<String> subID = new Attribute<>();
    private Attribute<Boolean> blkEna = new Attribute<>();
    /**
     * Configuration, description and extension
     */
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
