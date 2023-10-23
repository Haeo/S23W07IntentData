package kr.ac.kumoh.ce.com.s20190839.s23w07intentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.kumoh.ce.com.s20190839.s23w07intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    companion object{
        const val IMAGE_NAME = "image name"
        const val IMAGE_RESULT = "image result"

        const val LIKE = 100
        const val DISLIKE = 101
        const val NONE = 0
    }

    private lateinit var main: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityImageBinding.inflate(layoutInflater)
        setContentView(main.root)


//        Log.i("Image!!!", intent.getStringExtra("location") ?: "없음")
        val res = when (intent.getStringExtra(MainActivity.KEY_NAME)) {
            MainActivity.IMAGE_MOUNTAIN -> R.drawable.mountain
            MainActivity.IMAGE_SEA -> R.drawable.sea
            else -> R.drawable.ic_launcher_foreground
        }

        main.image.setImageResource(res)
    }
}