package com.example.pocandroidanimjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.animation.InterruptionHandling
import androidx.animation.transitionDefinition
import androidx.compose.Composable
import androidx.ui.core.setContent
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme
import androidx.compose.state
import androidx.ui.animation.ColorPropKey
import androidx.ui.animation.DpPropKey
import androidx.ui.animation.Transition
import androidx.ui.animation.animate
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.clickable
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.rotate
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.purple200
import com.example.pocandroidanimjetpackcompose.ui.purple500
import com.example.pocandroidanimjetpackcompose.ui.purple700

class ActivityEvaluatorColor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                EvaluatorColor()
            }
        }
    }

    enum class State {
        First,Second
    }

    val colorKey = ColorPropKey()

    val definition = transitionDefinition {
        state(State.First) {
            this[colorKey] = purple200
        }
        state(State.Second) {
            this[colorKey] = purple500
        }
        transition(fromState = State.First, toState = State.Second) {
            colorKey using keyframes {
                duration = 2000
            }
            interruptionHandling = InterruptionHandling.UNINTERRUPTIBLE
        }
    }

    @Composable
    fun EvaluatorColor() {
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
                Transition(definition = definition,
                    initState = State.First,
                    toState = if (!animation.value) {
                        State.First
                    } else {
                        State.Second
                    }
                ) { state ->
                    Canvas(
                        modifier = Modifier
                            .preferredSize(100.dp)
                            .clickable(onClick = { animation.value = animation.value != true })
                    ) {
                            drawRect(color = state[colorKey])
                    }
                }
            }
        }
    }
}

/*
// Draw a seekbar-like composable that has a black background
// with a red square that moves along the 300.dp drag distance
val max = 300.dp
val min = 0.dp
// this is the  state we will update while dragging
val position = state { 0f }

// seekbar itself
Box(
modifier = Modifier
.preferredWidth(max)
.draggable(dragDirection = DragDirection.Horizontal) {delta ->
    val newValue = position.value + delta
    position.value = newValue.coerceIn(min, max)
    position.value
},
backgroundColor = Color.Black
) {
    Box(
        Modifier.offsetPx(x = position.value).preferredSize(50.dp),
        backgroundColor = Color.Red
    )
}
*/