package com.expfool.entity;

import java.util.TimeZone;

public enum Cities {
    VVO(TimeZone.getTimeZone("Asia/Vladivostok")),
    TLV(TimeZone.getTimeZone("Asia/Tel_Aviv")),
    LRN(TimeZone.getTimeZone("Asia/Nicosia")),
    UFA(TimeZone.getTimeZone("Asia/Yekaterinburg"));

    public final TimeZone timeZone;

    Cities(
            TimeZone timeZone
    ) {
        this.timeZone = timeZone;
    }
}
