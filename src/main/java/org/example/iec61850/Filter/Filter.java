package org.example.iec61850.Filter;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.node_parameters.DataObject.CMV;
import org.example.iec61850.node_parameters.DataObject.measured_and_metered_values.MV;

@Getter
@Setter
public abstract class Filter {
    public abstract void process(MV measuredValue, CMV complexMeasurementValue);
}
