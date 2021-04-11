package ru.devivanov.coroutinesappexample.view

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.coroutines.*
import ru.devivanov.coroutinesappexample.databinding.ActivityMainBinding
import ru.devivanov.coroutinesappexample.view.view_models.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val scope = CoroutineScope(Dispatchers.IO)
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDogImageAsync()

        binding.button.setOnClickListener {
            getDogImageAsync()
        }
    }

    private fun getDogImageAsync() {
        scope.launch {
            val response = viewModel.getDoggyImageUrl().message
            withContext(Dispatchers.Main) {
                Glide.with(this@MainActivity)
                    .load(response)
                    .transform(CenterCrop(), RoundedCornersTransformation(50, 15))
                    .into(binding.image)
                binding.image.imageAnimationFadeIn()
            }
        }
    }

    private fun ImageView.imageAnimationFadeIn() {
        binding.image.alpha = 0f
        this.animate()
            .alpha(1f)
            .setDuration(1000)
            .start()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}