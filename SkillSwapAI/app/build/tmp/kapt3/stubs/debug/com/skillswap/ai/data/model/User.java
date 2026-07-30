package com.skillswap.ai.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

@com.google.firebase.database.IgnoreExtraProperties()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b6\b\u0087\b\u0018\u00002\u00020\u0001B\u00e9\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b\u00a2\u0006\u0002\u0010\u001dJ\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u00c6\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u00c6\u0003J\t\u0010<\u001a\u00020\u0012H\u00c6\u0003J\t\u0010=\u001a\u00020\u0014H\u00c6\u0003J\t\u0010>\u001a\u00020\u0014H\u00c6\u0003J\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\u0018H\u00c6\u0003J\t\u0010A\u001a\u00020\u0018H\u00c6\u0003J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\t\u0010C\u001a\u00020\u001bH\u00c6\u0003J\t\u0010D\u001a\u00020\u001bH\u00c6\u0003J\t\u0010E\u001a\u00020\u0003H\u00c6\u0003J\t\u0010F\u001a\u00020\u0003H\u00c6\u0003J\t\u0010G\u001a\u00020\u0003H\u00c6\u0003J\t\u0010H\u001a\u00020\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\t\u0010J\u001a\u00020\u0003H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\u00ed\u0001\u0010L\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u00c6\u0001J\u0013\u0010M\u001a\u00020\u00182\b\u0010N\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010O\u001a\u00020\u0014H\u00d6\u0001J\t\u0010P\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0011\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010!R\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010)R\u0011\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010)R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010!R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00100R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010!R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010!R\u0011\u0010\u001c\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010$R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010!\u00a8\u0006Q"}, d2 = {"Lcom/skillswap/ai/data/model/User;", "", "uid", "", "email", "name", "studentId", "profilePictureUrl", "college", "department", "year", "bio", "teachSkills", "", "learnSkills", "experienceLevel", "availability", "rating", "", "ratingCount", "", "skillCredits", "fcmToken", "isActive", "", "isOnline", "createdAt", "", "updatedAt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/util/List;DIILjava/lang/String;ZZJJ)V", "getAvailability", "()Ljava/util/List;", "getBio", "()Ljava/lang/String;", "getCollege", "getCreatedAt", "()J", "getDepartment", "getEmail", "getExperienceLevel", "getFcmToken", "()Z", "getLearnSkills", "getName", "getProfilePictureUrl", "getRating", "()D", "getRatingCount", "()I", "getSkillCredits", "getStudentId", "getTeachSkills", "getUid", "getUpdatedAt", "getYear", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class User {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String uid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String email = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String studentId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String profilePictureUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String college = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String department = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String year = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String bio = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> teachSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> learnSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String experienceLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> availability = null;
    private final double rating = 0.0;
    private final int ratingCount = 0;
    private final int skillCredits = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fcmToken = null;
    private final boolean isActive = false;
    private final boolean isOnline = false;
    private final long createdAt = 0L;
    private final long updatedAt = 0L;
    
    public User(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String studentId, @org.jetbrains.annotations.NotNull()
    java.lang.String profilePictureUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String college, @org.jetbrains.annotations.NotNull()
    java.lang.String department, @org.jetbrains.annotations.NotNull()
    java.lang.String year, @org.jetbrains.annotations.NotNull()
    java.lang.String bio, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> teachSkills, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> learnSkills, @org.jetbrains.annotations.NotNull()
    java.lang.String experienceLevel, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availability, double rating, int ratingCount, int skillCredits, @org.jetbrains.annotations.NotNull()
    java.lang.String fcmToken, boolean isActive, boolean isOnline, long createdAt, long updatedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStudentId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProfilePictureUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCollege() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDepartment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getYear() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBio() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTeachSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLearnSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExperienceLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAvailability() {
        return null;
    }
    
    public final double getRating() {
        return 0.0;
    }
    
    public final int getRatingCount() {
        return 0;
    }
    
    public final int getSkillCredits() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFcmToken() {
        return null;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    public final boolean isOnline() {
        return false;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
    
    public User() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component13() {
        return null;
    }
    
    public final double component14() {
        return 0.0;
    }
    
    public final int component15() {
        return 0;
    }
    
    public final int component16() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    public final boolean component18() {
        return false;
    }
    
    public final boolean component19() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component20() {
        return 0L;
    }
    
    public final long component21() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.data.model.User copy(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String studentId, @org.jetbrains.annotations.NotNull()
    java.lang.String profilePictureUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String college, @org.jetbrains.annotations.NotNull()
    java.lang.String department, @org.jetbrains.annotations.NotNull()
    java.lang.String year, @org.jetbrains.annotations.NotNull()
    java.lang.String bio, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> teachSkills, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> learnSkills, @org.jetbrains.annotations.NotNull()
    java.lang.String experienceLevel, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availability, double rating, int ratingCount, int skillCredits, @org.jetbrains.annotations.NotNull()
    java.lang.String fcmToken, boolean isActive, boolean isOnline, long createdAt, long updatedAt) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}