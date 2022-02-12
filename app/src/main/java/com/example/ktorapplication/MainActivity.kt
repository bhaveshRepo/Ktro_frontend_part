package com.example.ktorapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.ktorapplication.data.Resource
import com.example.ktorapplication.databinding.ActivityMainBinding
import com.example.ktorapplication.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.response.observe(this@MainActivity,{
            event -> when(event){
                    is Resource.Success ->{
                        Glide.with(this@MainActivity).load(event.data!!.imageUrl).into(binding.ivWallpaper)
                    }
                is Resource.Error -> {
                    Log.e("Main Activity", "Unexpected error")
                }
            }
            }
            )
        }

//        val wallpaper = viewModel.state.value.wallpaper
//        val isLoading = viewModel.state.value.isLoading
//        wallpaper?.let {
////            binding.ivWallpaper.setImageURI(wallpaper.imageUrl.toUri())
////            Image(painter = rememberImagePainter(
////                data = wallpaper.imageUrl,
////                builder = {
////                    crossfade(enable = true)
////                }
////            ),
////                contentDescription = "Wallpaper"
////
////            )
//            Glide.with(this).load(wallpaper.imageUrl).into(binding.ivWallpaper)
//        }

        binding.btChange.setOnClickListener {
            viewModel.getRandomWallpaper()
        }


    }
}