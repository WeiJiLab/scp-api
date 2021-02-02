package com.thoughtworks.security.scpapi.entity;

public enum SecurityToolCategory {
    SCA(0), SAST(1), DAST(2), IAST(3), AUDIT(4);
    private int value;
    private SecurityToolCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}