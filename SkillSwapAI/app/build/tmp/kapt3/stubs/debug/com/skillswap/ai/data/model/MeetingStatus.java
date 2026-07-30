package com.skillswap.ai.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/skillswap/ai/data/model/MeetingStatus;", "", "(Ljava/lang/String;I)V", "PENDING", "CONFIRMED", "RESCHEDULED", "REJECTED", "COMPLETED", "app_debug"})
public enum MeetingStatus {
    /*public static final*/ PENDING /* = new PENDING() */,
    /*public static final*/ CONFIRMED /* = new CONFIRMED() */,
    /*public static final*/ RESCHEDULED /* = new RESCHEDULED() */,
    /*public static final*/ REJECTED /* = new REJECTED() */,
    /*public static final*/ COMPLETED /* = new COMPLETED() */;
    
    MeetingStatus() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.skillswap.ai.data.model.MeetingStatus> getEntries() {
        return null;
    }
}