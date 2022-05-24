package com.example.demofilecloud

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demofilecloud.databinding.ActivityMainBinding
import io.reactivex.functions.Action
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setClickCV {
            startActivity(Intent(this, CVActivity::class.java))
        }
    }

    object RxActions {
        @JvmStatic
        fun runSafe(action: Action?) {
            try {
                action?.run()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}