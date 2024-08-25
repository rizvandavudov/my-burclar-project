package com.example.burcrehberiapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.burcrehberiapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var logoDondurAnimation = AnimationUtils.loadAnimation(this, R.anim.logodondur)

        binding.imgLogo.animation = logoDondurAnimation

    }

    override fun onResume() {

        object :CountDownTimer(5000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                var intent=Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
            }

        }.start()



        super.onResume()
    }
}
