package org.example.iec61850.common.modelData;

import lombok.Getter;
import lombok.Setter;
import org.example.iec61850.common.datatypes.MyData;

@Getter
@Setter
public class Unit extends MyData {
    /**
     * Unit (единицы измерения)
     */
    public enum SIUnit {
        AMPERE,
        VOLT,
        OMH,
        SECOND,
        VOLT_AMPERE,
        WATTS,
        VOLT_AMPERE_REACTIVE,
        DEGREES,
        VOLT_SECONDS,
        VOLT_SQUARE,
        AMP_SECOND,
        AMP_SQUARE,
        AMP_SQUARE_SECOND,
        VOLT_AMPERE_HOURS,
        VOLTS_PER_HERTZ,
        HERTZ_PER_SECOND,
        WATT_PER_SECOND
    }

    /**
     * Размерность
     */
    public enum multiplier {
        YOCTO,
        ZEPTO,
        ATTO,
        FEMTO,
        PICO,
        NANO,
        MICRO,
        MILLI,
        CENTI,
        DECI,
        DECA,
        HECTO,
        KILO,
        MEGA,
        GIGA,
        TERA,
        PETA,
        EXA,
        ZETTA,
        YOTTA
    }

    private Attribute<SIUnit> siUnitAttribute = new Attribute<>();
    private Attribute<multiplier> multiplierAttribute = new Attribute<>();
}
