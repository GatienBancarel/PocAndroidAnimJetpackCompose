package com.example.pocandroidanimjetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            Column(modifier = Modifier.padding()) {
                Button("Animation Rotate", Intent(applicationContext, ActivityRotate::class.java))
                Button("Animation Scalling", Intent(applicationContext, ActivityScalling::class.java))
                Button("Few Animations", Intent(applicationContext, ActivityFewAnimations::class.java))
                Button("Animation Opacity", Intent(applicationContext, ActivityOpacity::class.java))
                Button("Animation CrossFade", Intent(applicationContext, ActivityCrossFade::class.java))
                Button("Animation Translation", Intent(applicationContext, ActivityTranslation::class.java))
                Button("Animation EvaluatorColor", Intent(applicationContext, ActivityEvaluatorColor::class.java))
            }
        }
    }

    @Composable
    fun Button(name: String, intent: Intent){
        Button(
            onClick = {startActivity(intent)},
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.Black,
            modifier = Modifier.fillMaxWidth().padding(top = 25.dp)
        ) {
            Text(text = name)
        }
    }
}






