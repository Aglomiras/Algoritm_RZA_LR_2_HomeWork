package org.example.iec61850.node_parameters.DataObject.settings;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.modelData.Attribute;
import org.example.iec61850.common.modelData.Unit;
import org.example.iec61850.common.datatypes.MyData;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CSD extends MyData {
    /**
     * Curve shape description (Описание формы кривой)
     * */
    /**
     * Configuration, description and extension
     */
    private Unit xUnits = new Unit();
    private Attribute<String> xD = new Attribute<>();
    private Attribute<Character.UnicodeBlock> xDU = new Attribute<>();
    private Unit yUnits = new Unit();
    private Attribute<String> yD = new Attribute<>();
    private Attribute<Character.UnicodeBlock> yDU = new Attribute<>();
    private Unit zUnits = new Unit();
    private Attribute<String> zD = new Attribute<>();
    private Attribute<Character.UnicodeBlock> zDU = new Attribute<>();
    private Attribute<Integer> numPts = new Attribute<>();
    private List<Double> crvPts = new ArrayList<>();
    private Attribute<String> d = new Attribute<>();
    private Attribute<Character.UnicodeBlock> dU = new Attribute<>();
    private Attribute<String> cdcNs = new Attribute<>();
    private Attribute<String> cdcName = new Attribute<>();
    private Attribute<String> dataNs = new Attribute<>();
}
