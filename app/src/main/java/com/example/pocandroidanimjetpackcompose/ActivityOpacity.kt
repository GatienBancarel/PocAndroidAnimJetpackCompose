package com.example.pocandroidanimjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.animate
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.core.setContent
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme
import com.example.pocandroidanimjetpackcompose.ui.purple200

class ActivityOpacity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                Opacity()
            }
        }
    }

    @Composable
    fun Opacity() {
        val animation = state { false }

        Surface(color = Color.White, modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(bottom = 100.dp, start = 16.dp, top = 16.dp, end = 16.dp),
                horizontalGravity = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                val opacity = animate(if (animation.value) 0.1f else 1f)
                Canvas(modifier = Modifier.drawOpacity(opacity).preferredSize(100.dp).clickable(onClick = { animation.value = animation.value != true })) {
                    drawRect(color = purple200)
                }
            }
        }
    }
}