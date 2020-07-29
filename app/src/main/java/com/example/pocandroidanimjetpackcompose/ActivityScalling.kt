package com.example.pocandroidanimjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.animate
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.rotate
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme
import com.example.pocandroidanimjetpackcompose.ui.purple200

class ActivityScalling : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                Scalling()
            }
        }
    }

    @Composable
    fun Scalling() {
        val animation = state { false }

        Surface(color = Color.White, modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(bottom = 100.dp, start = 16.dp, top = 16.dp, end = 16.dp),
                    horizontalGravity = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
            ) {
                val height : Dp = animate(if (animation.value) 200.dp else 100.dp)
                Canvas(modifier = Modifier.preferredSize(height).clickable(onClick = { animation.value = animation.value != true })) {
                    drawRect(color = purple200)
                }
            }
        }
    }
}