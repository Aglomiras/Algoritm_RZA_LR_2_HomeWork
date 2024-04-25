package org.example.iec61850.node_parameters.DataObject.descriptions;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class DPL extends MyData {
    /**
     * Device name plate (Заводская табличка устройства)
     * */
    /**
     * Configuration, description and extension
     */
    private Attribute<String> vendor = new Attribute<>();
    private Attribute<String> hwRev = new Attribute<>();
    private Attribute<String> swRev = new Attribute<>();
    private Attribute<String> serNum = new Attribute<>();
    private Attribute<String> model = new Attribute<>();
    private Attribute<String> location = new Attribute<>();
    private Attribute<String> nameDPL = new Attribute<>();
    private Attribute<String> owner = new Attribute<>();
    private Attribute<String> ePSName = new Attribute<>();
    private Attribute<String> primeOper = new Attribute<>();
    private Attribute<String> secondOper = new Attribute<>();
    private Attribute<Float> latitude = new Attribute<>();
    private Attribute<Float> longitude = new Attribute<>();
    private Attribute<Float> altitude = new Attribute<>();
    private Attribute<String> mRID = new Attribute<>();
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();

}
