package com.skillswap.ai.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

@com.google.firebase.database.IgnoreExtraProperties()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00a5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0014J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0012H\u00c6\u0003J\t\u0010.\u001a\u00020\u0012H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\u00a9\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u00c6\u0001J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010;\u001a\u00020<H\u00d6\u0001J\t\u0010=\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0011\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016\u00a8\u0006>"}, d2 = {"Lcom/skillswap/ai/data/model/MeetingRequest;", "", "meetingId", "", "requestId", "learnerId", "learnerName", "learnerProfilePic", "teacherId", "teacherName", "teacherProfilePic", "meetingDate", "meetingTime", "meetingMode", "meetingLocationOrLink", "notes", "meetingStatus", "createdAt", "", "updatedAt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJ)V", "getCreatedAt", "()J", "getLearnerId", "()Ljava/lang/String;", "getLearnerName", "getLearnerProfilePic", "getMeetingDate", "getMeetingId", "getMeetingLocationOrLink", "getMeetingMode", "getMeetingStatus", "getMeetingTime", "getNotes", "getRequestId", "getTeacherId", "getTeacherName", "getTeacherProfilePic", "getUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class MeetingRequest {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meetingId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String requestId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String learnerId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String learnerName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String learnerProfilePic = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String teacherId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String teacherName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String teacherProfilePic = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meetingDate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meetingTime = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meetingMode = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meetingLocationOrLink = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String notes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meetingStatus = null;
    private final long createdAt = 0L;
    private final long updatedAt = 0L;
    
    public MeetingRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String meetingId, @org.jetbrains.annotations.NotNull()
    java.lang.String requestId, @org.jetbrains.annotations.NotNull()
    java.lang.String learnerId, @org.jetbrains.annotations.NotNull()
    java.lang.String learnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String learnerProfilePic, @org.jetbrains.annotations.NotNull()
    java.lang.String teacherId, @org.jetbrains.annotations.NotNull()
    java.lang.String teacherName, @org.jetbrains.annotations.NotNull()
    java.lang.String teacherProfilePic, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingDate, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingTime, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingMode, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingLocationOrLink, @org.jetbrains.annotations.NotNull()
    java.lang.String notes, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingStatus, long createdAt, long updatedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeetingId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRequestId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLearnerId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLearnerName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLearnerProfilePic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTeacherId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTeacherName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTeacherProfilePic() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeetingDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeetingTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeetingMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeetingLocationOrLink() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeetingStatus() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
    
    public MeetingRequest() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    public final long component15() {
        return 0L;
    }
    
    public final long component16() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
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
    public final com.skillswap.ai.data.model.MeetingRequest copy(@org.jetbrains.annotations.NotNull()
    java.lang.String meetingId, @org.jetbrains.annotations.NotNull()
    java.lang.String requestId, @org.jetbrains.annotations.NotNull()
    java.lang.String learnerId, @org.jetbrains.annotations.NotNull()
    java.lang.String learnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String learnerProfilePic, @org.jetbrains.annotations.NotNull()
    java.lang.String teacherId, @org.jetbrains.annotations.NotNull()
    java.lang.String teacherName, @org.jetbrains.annotations.NotNull()
    java.lang.String teacherProfilePic, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingDate, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingTime, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingMode, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingLocationOrLink, @org.jetbrains.annotations.NotNull()
    java.lang.String notes, @org.jetbrains.annotations.NotNull()
    java.lang.String meetingStatus, long createdAt, long updatedAt) {
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