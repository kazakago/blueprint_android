package com.kazakago.blueprint.data.api.di

import com.kazakago.blueprint.data.api.global.ApiRequester
import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Singleton
    @Provides
    fun provideGithubService(apiRequester: ApiRequester): GithubApi {
        return apiRequester.create(GithubApi::class)
    }
}
