package com.sanket.newsreader.ui.di.core

import com.sanket.newsreader.ui.di.headlines.HeadlineSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class, DataSourceModule::class, RepositoryModule::class, UsecaseModule::class])
interface AppComponent {
    fun newsHeadlineSubComponent(): HeadlineSubComponent.Factory
}