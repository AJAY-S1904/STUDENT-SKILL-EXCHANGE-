package com.skillswap.ai.data.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lcom/skillswap/ai/data/model/NotificationType;", "", "(Ljava/lang/String;I)V", "NEW_REQUEST", "REQUEST_ACCEPTED", "REQUEST_REJECTED", "SESSION_REMINDER", "RATING_REMINDER", "CREDIT_EARNED", "CREDIT_SPENT", "GENERAL", "app_debug"})
public enum NotificationType {
    /*public static final*/ NEW_REQUEST /* = new NEW_REQUEST() */,
    /*public static final*/ REQUEST_ACCEPTED /* = new REQUEST_ACCEPTED() */,
    /*public static final*/ REQUEST_REJECTED /* = new REQUEST_REJECTED() */,
    /*public static final*/ SESSION_REMINDER /* = new SESSION_REMINDER() */,
    /*public static final*/ RATING_REMINDER /* = new RATING_REMINDER() */,
    /*public static final*/ CREDIT_EARNED /* = new CREDIT_EARNED() */,
    /*public static final*/ CREDIT_SPENT /* = new CREDIT_SPENT() */,
    /*public static final*/ GENERAL /* = new GENERAL() */;
    
    NotificationType() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.skillswap.ai.data.model.NotificationType> getEntries() {
        return null;
    }
}