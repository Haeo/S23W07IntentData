package kr.ac.kumoh.ce.com.s20190839.s23w07intentdata

import android.app.Activity
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.ce.com.s20190839.s23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val KEY_NAME = "location"
        const val IMAGE_MOUNTAIN = "mountain"
        const val IMAGE_SEA = "sea"
    }

    private lateinit var main: ActivityMainBinding
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode != Activity.RESULT_OK)
            return@registerForActivityResult

        val result = it.data?.getIntExtra(ImageActivity.IMAGE_RESULT, ImageActivity.NONE)

        val str = when(result) {
            ImageActivity.LIKE -> "좋아요"
            ImageActivity.DISLIKE -> "싫어요"
            else -> "에러"
        }

        when(it.data?.getStringExtra(ImageActivity.IMAGE_NAME)){
            IMAGE_MOUNTAIN -> main.btnMountain.text = "산 ($str)"
            IMAGE_SEA -> main.btnSea.text = "바다 ($str)"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.btnMountain.setOnClickListener(this)
        main.btnSea.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val value = when (v?.id) {
            main.btnMountain.id -> {
                Toast.makeText(this, "산 이미지", Toast.LENGTH_SHORT).show()
                IMAGE_MOUNTAIN
            }
            main.btnSea.id -> {
                Toast.makeText(this, "바다 이미지", Toast.LENGTH_SHORT).show()
                IMAGE_SEA
            }
            else -> return
        }
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(KEY_NAME, value)
//        startActivity(intent)
        startForResult.launch(intent)
    }
}