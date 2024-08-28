package com.example.burcrehberiapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

// 'BurclarBaseAdapter' adlı xüsusi adapter sinifi yaradılır və 'BaseAdapter' sinifindən miras alır
class BurclarBaseAdapter(context: Context, tumBurcBilgileri: ArrayList<Burc>) : BaseAdapter() {

    // Adapter üçün lazım olan məlumatları saxlayan dəyişənlər yaradılır
    var tumBurclar: ArrayList<Burc>
    var context: Context

    // 'init' bloku konstruktor kimi işləyir və 'context' və 'tumBurcBilgileri' dəyişənlərini inisializasiya edir
    init {
        this.tumBurclar = tumBurcBilgileri // Bürc məlumatlarının siyahısı
        this.context = context // Mövcud kontekst
    }

    // Adapter üçün hər bir elementin görünüşünü təmin edən metod
    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View? {
        var tek_satir_view1 = contentView // Mövcud görünüşü tutan dəyişən
        var viewHolder: ViewHolder2 // 'ViewHolder' obyekti referansları saxlayacaq

        // 'tek_satir_view1' boşdursa, yeni bir görünüş yaradılır
        if (tek_satir_view1 == null) {
            // 'LayoutInflater' istifadə edilərək yeni bir görünüş yaradılır
            var inflater = LayoutInflater.from(context)
            tek_satir_view1 = inflater.inflate(R.layout.tek_satir, parent, false)

            // 'ViewHolder' obyektini yaradaraq görünüş komponentlərini yadda saxlayırıq
            viewHolder = ViewHolder2(tek_satir_view1)
            tek_satir_view1.tag = viewHolder // 'ViewHolder' obyektini görünüşə əlavə edirik
        } else {
            // Əgər 'tek_satir_view1' boş deyilsə, mövcud 'ViewHolder' obyektini alırıq
            viewHolder = tek_satir_view1.getTag() as ViewHolder2
        }

        // Bürc məlumatlarını 'ViewHolder' istifadə edərək təyin edirik
        viewHolder.burcResim.setImageResource(tumBurclar.get(position).burcSembol) // Bürc simvolunu təyin edir
        viewHolder.burcAdi.setText(tumBurclar.get(position).burcAdi) // Bürc adını təyin edir
        viewHolder.burcTarih.setText(tumBurclar.get(position).burcTarix) // Bürc tarixini təyin edir

        return tek_satir_view1 // Yaradılmış və ya yenilənmiş görünüşü qaytarırıq
    }

    // Adapterdə mövcud olan hər bir elementin obyektini qaytarır
    override fun getItem(position: Int): Any {
        return tumBurclar.get(position)
    }

    // Adapterdəki hər bir elementin ID-sini qaytarır (Bu vəziyyətdə hər zaman 0)
    override fun getItemId(position: Int): Long {
        return 0
    }

    // Adapterdə neçə element olduğunu qaytarır
    override fun getCount(): Int {
        return tumBurclar.size
    }
}

// 'ViewHolder2' adlı daxili sinif yaradılır və görünüşdəki komponentlər üçün referansları saxlayır
class ViewHolder2(tek_satir_view: View) {
    val burcResim: ImageView // Bürc simvolu üçün 'ImageView'
    val burcAdi: TextView // Bürc adı üçün 'TextView'
    val burcTarih: TextView // Bürc tarixi üçün 'TextView'

    // 'init' bloku 'ViewHolder2' sinifinin inisializasiyasını təmin edir
    init {
        // ID-sinə görə görünüşdəki komponentləri tapır və dəyişənlərə təyin edir
        this.burcResim = tek_satir_view.findViewById(R.id.imgBurcSembol)
        this.burcAdi = tek_satir_view.findViewById(R.id.tbBurcAdi)
        this.burcTarih = tek_satir_view.findViewById(R.id.tvBurcTarixi)
    }
}
