package com.example.ktorapplication.vm

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ktorapplication.data.MainRepository
import com.example.ktorapplication.data.Resource
import com.example.ktorapplication.data.Wallpaper
import com.example.ktorapplication.di.WallpaperApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _response = MutableLiveData<Resource<Wallpaper>>()
    val response : LiveData<Resource<Wallpaper>> = _response

    fun getRandomWallpaper(){
        viewModelScope.launch {
            _response.postValue(Resource.Loading())
            val wallpaperData = repository.getWallpapers()
            if(wallpaperData == null){
                _response.postValue(Resource.Error("Empty Data"))
            }
            else{
                wallpaperData.data?.let {
                    _response.postValue(Resource.Success(it))
                }
            }
        }
    }


//    private val _state = mutableStateOf<WallpaperState>(WallpaperState())
//    val state : State<WallpaperState> = _state
//


//    data class WallpaperState(
//
//        val wallpaper: Wallpaper? = null,
//        val isLoading : Boolean = false
//
//    )
//    init {
//        getRandomWallpaper()
//    }
//
//    fun getRandomWallpaper(){
//        viewModelScope.launch {
//            try {
//                _state.value = state.value.copy(isLoading = true)
//                _state.value = state.value.copy(wallpaper = api.getWallpapers(),
//                    isLoading = false)
//            }catch (e: Exception){
//                Log.e("Main view model","getRandomWallpaper : $e ")
//                _state.value = state.value.copy(isLoading = false)
//            }
//
//
//        }
//    }

}