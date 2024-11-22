package com.codewithdipesh.tracker_domain.di

import com.codewithdipesh.core.domain.Preferences.Preferences
import com.codewithdipesh.tracker_domain.repository.TrackerRepository
import com.codewithdipesh.tracker_domain.usecase.CalculateMealNutriments
import com.codewithdipesh.tracker_domain.usecase.DeleteTrackFood
import com.codewithdipesh.tracker_domain.usecase.GetFoodById
import com.codewithdipesh.tracker_domain.usecase.GetFoodsByDate
import com.codewithdipesh.tracker_domain.usecase.GetTrackedFood
import com.codewithdipesh.tracker_domain.usecase.SearchFood
import com.codewithdipesh.tracker_domain.usecase.TrackFood
import com.codewithdipesh.tracker_domain.usecase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class TrackerDomainModule {

    @ViewModelScoped
    @Provides

    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences : Preferences
    ) : TrackerUseCases{
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsByDate(repository),
            deleteTrackedFood = DeleteTrackFood(repository),
            getFoodById = GetFoodById(repository),
            calculateMealNutriments = CalculateMealNutriments(preferences),
            getTrackedFood = GetTrackedFood(repository)
        )
    }


}