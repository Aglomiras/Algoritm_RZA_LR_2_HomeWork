package org.example.iec61850.lodicalNodes.hmi.other;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NHMIPoint<X, Y> {
    private final X value1;
    private final Y value2;

    public NHMIPoint(X value1, Y value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public X getValue1() {
        return value1;
    }

    public Y getValue2() {
        return value2;
    }
}
