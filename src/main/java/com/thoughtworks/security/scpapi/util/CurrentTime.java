package com.thoughtworks.security.scpapi.util;

import java.sql.Timestamp;
import java.util.Date;

public class CurrentTime {
    public static Timestamp getTime() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
