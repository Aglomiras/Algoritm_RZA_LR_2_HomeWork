package org.example.iec61850.common.modelData;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class Quality extends MyData {
    /**
     * Quality (Качество)
     */
    public enum validity {
        GOOD,
        INVALID,
        RESERVED,
        QUESTIONABLE
    }

    private Attribute<validity> validityAttribute = new Attribute<>();
    private Attribute<Boolean> overflow = new Attribute<>(false);
    private Attribute<Boolean> outOfRange = new Attribute<>(false);
    private Attribute<Boolean> badReference = new Attribute<>(false);
    private Attribute<Boolean> oscillatory = new Attribute<>(false);
    private Attribute<Boolean> failure = new Attribute<>(false);
    private Attribute<Boolean> oldData = new Attribute<>(false);
    private Attribute<Boolean> inconsistent = new Attribute<>(false);
    private Attribute<Boolean> inaccurate = new Attribute<>(false);

    public enum source {
        PROCESS,
        SUBSTITUTED
    }

    private source sr = source.PROCESS;
    private Attribute<Boolean> test = new Attribute<>(false);
    private Attribute<Boolean> operatorBlocked = new Attribute<>(false);
}
