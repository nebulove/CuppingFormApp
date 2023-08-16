package com.nebulov.cuppingformapp.di

import android.app.Application
import androidx.room.Room
import com.nebulov.cuppingformapp.feature_cup.data.data_source.CupDatabase
import com.nebulov.cuppingformapp.feature_cup.data.data_source.CupDatabase.Companion.MIGRATION_2_3
import com.nebulov.cuppingformapp.feature_cup.data.repository.CupRepositoryImpl
import com.nebulov.cuppingformapp.feature_cup.domain.repository.CupRepository
import com.nebulov.cuppingformapp.feature_cup.domain.use_case.AddCup
import com.nebulov.cuppingformapp.feature_cup.domain.use_case.CupUseCases
import com.nebulov.cuppingformapp.feature_cup.domain.use_case.DeleteCup
import com.nebulov.cuppingformapp.feature_cup.domain.use_case.DeleteSession
import com.nebulov.cuppingformapp.feature_cup.domain.use_case.GetCup
import com.nebulov.cuppingformapp.feature_cup.domain.use_case.GetCupList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCupDataBase(app: Application): CupDatabase {
        return Room.databaseBuilder(
            app,
            CupDatabase::class.java,
            CupDatabase.DATABASE_NAME
        ).addMigrations(MIGRATION_2_3)
            .build()
    }

    @Provides
    @Singleton
    fun provideCupRepository(db: CupDatabase): CupRepository {
        return CupRepositoryImpl(db.cupDao)
    }

    @Provides
    @Singleton
    fun provideCupUseCases(repository: CupRepository): CupUseCases {
        return CupUseCases(
            getCupList = GetCupList(repository),
            deleteCup = DeleteCup(repository),
            addCup = AddCup(repository),
            getCup = GetCup(repository),
            deleteSession = DeleteSession(repository)
        )
    }
}