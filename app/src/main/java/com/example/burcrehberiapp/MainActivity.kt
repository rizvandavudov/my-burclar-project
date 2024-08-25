package com.example.burcrehberiapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.burcrehberiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var tumBurcBilgileri: ArrayList<Burc>


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.statusBarColor=resources.getColor(R.color.statusBarColor)

        veriKaynagiHazrila()


        var myBaseAdapter = BurclarBaseAdapter(this,tumBurcBilgileri)
        binding.listBurclar.adapter = myBaseAdapter

        binding.listBurclar.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this@MainActivity,DetayActivity::class.java)
            intent.putExtra("position",position)
            intent.putExtra("tumBurcBilgileri",tumBurcBilgileri)

            startActivity(intent)


        }
    }

    private fun veriKaynagiHazrila() {
        tumBurcBilgileri = ArrayList<Burc>(12)
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
            R.drawable.baliqlar_burcu,
        )
         var burcGenelOzellikler=resources.getStringArray(R.array.burcGenelOzellikleri)
        for (i in 0..11) {

            var arraylistAtanacaqBurc = Burc(burclar[i], burcTarihleri[i], burcResimleri[i], burcBoyukResimler[i],burcGenelOzellikler[i])
            tumBurcBilgileri.add(arraylistAtanacaqBurc)
        }

    }

}

