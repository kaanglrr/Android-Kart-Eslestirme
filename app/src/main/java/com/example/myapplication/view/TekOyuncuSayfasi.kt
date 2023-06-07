@file:OptIn(ExperimentalFoundationApi::class)

package com.berkaykazkilinc.composedeneme.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.model.Card
import com.example.myapplication.ui.theme.ComposeDenemeTheme
import com.example.myapplication.viewmodel.TekOyuncuViewModel2
import kotlinx.coroutines.delay


@Composable
fun TekOyuncu(sayi:Int,viewModel: TekOyuncuViewModel2) {

    val puan = remember { mutableStateOf(0) }
    val setView = remember { mutableStateOf("")}
    var eskicardListesi = viewModel.cardlistesi
    var cardListesi = mutableListOf<Card>()
    for (i in 0..sayi-1){
        cardListesi.add(eskicardListesi.value!!.get(i))
        cardListesi.add(eskicardListesi.value!!.get(i))
    }
    cardListesi.apply { shuffle() }
    val cards: List<Card> by viewModel.getCards().observeAsState(listOf())
    println("kartlar"+ cards)
    var state by remember {
        mutableStateOf(0)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 10.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly
    )
    {

        Text(
            text = "Puan: ${puan.value}",
            color = Color.Blue,
            fontSize = 31.sp
        )

        Text(
            text = "Sayac: ${state}",
            color = Color.Red,
            fontSize = 31.sp
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val kart_sayisi = remember { mutableStateOf(6) }
        kart_sayisi.value=sayi
        var tıklanan: String = ""

        LazyVerticalGrid(cells = GridCells.Fixed(kart_sayisi.value), content = {
            items(kart_sayisi.value*kart_sayisi.value){ i->

                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(all = 10.dp),
                    elevation = 20.dp,
                )
                {
                    Row(
                        modifier = Modifier.clickable {
                            if (cards[i].card_acik_mi){
                                viewModel.updateShowVisibleCard(cards[i].card_id.toString())
                            }
                        },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically


                    ) {
                        if(cards[i].card_secildi_mi){
                            //Text(text = "${cards[i].card_karakteri}")
                            var b64 = "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"
                            val imageBytes = Base64.decode(b64, 0)
                            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                            val option = BitmapFactory.Options()
                            option.inPreferredConfig = Bitmap.Config.ARGB_8888
                            val bitmap = image.asImageBitmap()
                            Image(bitmap = bitmap, contentDescription = "deneme")
                        }


                    }
                }
            }
        })




    }

    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            state++
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun TekOyuncuPreview() {
    ComposeDenemeTheme {
        TekOyuncu(2)
    }
}

 */