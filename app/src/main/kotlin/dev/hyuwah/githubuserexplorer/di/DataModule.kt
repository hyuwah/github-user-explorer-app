package dev.hyuwah.githubuserexplorer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.hyuwah.githubuserexplorer.data.repository.GithubRepositoryImpl
import dev.hyuwah.githubuserexplorer.domain.repository.GithubRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindsGithubRepository(
        githubRepositoryImpl: GithubRepositoryImpl
    ): GithubRepository

}