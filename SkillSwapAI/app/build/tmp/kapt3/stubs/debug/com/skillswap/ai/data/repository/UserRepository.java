package com.skillswap.ai.data.repository;

import android.net.Uri;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.skillswap.ai.data.model.User;
import kotlinx.coroutines.flow.Flow;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.ByteArrayOutputStream;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\fH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\"\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\f2\u0006\u0010\u001c\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\"\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\f2\u0006\u0010\u001e\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\"\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\f2\u0006\u0010 \u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J$\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010#J*\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00180\u0012H\u0086@\u00a2\u0006\u0002\u0010&J*\u0010\'\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00180\u0012H\u0086@\u00a2\u0006\u0002\u0010&J0\u0010(\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010*H\u0086@\u00a2\u0006\u0002\u0010+J$\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00180\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.H\u0086@\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/skillswap/ai/data/repository/UserRepository;", "", "context", "Landroid/content/Context;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "(Landroid/content/Context;Lcom/google/firebase/database/FirebaseDatabase;Lcom/google/firebase/storage/FirebaseStorage;)V", "usersRef", "Lcom/google/firebase/database/DatabaseReference;", "createUser", "Lcom/skillswap/ai/data/repository/AuthResult;", "", "user", "Lcom/skillswap/ai/data/model/User;", "(Lcom/skillswap/ai/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllUsers", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllUsersFlow", "Lkotlinx/coroutines/flow/Flow;", "getUser", "uid", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserFlow", "searchUsersByCollege", "college", "searchUsersByDepartment", "department", "searchUsersBySkill", "skill", "updateFcmToken", "token", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLearnSkills", "skills", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTeachSkills", "updateUser", "updates", "", "(Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadProfilePicture", "uri", "Landroid/net/Uri;", "(Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.storage.FirebaseStorage storage = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.DatabaseReference usersRef = null;
    
    @javax.inject.Inject()
    public UserRepository(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database, @org.jetbrains.annotations.NotNull()
    com.google.firebase.storage.FirebaseStorage storage) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createUser(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getUser(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<com.skillswap.ai.data.model.User>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.skillswap.ai.data.model.User> getUserFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String uid) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateUser(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> updates, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object uploadProfilePicture(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<? extends java.util.List<com.skillswap.ai.data.model.User>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.skillswap.ai.data.model.User>> getAllUsersFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchUsersBySkill(@org.jetbrains.annotations.NotNull()
    java.lang.String skill, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<? extends java.util.List<com.skillswap.ai.data.model.User>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchUsersByCollege(@org.jetbrains.annotations.NotNull()
    java.lang.String college, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<? extends java.util.List<com.skillswap.ai.data.model.User>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchUsersByDepartment(@org.jetbrains.annotations.NotNull()
    java.lang.String department, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<? extends java.util.List<com.skillswap.ai.data.model.User>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateFcmToken(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTeachSkills(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> skills, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateLearnSkills(@org.jetbrains.annotations.NotNull()
    java.lang.String uid, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> skills, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.skillswap.ai.data.repository.AuthResult<kotlin.Unit>> $completion) {
        return null;
    }
}