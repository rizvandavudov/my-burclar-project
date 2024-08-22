package com.example.burcrehberiapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.burcrehberiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


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
        //1. Resorsda yaratdigim burclar arayini activity maine getirdim
        var burclar = resources.getStringArray(R.array.burclar)
        var burcTarihleri = resources.getStringArray(R.array.burcTarix)

        // 2. Burda sekilleri cagiriram
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

        // 4. Burda yaraydigimiz Array adapteri cagiririq ve conustruktorlarina burclara aid olan her seyi gonderirik
        /*  var myAdapter = BurclarArrayAdapter(
              this,
              R.layout.tek_satir,
              R.id.tbBurcAdi,
              burclar,
              burcTarihleri,
              burcResimleri
          )

          // 5. Burda ise yaratdigimiz obyekti myAdapteri activitideki listBurclara gonderirik
          binding.listBurclar.adapter = myAdapter
  */
        var myBaseAdapter = BurclarBaseAdapter(this)
        binding.listBurclar.adapter=myBaseAdapter
    }
}

