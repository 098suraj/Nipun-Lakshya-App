package com.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.db.models.MentorPerformanceInsightsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MentorPerformanceInsightsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(insightsListEntity: MentorPerformanceInsightsItem?)

    @Query("SELECT * FROM mentor_performance_insights_list")
    fun getMentorPerformanceInsights(): Flow<MentorPerformanceInsightsItem>
}

