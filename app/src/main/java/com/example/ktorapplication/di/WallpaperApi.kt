package com.example.ktorapplication.di

import com.example.ktorapplication.data.Wallpaper
import retrofit2.Response
import retrofit2.http.GET

interface WallpaperApi {

    @GET("/randomWallpaper")
    suspend fun getWallpapers() : Response<Wallpaper>

}