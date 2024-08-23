package com.example.burcrehberiapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class BurclarBaseAdapter(context: Context, tumBurcBilgileri: ArrayList<Burc>) : BaseAdapter() {

    var tumBurclar: ArrayList<Burc>
    var context: Context

    init {
      this.tumBurclar = tumBurcBilgileri
        this.context = context

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