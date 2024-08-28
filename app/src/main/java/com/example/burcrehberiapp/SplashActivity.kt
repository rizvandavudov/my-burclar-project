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

// SplashActivity adlı fəaliyyət (activity) sinifi 'AppCompatActivity'-dən miras alır
class SplashActivity : AppCompatActivity() {
    // ActivitySplashBinding obyektini yaratmaq üçün dəyişən. Bu obyekt vasitəsilə XML faylındakı elementlərə müraciət edə bilərik.
    private lateinit var binding: ActivitySplashBinding

    // onCreate metodu, activity yaradıldığında ilk dəfə çağırılır
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fullscreen modunu təmin edən bir funksiya, bəzi cihazlarda sistem barlarının gizlədilməsini təmin edə bilər
        enableEdgeToEdge()
        // ActivitySplashBinding vasitəsilə XML resursları ilə bağlı görünüşləri inflyasiya (inflate) edir və kök görünüşü təyin edir.
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sistem barları üçün inset listener əlavə edilir ki, sistem barlarının yerləşdirilməsini idarə edək.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Sistem barlarının yerini alır.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Görünüşün kənarlarına doldurma əlavə edir.
            insets // İnsetləri geri qaytarır.
        }

        // Logo üçün döndərmə animasiyasını yükləyir
        var logoDondurAnimation = AnimationUtils.loadAnimation(this, R.anim.logodondur)

        // Start button üçün animasiyanı yükləyir
        var buttonSplash = AnimationUtils.loadAnimation(this, R.anim.buttonsplash)

        // Logo üçün animasiyanı tətbiq edir
        binding.imgLogo.animation = logoDondurAnimation

        // Start button üçün animasiyanı tətbiq edir
        binding.startButton.animation = buttonSplash

        // Start button üçün klik dinləyici əlavə edir
        binding.startButton.setOnClickListener {
            // Geri sayım başlatmaq üçün CountDownTimer obyektini yaradılır
            object : CountDownTimer(1000, 1000) {
                override fun onTick(p0: Long) {
                    // Geri sayım zamanı heç bir iş görmür
                }

                override fun onFinish() {
                    // Geri sayım bitdikdən sonra MainActivity-yə keçid üçün Intent yaradılır
                    var intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent) // Intent başlatılır
                }
            }.start() // Timer işə salınır
        }
    }
}
