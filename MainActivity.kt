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
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class MainActivity : ComponentActivity() {

    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        player = ExoPlayer.Builder(this).build()

        val mediaItem = MediaItem.fromUri(
            "https://azura.dnbradio.com/listen/dnbradio/dnbradio_main.mp3"
        )

        player?.setMediaItem(mediaItem)
        player?.prepare()

        setContent {
            DNBRadioApp(
                onPlay = {
                    player?.play()
                },
                onStop = {
                    player?.pause()
                }
            )
        }
    }

    override fun onDestroy() {
        player?.release()
        player = null
        super.onDestroy()
    }
}

@Composable
fun DNBRadioApp(
    onPlay: () -> Unit,
    onStop: () -> Unit
) {

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

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Drum & Bass 24/7"
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = if (tocando)
                "▶ RÁDIO TOCANDO"
            else
                "⏸ RÁDIO PARADA"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (tocando) {
                    onStop()
                } else {
                    onPlay()
                }

                tocando = !tocando
            }
        ) {
            Text(
                text = if (tocando)
                    "PARAR"
                else
                    "OUVIR RÁDIO"
            )
        }
    }
}
