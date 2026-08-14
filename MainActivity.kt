package com.dnbradio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DNBRadioApp()
        }
    }
}

@Composable
fun DNBRadioApp() {

    var tocando by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "DNBRADIO",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = if (tocando) "▶ Rádio tocando" else "⏸ Rádio parada"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                tocando = !tocando
            }
        ) {
            Text(
                text = if (tocando) "PARAR" else "OUVIR RÁDIO"
            )
        }
    }
}
