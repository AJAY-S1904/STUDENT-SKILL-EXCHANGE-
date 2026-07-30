package com.skillswap.ai.ui.search;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.User;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/skillswap/ai/ui/search/SortOrder;", "", "(Ljava/lang/String;I)V", "HIGHEST_RATED_FIRST", "LOWEST_RATED_FIRST", "app_debug"})
public enum SortOrder {
    /*public static final*/ HIGHEST_RATED_FIRST /* = new HIGHEST_RATED_FIRST() */,
    /*public static final*/ LOWEST_RATED_FIRST /* = new LOWEST_RATED_FIRST() */;
    
    SortOrder() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.skillswap.ai.ui.search.SortOrder> getEntries() {
        return null;
    }
}