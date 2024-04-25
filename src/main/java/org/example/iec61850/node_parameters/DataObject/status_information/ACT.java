package org.example.iec61850.node_parameters.DataObject.status_information;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Originator;
import org.example.iec61850.common.modelData.Quality;
import org.example.iec61850.common.modelData.TimeStamp;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class ACT extends MyData {
    /**
     * Protection activation information (Информация об активации защиты)
     * */
    /**
     * Status
     */
    private Attribute<Boolean> general = new Attribute<>();
    private Attribute<Boolean> phsA = new Attribute<>();
    private Attribute<Boolean> phsB = new Attribute<>();
    private Attribute<Boolean> phsC = new Attribute<>();
    private Attribute<Boolean> neut = new Attribute<>();
    private Quality q = new Quality();
    private TimeStamp t = new TimeStamp();
    private Originator origin = new Originator();
    private TimeStamp operTmPhsA = new TimeStamp();
    private TimeStamp operTmPhsB = new TimeStamp();
    private TimeStamp operTmPhsC = new TimeStamp();
    /**
     * Configuration, description and extension
     */
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
