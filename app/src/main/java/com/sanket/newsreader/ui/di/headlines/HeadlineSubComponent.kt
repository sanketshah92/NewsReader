package com.sanket.newsreader.ui.di.headlines

import com.sanket.newsreader.MainActivity
import dagger.Component
import dagger.Subcomponent

@HeadlineScope
@Subcomponent(modules = [HeadlineModule::class])
interface HeadlineSubComponent {
    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():HeadlineSubComponent
    }
}