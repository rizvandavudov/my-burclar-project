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

// 'DetayActivity' sinifi 'AppCompatActivity'-dən miras alır, yəni bir fəaliyyət (activity) sinifidir
class DetayActivity : AppCompatActivity() {

    // View Binding obyekti, bu activity-dəki görünüş komponentlərinə rahatlıqla erişim təmin edir
    private lateinit var binding: ActivityDetayBinding

    // 'onCreate' metodu, activity yaradıldığında ilk dəfə çağırılır
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding istifadə edilir, layout resurslarını şişirdir (inflate) və kök görünüşü təyin edir
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fullscreen modunu təmin edən bir funksiya, bəzi cihazlarda sistem barlarının gizlədilməsini təmin edə bilər
        enableEdgeToEdge()

        // Sistem barları üçün insert listener əlavə edilir ki, sistem barlarının yerləşdirilməsini idarə edək.
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Sistem barlarının yerini alır.
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            ) // Görünüşün kənarlarına doldurma əlavə edir.
            insets // İnsetləri geri qaytarır.
        }

        // Intentdən məlumatları alır, null yoxlaması ilə birlikdə
        val position = intent.extras?.getInt("position") ?: 0 // 'position' dəyəri, default olaraq 0
        val tumBurcBilgileri =
            intent.extras?.getSerializable("tumBurcBilgileri") as? ArrayList<Burc>
                ?: arrayListOf() // Bürc məlumatlarını alır, default olaraq boş siyahı

        // Bürcün xüsusiyyətlərini göstərən TextView-ə dəyər təyin edir
        binding.tvBurcOzelikeri.text = tumBurcBilgileri[position].burcGenelOzellikleri

        // Bürcün şəkilini göstərən ImageView-ə resurs təyin edir
        binding.header.setImageResource(tumBurcBilgileri[position].burcBuyukResim)

        // AppBar və Toolbar-ı tənzimləyir
        setSupportActionBar(binding.anim1toolbar) // Toolbarı AppBar olaraq təyin edir
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true) // Geri dönmə düyməsini göstərir
        binding.collapsingToolbar.title =
            tumBurcBilgileri[position].burcAdi // Toolbar başlığını bürc adını təyin edir

        // Resurslardan Bitmap yaradır və rəngləri çıxarmaq üçün istifadə edir
        var bitmap =
            BitmapFactory.decodeResource(resources, tumBurcBilgileri[position].burcBuyukResim)

        // 'Palette' obyektindən istifadə edərək bitmapdən rəng çıxarır
        Palette.from(bitmap).generate(object : PaletteAsyncListener {
            override fun onGenerated(palette: Palette?) {
                // Əsas rəng (vibrant color) əldə edilir
                var color = palette?.getVibrantColor(androidx.appcompat.R.attr.color)
                // 'CollapsingToolbarLayout'-ın rəngini təyin edir
                binding.collapsingToolbar.setContentScrimColor(color!!)
                // Status barın rəngini təyin edir
                window.statusBarColor = color
            }
        })
    }

    // Geri düyməsinə basıldığında çağırılan metod
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Geri basma əməliyyatını işlədir
        return super.onSupportNavigateUp() // Əsas sinifin geri dönmə davranışını geri qaytarır
    }
}
