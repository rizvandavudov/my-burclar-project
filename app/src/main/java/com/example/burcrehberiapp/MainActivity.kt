package com.example.burcrehberiapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.burcrehberiapp.databinding.ActivityMainBinding

// MainActivity adlı fəaliyyət (activity) sinifi 'AppCompatActivity'-dən miras alır
class MainActivity : AppCompatActivity() {

    // ActivityMainBinding obyektini yaratmaq üçün dəyişən. Bu obyekt vasitəsilə XML faylındakı elementlərə müraciət edə bilərik.
    private lateinit var binding: ActivityMainBinding

    // Bürclərin məlumatlarını saxlayan ArrayList yaratmaq üçün dəyişən
    lateinit var tumBurcBilgileri: ArrayList<Burc>

    // onCreate metodu, activity yaradıldığında ilk dəfə çağırılır
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // enableEdgeToEdge() metodu ekranın kənarlarına qədər uzanan bir interfeys yaradır, sistem barlarını gizlətməyə kömək edə bilər.
        enableEdgeToEdge()

        // ActivityMainBinding vasitəsilə XML resursları ilə bağlı görünüşləri inflyasiya (inflate) edir və kök görünüşü təyin edir.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sistem barları üçün inset listener əlavə edilir ki, sistem barlarının yerləşdirilməsini idarə edək.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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

        // Status barın rəngini təyin edir ki, dizayna uyğun olsun.
        window.statusBarColor = resources.getColor(R.color.statusBarColor)

        // Bürclərin məlumatlarını hazırlayan funksiyanı çağırır ki, məlumatları adapterə təyin edə bilək.
        veriKaynagiHazrila()

        // 'BurclarBaseAdapter' adlı xüsusi adapter yaradılır və 'ListView'-a təyin edilir.
        var myBaseAdapter = BurclarBaseAdapter(this, tumBurcBilgileri)
        binding.listBurclar.adapter = myBaseAdapter

        // Hər bir bürc elementinə klik edildiyində işlədiləcək dinləyici əlavə olunur.
        binding.listBurclar.setOnItemClickListener { parent, view, position, id ->
            // Bürc detalları üçün yeni bir intent yaradılır.
            var intent = Intent(this@MainActivity, DetayActivity::class.java)
            intent.putExtra("position", position) // Kliklənən bürcün mövqeyini əlavə edir.
            intent.putExtra(
                "tumBurcBilgileri",
                tumBurcBilgileri
            ) // Bütün bürc məlumatlarını əlavə edir.

            // DetayActivity başlatılır.
            startActivity(intent)
        }
    }

    // Bürclərin məlumatlarını hazırlayan funksiya
    private fun veriKaynagiHazrila() {
        // Bürc məlumatlarını saxlayan ArrayList yaradılır.
        tumBurcBilgileri = ArrayList<Burc>(12)

        // Bürclərin adlarını, tarixlərini və digər xüsusiyyətlərini resurslardan alır.
        var burclar = resources.getStringArray(R.array.burclar)
        var burcTarihleri = resources.getStringArray(R.array.burcTarix)
        var burcResimleri = arrayOf(
            R.drawable.qoc,
            R.drawable.buga,
            R.drawable.ekizler,
            R.drawable.xerceng,
            R.drawable.sir,
            R.drawable.qiz,
            R.drawable.terezi,
            R.drawable.eqreb,
            R.drawable.oxatan,
            R.drawable.oglaq,
            R.drawable.dolca,
            R.drawable.baliq
        )
        var burcBoyukResimler = arrayOf(
            R.drawable.qoc_burcu,
            R.drawable.buga_burcu,
            R.drawable.ekizler_burcu,
            R.drawable.xerceng_burcu,
            R.drawable.sir_burcu,
            R.drawable.qiz_burcu,
            R.drawable.terezi_burcu,
            R.drawable.eqreb_burcu,
            R.drawable.oxatan_burcu,
            R.drawable.oglaq_burcu,
            R.drawable.dolca_burcu,
            R.drawable.baliqlar_burcu
        )
        var burcGenelOzellikler = resources.getStringArray(R.array.burcGenelOzellikleri)

        // Bürc məlumatları ArrayList-ə əlavə edilir.
        for (i in 0..11) {
            var arraylistAtanacaqBurc = Burc(
                burclar[i],
                burcTarihleri[i],
                burcResimleri[i],
                burcBoyukResimler[i],
                burcGenelOzellikler[i]
            )
            tumBurcBilgileri.add(arraylistAtanacaqBurc) // Yeni bürc obyekti ArrayList-ə əlavə edilir.
        }
    }
}
