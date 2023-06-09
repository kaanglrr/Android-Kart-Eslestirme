package com.example.myapplication.viewmodel

import com.example.myapplication.CardlarDataRepo
import com.example.myapplication.model.Card
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Collections.shuffle

class TekOyuncuViewModel2 : ViewModel() {

    var cardlistesi = MutableLiveData<MutableList<Card>>()
    var tıklanmalistesi = MutableLiveData<List<Card>>()
    var cardrepo = CardlarDataRepo()
    private val cards: MutableLiveData<MutableList<Card>> by lazy {
        MutableLiveData<MutableList<Card>>()
    }

    init {
        CardlariYuke()
        cardlistesi = cardrepo.CardlariGetir()
        println("kart listesi : "+ cardlistesi.value)
    }

    fun loadEmojis() {
        cards.value = mutableListOf(
            Card(1,cardlistesi.value!![0].card_karakteri,10,cardlistesi.value!![0].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(2,cardlistesi.value!![0].card_karakteri,10,cardlistesi.value!![0].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(3,cardlistesi.value!![1].card_karakteri,10,cardlistesi.value!![1].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(4,cardlistesi.value!![1].card_karakteri,10,cardlistesi.value!![1].card_ev,cardlistesi.value!![0].card_ev_puan,true,false)

        )
    }

    fun loadEmojis4() {
        cards.value = mutableListOf(
            Card(1,cardlistesi.value!![0].card_karakteri,cardlistesi.value!![0].card_karakter_puan,cardlistesi.value!![0].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(2,cardlistesi.value!![0].card_karakteri,cardlistesi.value!![0].card_karakter_puan,cardlistesi.value!![0].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(3,cardlistesi.value!![1].card_karakteri,cardlistesi.value!![1].card_karakter_puan,cardlistesi.value!![1].card_ev,cardlistesi.value!![1].card_ev_puan,true,false),
            Card(4,cardlistesi.value!![1].card_karakteri,cardlistesi.value!![1].card_karakter_puan,cardlistesi.value!![1].card_ev,cardlistesi.value!![1].card_ev_puan,true,false),
            Card(5,cardlistesi.value!![2].card_karakteri,cardlistesi.value!![2].card_karakter_puan,cardlistesi.value!![2].card_ev,cardlistesi.value!![2].card_ev_puan,true,false),
            Card(6,cardlistesi.value!![2].card_karakteri,cardlistesi.value!![2].card_karakter_puan,cardlistesi.value!![2].card_ev,cardlistesi.value!![2].card_ev_puan,true,false),
            Card(7,cardlistesi.value!![3].card_karakteri,cardlistesi.value!![3].card_karakter_puan,cardlistesi.value!![3].card_ev,cardlistesi.value!![3].card_ev_puan,true,false),
            Card(8,cardlistesi.value!![3].card_karakteri,cardlistesi.value!![3].card_karakter_puan,cardlistesi.value!![3].card_ev,cardlistesi.value!![3].card_ev_puan,true,false),
            Card(9,cardlistesi.value!![4].card_karakteri,cardlistesi.value!![4].card_karakter_puan,cardlistesi.value!![4].card_ev,cardlistesi.value!![4].card_ev_puan,true,false),
            Card(10,cardlistesi.value!![4].card_karakteri,cardlistesi.value!![4].card_karakter_puan,cardlistesi.value!![4].card_ev,cardlistesi.value!![4].card_ev_puan,true,false),
            Card(11,cardlistesi.value!![5].card_karakteri,cardlistesi.value!![5].card_karakter_puan,cardlistesi.value!![5].card_ev,cardlistesi.value!![5].card_ev_puan,true,false),
            Card(12,cardlistesi.value!![5].card_karakteri,cardlistesi.value!![5].card_karakter_puan,cardlistesi.value!![5].card_ev,cardlistesi.value!![5].card_ev_puan,true,false),
            Card(13,cardlistesi.value!![6].card_karakteri,cardlistesi.value!![6].card_karakter_puan,cardlistesi.value!![6].card_ev,cardlistesi.value!![6].card_ev_puan,true,false),
            Card(14,cardlistesi.value!![6].card_karakteri,cardlistesi.value!![6].card_karakter_puan,cardlistesi.value!![6].card_ev,cardlistesi.value!![6].card_ev_puan,true,false),
            Card(15,cardlistesi.value!![7].card_karakteri,cardlistesi.value!![7].card_karakter_puan,cardlistesi.value!![7].card_ev,cardlistesi.value!![7].card_ev_puan,true,false),
            Card(16,cardlistesi.value!![7].card_karakteri,cardlistesi.value!![7].card_karakter_puan,cardlistesi.value!![7].card_ev,cardlistesi.value!![7].card_ev_puan,true,false),
        )
    }

    fun loadEmojis6() {
        cards.value = mutableListOf(
            Card(1,cardlistesi.value!![0].card_karakteri,10,cardlistesi.value!![0].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(2,cardlistesi.value!![0].card_karakteri,10,cardlistesi.value!![0].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(3,cardlistesi.value!![1].card_karakteri,10,cardlistesi.value!![1].card_ev,cardlistesi.value!![0].card_ev_puan,true,false),
            Card(4,cardlistesi.value!![1].card_karakteri,10,cardlistesi.value!![1].card_ev,cardlistesi.value!![0].card_ev_puan,true,false)

        )
    }

    fun CardlariYuke(){
        cardrepo.deneme()
    }

    fun getCards(): LiveData<MutableList<Card>> {
        return cards
    }

    fun updateShowVisibleCard(id: String) {
        val selects: List<Card>? = cards.value?.filter { it -> it.card_secildi_mi }
        val selectCount: Int = selects?.size ?: 0
        var charFind: String = "";
        if (selectCount >= 2) {
            val hasSameChar: Boolean = selects!!.get(0).card_karakteri == selects.get(1).card_karakteri
            if (hasSameChar) {
                charFind = selects.get(0).card_karakteri
            }
        }

        val list: MutableList<Card>? = cards.value?.map { it ->
            if (selectCount >= 2) {
                it.card_secildi_mi = false
            }

            if (it.card_karakteri == charFind) {
                it.card_acik_mi = false
            }

            if (it.card_id.toString() == id) {
                it.card_secildi_mi = true
            }

            it
        } as MutableList<Card>?

        val visibleCount: Int = list?.filter { it -> it.card_acik_mi }?.size ?: 0
        if (visibleCount <= 0) {
            CardlariYuke()
            return
        }

        cards.value?.removeAll { true }
        cards.value = list
    }
    fun KartEkle(kart_adi: String, kart_puan: String, kart_hane: String){
        val docData = hashMapOf(
            "card_ev" to kart_hane,
            "card_karakter_puan" to kart_puan,
            "card_karakteri" to kart_adi
        )
        FirebaseFirestore.getInstance().collection("kart").add(docData)
    }


    /* fun AcikCardlariYukle(){
         cardrepo.AcikCardlariAl()
     }


     fun Eslesme(puan: MutableState<Int>): Int {
         AcikCardlariYukle()
         tıklanmalistesi = cardrepo.AcikCardlariGetir()
         if(tıklanmalistesi.value!![0].card_karakteri == tıklanmalistesi.value!![1].card_karakteri){
             puan.value+=(2* (tıklanmalistesi.value!![0].card_karakter_puan!!)*(tıklanmalistesi.value!![1].card_ev_puan!!))/* * (kalan_sure/10)*/
             var id=tıklanmalistesi.value!![0].card_id
             KartlarıEslesmisYap(id!!)
         }
         if (tıklanmalistesi.value!![0].card_karakteri != tıklanmalistesi.value!![1].card_karakteri && tıklanmalistesi.value!![0].card_ev == tıklanmalistesi.value!![1].card_ev)
         {
             puan.value-=((tıklanmalistesi.value!![0].card_karakter_puan!!+tıklanmalistesi.value!![1].card_karakter_puan!!)/tıklanmalistesi.value!![0].card_ev_puan!!)/* * (gecen_sure/10)*/
             var id1=tıklanmalistesi.value!![0].card_id
             var id2=tıklanmalistesi.value!![1].card_id
             KartlarıKapaliYap(id1!!,id2!!)
         }
         if(tıklanmalistesi.value!![0].card_karakteri != tıklanmalistesi.value!![1].card_karakteri && tıklanmalistesi.value!![0].card_ev != tıklanmalistesi.value!![1].card_ev)
         {
             puan.value-=((tıklanmalistesi.value!![0].card_karakter_puan!!+tıklanmalistesi.value!![1].card_karakter_puan!!)/2)*((tıklanmalistesi.value!![0].card_ev_puan!!*tıklanmalistesi.value!![1].card_ev_puan!!))/* * (gecen_sure/10)*/
             var id1=tıklanmalistesi.value!![0].card_id
             var id2=tıklanmalistesi.value!![1].card_id
             KartlarıKapaliYap(id1!!,id2!!)
         }
         return puan.value

     }*/

    /*fun KartlarıEslesmisYap(id:Int){
        for(i in 0..7){
            if(cardlistesi.value!![i].card_id==id){
                cardlistesi.value!![i].card_eslesti_mi=true
            }
        }
    }

    fun KartlarıKapaliYap(id1:Int,id2:Int){
        for(i in 0..7){
            if(cardlistesi.value!![i].card_id==id1 || cardlistesi.value!![i].card_id==id2 ){
                cardlistesi.value!![i].card_acik_mi=false
            }
        }
    }*/

}