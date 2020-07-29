package com.example.pocandroidanimjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.animation.FloatPropKey
import androidx.animation.InterruptionHandling
import androidx.animation.transitionDefinition
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.DpPropKey
import androidx.ui.animation.Transition
import androidx.ui.animation.animate
import androidx.ui.core.*
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.rotate
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme
import com.example.pocandroidanimjetpackcompose.ui.purple200

class ActivityTranslation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                Translation()
            }
        }
    }

    enum class TranslationState {
        First,Second
    }

    val heightKey = FloatPropKey()

    val definitionTranslation = transitionDefinition {
        state(TranslationState.First) {
            this[heightKey] = 0f
        }
        state(TranslationState.Second) {
            this[heightKey] = -1000f
        }
        transition(fromState = TranslationState.First, toState = TranslationState.Second) {
            heightKey using keyframes {
                duration = 2000
            }
            interruptionHandling = InterruptionHandling.UNINTERRUPTIBLE
        }
    }

    @Composable
    fun Translation () {
        val animation = state { false }

        Surface(color = Color.White, modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(bottom = 100.dp, start = 16.dp, top = 16.dp, end = 16.dp),
                horizontalGravity = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Transition(definition = definitionTranslation,
                    initState = TranslationState.First,
                    toState = if (!animation.value) {
                        TranslationState.First
                    } else {
                        TranslationState.Second
                    }
                ) { state ->
                    Canvas(
                        modifier = Modifier.drawLayer(translationY = state[heightKey]).preferredSize(100.dp).clickable(onClick = { animation.value = animation.value != true })
                    ) {
                        drawRect(color = purple200)
                    }
                }
            }
        }
    }
}