
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.ComposeDenemeTheme
import com.example.myapplication.viewmodel.AuthViewModel
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.TekOyuncuViewModel2

@Composable
fun KartEkle() {
    val tf_1 = remember { mutableStateOf("") }//text field 1
    val tf_2 = remember { mutableStateOf("") }//text field 2
    val eski_sifre = remember { mutableStateOf("") }
    val yeni_sifre = remember { mutableStateOf("") }
    val tf_3 = remember { mutableStateOf("") }//text field 2
    val username = remember { mutableStateOf("") }
    val viewModel : TekOyuncuViewModel2 = viewModel()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = tf_3.value,
            onValueChange = { tf_3.value = it },
            label = { Text(text = "kart adı :") },


        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = tf_1.value,
            onValueChange = { tf_1.value = it },
            label = { Text(text = "kart puanı :") },


        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = tf_2.value,
            onValueChange = { tf_2.value = it },
            label = { Text(text = "kart hanesi :") },

        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            eski_sifre.value = tf_1.value
            yeni_sifre.value = tf_2.value
            username.value = tf_3.value
            viewModel.KartEkle(username.value,eski_sifre.value,yeni_sifre.value)
            Log.e("sifre guncelle","Sifre Guncelleme İslemi Tamamlandi")
        }) {
            Text(text = "Sifreyi Guncelle")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SifreSayfasiPreview() {
    ComposeDenemeTheme {
        KartEkle()
    }
}
