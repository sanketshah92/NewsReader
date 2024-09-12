package com.sanket.newsreader

import android.app.Application
import android.util.Log
import com.sanket.newsreader.ui.di.core.AppComponent
import com.sanket.newsreader.ui.di.core.AppModule
import com.sanket.newsreader.ui.di.core.DaggerAppComponent
import com.sanket.newsreader.ui.di.core.DataSourceModule
import com.sanket.newsreader.ui.di.core.Injector
import com.sanket.newsreader.ui.di.core.NetworkModule
import com.sanket.newsreader.ui.di.headlines.HeadlineSubComponent


class NewsReaderApplication: Application(),Injector {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .networkModule(NetworkModule("https://newsapi.org/v2/"))
                .build()

    }

    override fun createHeadlineSubcomponent(): HeadlineSubComponent {
        Log.e("CREATING","::")
        return appComponent.newsHeadlineSubComponent().create()
    }
}