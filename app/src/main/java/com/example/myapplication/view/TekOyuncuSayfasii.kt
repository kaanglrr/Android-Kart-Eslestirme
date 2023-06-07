package com.berkaykazkilinc.composedeneme.view

import android.provider.ContactsContract
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.sealed.AnnenState
import com.example.myapplication.sealed.DataState
import com.example.myapplication.sealed.DenemeState
import com.example.myapplication.ui.theme.ComposeDenemeTheme
import com.example.myapplication.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun TekOyuncuu(sayi:Int,viewModel: MainViewModel) {
    when (val result = viewModel.response.value) {
        is AnnenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is AnnenState.Success -> {
            var state by remember {
                mutableStateOf(0)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                val kart_sayisi = remember { mutableStateOf(6) }
                kart_sayisi.value = sayi


                if (kart_sayisi.value == 2) {
                    var sayac: Int = 0
                    for (j in 0..1) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            for (i in 0..1) {
                                Card(

                                    modifier = Modifier
                                        .size(120.dp)
                                        .padding(all = 10.dp),
                                    elevation = 20.dp,
                                )
                                {
                                    var indexx = sayac
                                    Row(
                                        modifier = Modifier.clickable {
                                                                      println("kartın indexi"+ indexx)
                                            viewModel.fetchDataFromFirebaseDenemee1(result.data?.get(0).matris?.get(indexx)?.tur.toString(),state.toString())
                                        },
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically


                                    ) {
                                        if (result.data.get(0).matris?.get(sayac)?.tur != null && result.data.get(0).matris!!.get(sayac).secim != null) {
                                            var string: String =
                                                result.data.get(0).matris!!.get(sayac).tur.toString()

                                            if (result.data.get(0).matris!!.get(sayac).secim == "true"){
                                                Text(text = "bakıldı!")
                                            }else{
                                                Text(text = string)

                                            }
                                        }
                                        val deneme = "adsad"
                                        sayac++

                                    }
                                }
                            }
                        }
                    }
                }

            }
            Column() {
                Text(text = state.toString())
            }
            LaunchedEffect(Unit) {
                while(true) {
                    delay(1000)
                    state++
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TekOyuncuuPreview() {
    ComposeDenemeTheme {
        //TekOyuncu(2)
    }
}