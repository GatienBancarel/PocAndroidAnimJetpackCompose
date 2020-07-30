package com.example.pocandroidanimjetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.animation.InterruptionHandling
import androidx.animation.transitionDefinition
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.animation.ColorPropKey
import androidx.ui.animation.DpPropKey
import androidx.ui.animation.Transition
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.graphics.drawscope.rotate
import androidx.ui.layout.*
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import com.example.pocandroidanimjetpackcompose.ui.POCAndroidAnimJetpackComposeTheme
import com.example.pocandroidanimjetpackcompose.ui.purple200

class ActivityTransitionKeyframes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCAndroidAnimJetpackComposeTheme {
                TransitionKeyframes()
            }
        }
    }

    enum class FewAnimation {
        First,Second
    }

    val widthKey = DpPropKey()
    val heightKey = DpPropKey()

    val definitionFewAnimations = transitionDefinition {
        state(FewAnimation.First) {
            this[widthKey] = 100.dp
            this[heightKey] = 100.dp
        }
        state(FewAnimation.Second) {
            this[widthKey] = 100.dp
            this[heightKey] = 100.dp
        }
        transition(fromState = FewAnimation.First, toState = FewAnimation.Second) {
            heightKey using keyframes {
                duration = 4000
                100.dp at 1000
                200.dp at 2000
                200.dp at 3000
                100.dp at 4000
            }
            widthKey using keyframes {
                duration = 4000
                200.dp at 1000
                200.dp at 2000
                100.dp at 3000
                100.dp at 4000
            }
            interruptionHandling = InterruptionHandling.UNINTERRUPTIBLE
        }
    }

    @Composable
    fun TransitionKeyframes() {
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
                Transition(definition = definitionFewAnimations,
                    initState = FewAnimation.First,
                    toState = if (!animation.value) {
                        FewAnimation.First
                    } else {
                        FewAnimation.Second
                    }
                ) { state ->
                    Canvas(
                        modifier = Modifier
                            .preferredSize(state[widthKey], state[heightKey])
                            .clickable(onClick = { animation.value = true })
                    ) {
                        drawRect(color = purple200)
                    }
                }
            }
        }
    }
}
