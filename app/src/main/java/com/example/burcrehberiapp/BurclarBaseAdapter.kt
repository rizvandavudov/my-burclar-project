package com.example.burcrehberiapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class BurclarBaseAdapter(context: Context) : BaseAdapter() {

    var tumBurclar: ArrayList<Burclar>
    var context: Context

    init {
        tumBurclar = ArrayList<Burclar>(12)
        this.context = context
        var burcAdlari = context.resources.getStringArray(R.array.burclar)
        var burcTarixleri = context.resources.getStringArray(R.array.burcTarix)
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
        for (i in 0..11) {

            var arraylistAtanacaqBurc = Burclar(burcAdlari[i], burcTarixleri[i], burcResimleri[i])
            tumBurclar.add(arraylistAtanacaqBurc)
        }
    }

    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View? {


        var tek_satir_view1 = contentView

        var viewHolder: ViewHolder2

        if (tek_satir_view1 == null) {

            var inflater = LayoutInflater.from(context)
            tek_satir_view1 = inflater.inflate(R.layout.tek_satir, parent, false)
            viewHolder = ViewHolder2(tek_satir_view1)
            tek_satir_view1.tag = viewHolder
        } else {
            viewHolder = tek_satir_view1.getTag() as ViewHolder2
        }

        viewHolder.burcResim.setImageResource(tumBurclar.get(position).burcSembol)
        viewHolder.burcAdi.setText(tumBurclar.get(position).burcAdi)
        viewHolder.burcTarih.setText(tumBurclar.get(position).burcTarix)

        return tek_satir_view1
    }

    override fun getItem(position: Int): Any {
        return tumBurclar.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return tumBurclar.size
    }


}

data class Burclar(var burcAdi: String, var burcTarix: String, var burcSembol: Int) {}

class ViewHolder2(tek_satir_view: View) {
    val burcResim: ImageView
    val burcAdi: TextView
    val burcTarih: TextView

    init {
        this.burcResim = tek_satir_view.findViewById(R.id.imgBurcSembol)

        this.burcAdi = tek_satir_view.findViewById(R.id.tbBurcAdi)

        this.burcTarih = tek_satir_view.findViewById(R.id.tvBurcTarixi)
    }
}