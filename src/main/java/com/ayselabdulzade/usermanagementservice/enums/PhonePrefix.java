package com.ayselabdulzade.usermanagementservice.enums;

public enum PhonePrefix{
    _050("050", "Azercell"),
    _055("055", "Bakcell"),
    _051("051", "Azercell"),
    _070("070", "Nar"),
    _077("077", "Nar"),
    _099("099", "Bakcell"),
    _010("010", "Bakcell");
    private final String code;
    private final String operator;

    PhonePrefix(String code, String operator) {
        this.code = code;
        this.operator = operator;
    }

    public String getCode() {
        return code;
    }

    public String getOperator() {
        return operator;
    }

}
