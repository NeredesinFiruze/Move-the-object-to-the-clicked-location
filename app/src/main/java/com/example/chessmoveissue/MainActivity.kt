package com.example.chessmoveissue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.chessmoveissue.ui.theme.ChessMoveIssueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessMoveIssueTheme {

                val pictureSize = 80.dp
                val offset = remember { mutableStateOf(DpOffset(0.dp,0.dp)) }
                val offsetAnimatable by animateOffsetAsState(
                    targetValue = Offset(offset.value.x.value,offset.value.y.value),
                    animationSpec = tween(300)
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                offset.value = DpOffset(it.x.toDp(),it.y.toDp())
                            }
                        )
                    }
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .offset(
                                x = offsetAnimatable.x.dp - pictureSize / 2,
                                y = offsetAnimatable.y.dp - pictureSize / 2
                            //The expression '- pictureSize / 2' adjusts the object's offset from the top left corner to the center.
                            )
                            .size(pictureSize)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}