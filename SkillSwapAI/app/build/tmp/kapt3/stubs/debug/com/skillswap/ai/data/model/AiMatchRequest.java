package com.skillswap.ai.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u00c6\u0003J]\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0004H\u00d6\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e\u00a8\u0006#"}, d2 = {"Lcom/skillswap/ai/data/model/AiMatchRequest;", "", "teach_skills", "", "", "learning_skills", "experience", "rating", "", "availability", "candidates", "Lcom/skillswap/ai/data/model/CandidateProfile;", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;DLjava/util/List;Ljava/util/List;)V", "getAvailability", "()Ljava/util/List;", "getCandidates", "getExperience", "()Ljava/lang/String;", "getLearning_skills", "getRating", "()D", "getTeach_skills", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class AiMatchRequest {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> teach_skills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> learning_skills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String experience = null;
    private final double rating = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> availability = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.CandidateProfile> candidates = null;
    
    public AiMatchRequest(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> teach_skills, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> learning_skills, @org.jetbrains.annotations.NotNull()
    java.lang.String experience, double rating, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availability, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.CandidateProfile> candidates) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTeach_skills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLearning_skills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExperience() {
        return null;
    }
    
    public final double getRating() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAvailability() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.CandidateProfile> getCandidates() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.CandidateProfile> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.data.model.AiMatchRequest copy(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> teach_skills, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> learning_skills, @org.jetbrains.annotations.NotNull()
    java.lang.String experience, double rating, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availability, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.CandidateProfile> candidates) {
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