package com.thoughtworks.security.scpapi.util;

import java.util.Arrays;

public enum ScanResultEnum {
    SCANNING(0), FAILED(1), SUCCESS(2);
    private Integer value;

    ScanResultEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static ScanResultEnum parse(Integer result) {
        return Arrays.stream(ScanResultEnum.values())
                .filter(it -> it.getValue().equals(result))
                .findFirst().get();
    }

}
