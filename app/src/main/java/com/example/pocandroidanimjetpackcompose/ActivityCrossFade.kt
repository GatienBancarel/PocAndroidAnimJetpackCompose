package com.example.pocandroidanimjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.animation.animate
import androidx.ui.core.*
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Surface
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme
import com.example.pocandroidanimjetpackcompose.ui.purple200

class ActivityCrossFade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                CrossFade()
            }
        }
    }

    @Composable
    fun CrossFade () {
        val animation = state { "A" }

        Surface(color = Color.White, modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(bottom = 100.dp, start = 16.dp, top = 16.dp, end = 16.dp),
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                Button(onClick = { animation.value = "B"}) {
                    Text(text = "go to state 1")
                }
                Button(onClick = { animation.value = "C"}, modifier = Modifier.padding(top = 16.dp)) {
                    Text(text = "go to state 2")
                }

                val myImage = imageResource(id = R.drawable.header)
                val myImage2 = imageResource(id = R.drawable.nature)
                val imageModifier = Modifier.padding(top = 32.dp).preferredHeightIn(maxHeight = 180.dp).fillMaxWidth().clip(shape = RoundedCornerShape(4.dp))
                
                Crossfade(current = animation.value) { screen ->
                    when (screen) {
                        "B" -> Image(asset = myImage, contentScale =  ContentScale.Crop, modifier = imageModifier)
                        "C" -> Image(asset = myImage2, contentScale =  ContentScale.Crop, modifier = imageModifier)
                    }
                }
            }
        }
    }
}