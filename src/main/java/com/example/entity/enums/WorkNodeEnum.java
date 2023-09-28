package com.example.entity.enums;

public enum WorkNodeEnum {

    CONTAINER(1),

    ACTUAL(2);

    /**
     * Lock type
     */
    private final Integer type;

    /**
     * Constructor with field of type
     */
    WorkNodeEnum(Integer type) {
        this.type = type;
    }

    public Integer value() {
        return type;
    }
}
