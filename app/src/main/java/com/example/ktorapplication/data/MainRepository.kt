package com.example.ktorapplication.data



import com.example.ktorapplication.di.WallpaperApi
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: WallpaperApi
) {

    suspend fun getWallpapers() : Resource<Wallpaper> {
            return try {
                val response = api.getWallpapers()
                val result = response.body()
                if(response.isSuccessful && result != null){
                    Resource.Success(result)
                }else
                {
                    Resource.Error(response.message())
                }
            } catch (e: Exception){
                    Resource.Error("Resource Error : ${e.message}")
            }
    }

}