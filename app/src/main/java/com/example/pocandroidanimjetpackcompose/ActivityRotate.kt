package com.example.pocandroidanimjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.animation.FastOutLinearInEasing
import androidx.animation.FloatPropKey
import androidx.animation.transitionDefinition
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.Transition
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.rotate
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme
import com.example.pocandroidanimjetpackcompose.ui.purple200

class ActivityRotate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                Rotate()
            }
        }
    }

    /////////////////////////////////////////////////Definition transition ////////////////////////////////////////////////////////////////////////
    enum class Rotation {
        First,Second
    }

    val rotation = FloatPropKey()

    val definitionRotation = transitionDefinition {
        state(Rotation.First) {
            this[rotation] = 0f
        }
        state(Rotation.Second) {
            this[rotation] = 360f
        }
        transition(fromState = Rotation.First, toState = Rotation.Second) {
            rotation using repeatable {
                animation = tween {
                    easing = FastOutLinearInEasing
                    duration = 2000
                }
                iterations = 1
            }
        }
    }

/////////////////////////////////////////////////////// Rotate Box //////////////////////////////////////////////////////////////////////////////

    @Composable
    fun Rotate() {
        val animation = state { false }

        Surface(color = Color.White, modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(bottom = 100.dp, start = 16.dp, top = 16.dp, end = 16.dp),
                    horizontalGravity = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
            ) {
                Transition(definition = definitionRotation,
                        initState = Rotation.First,
                        toState = if (!animation.value) {
                            Rotation.First
                        } else {
                            Rotation.Second
                        }
                ) { state ->
                    Canvas(
                            modifier = Modifier.preferredSize(100.dp).clickable(onClick = { animation.value = true })
                    ) {
                        rotate(state[rotation]) {
                            drawRect(color = purple200)
                        }
                    }
                }
            }
        }
    }
}
