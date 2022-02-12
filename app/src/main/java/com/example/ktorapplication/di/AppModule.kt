package com.example.ktorapplication.di

import com.example.ktorapplication.data.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRandomWallpaper() : WallpaperApi{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.19:9100/")
            .build().create(WallpaperApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: WallpaperApi) : MainRepository = MainRepository(api)

}