package com.example.burcrehberiapp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.PaletteAsyncListener
import com.example.burcrehberiapp.databinding.ActivityDetayBinding

class DetayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding istifadə edin
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // enableEdgeToEdge() metodunun istifadəsi (əgər lazım olarsa)
        enableEdgeToEdge()

        // System bar insets üçün listener
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Null təhlükəsizliyi və casting ilə intentdən məlumat alma
        val position = intent.extras?.getInt("position") ?: 0
        val tumBurcBilgileri =
            intent.extras?.getSerializable("tumBurcBilgileri") as? ArrayList<Burc> ?: arrayListOf()

        // TextView ve ImageView-i View Binding vasitəsilə istifadə edin
        binding.tvBurcOzelikeri.text = tumBurcBilgileri[position].burcGenelOzellikleri
        binding.header.setImageResource(tumBurcBilgileri[position].burcBuyukResim)

        // AppBar və Toolbar tənzimləməsi
        setSupportActionBar(binding.anim1toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbar.title = tumBurcBilgileri[position].burcAdi
        var bitmap =
            BitmapFactory.decodeResource(resources, tumBurcBilgileri[position].burcBuyukResim)
        Palette.from(bitmap).generate(object : PaletteAsyncListener {
            override fun onGenerated(palette: Palette?) {
                var color = palette?.getVibrantColor(androidx.appcompat.R.attr.color)
                binding.collapsingToolbar.setContentScrimColor(color!!)
                window.statusBarColor = color
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
