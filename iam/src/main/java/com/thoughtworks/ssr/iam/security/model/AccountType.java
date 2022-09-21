package com.thoughtworks.ssr.iam.security.model;

public enum AccountType {

    ADMIN,
    USER,
    OPLATFORM,
    OTHER,
    ;

    public static AccountType getAccountType(String accountType) {
         return AccountType.valueOf(accountType);
    }

}
