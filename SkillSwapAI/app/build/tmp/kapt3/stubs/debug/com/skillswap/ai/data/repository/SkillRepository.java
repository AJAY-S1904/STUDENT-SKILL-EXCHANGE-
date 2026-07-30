package com.skillswap.ai.data.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skillswap.ai.data.model.Skill;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000e0\bH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/skillswap/ai/data/repository/SkillRepository;", "", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "skillsRef", "Lcom/google/firebase/database/DatabaseReference;", "addSkill", "Lcom/skillswap/ai/data/repository/AuthResult;", "", "skill", "Lcom/skillswap/ai/data/model/Skill;", "(Lcom/skillswap/ai/data/model/Skill;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSkills", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementPopularity", "skillName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SkillRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference skillsRef = null;
    
    @javax.inject.Inject()
    public SkillRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllSkills(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<? extends java.util.List<com.skillswap.ai.data.model.Skill>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addSkill(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.Skill skill, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object incrementPopularity(@org.jetbrains.annotations.NotNull()
    java.lang.String skillName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
}