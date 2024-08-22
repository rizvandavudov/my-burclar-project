package com.example.burcrehberiapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class BurclarArrayAdapter(
    var gelenContext: Context,
    resource: Int,
    textViewResorcedId: Int,
    var burcAdlari: Array<String>,
    var burcTarihleri: Array<String>,
    var burcResimleri: Array<Int>
) : ArrayAdapter<String>(gelenContext, resource, textViewResorcedId, burcAdlari) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tek_satir_view = convertView
        var viewHolder: ViewHolder? = null

        // Görünüşün yenidən istifadəsi və ya yeni görünüşün yaradılması
        if (tek_satir_view == null) {
            val inflater = LayoutInflater.from(gelenContext)
            tek_satir_view = inflater.inflate(R.layout.tek_satir, parent, false)


            viewHolder = ViewHolder(tek_satir_view)
            tek_satir_view.tag = viewHolder

            Log.e("BurclarArrayAdapter", "INFLATION YAPILDI: ${burcAdlari[position]}")

        } else {
            viewHolder = tek_satir_view.getTag() as ViewHolder

        }

        // Məlumatların paylanması
        viewHolder.burcResim.setImageResource(burcResimleri[position])
        viewHolder.burcAdi.text = (burcAdlari[position])
        viewHolder.burcTarih.text = (burcTarihleri[position])

        return tek_satir_view!!

    }

    class ViewHolder(tek_satir_view: View?) {
        val burcResim: ImageView
        val burcAdi: TextView
        val burcTarih: TextView

        init {
            this.burcResim = tek_satir_view!!.findViewById(R.id.imgBurcSembol)

            this.burcAdi = tek_satir_view.findViewById(R.id.tbBurcAdi)

            this.burcTarih = tek_satir_view.findViewById(R.id.tvBurcTarixi)
        }
    }
}
