package com.sanket.newsreader.ui.di.core

import com.sanket.newsreader.ui.di.headlines.HeadlineSubComponent

interface Injector {
    fun createHeadlineSubcomponent():HeadlineSubComponent
}