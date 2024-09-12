package com.sanket.newsreader.ui.di.core

import android.content.Context
import com.sanket.newsreader.ui.di.headlines.HeadlineSubComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [HeadlineSubComponent::class])
class AppModule(private  val context: Context) {

    @Provides
    fun provideContext():Context{
        return context.applicationContext
    }
}