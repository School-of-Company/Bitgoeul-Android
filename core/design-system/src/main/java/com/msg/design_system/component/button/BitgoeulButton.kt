package com.msg.design_system.component.button

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.Vibration
import com.msg.design_system.util.vibe

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BitgoeulButton(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.Enable,
    onClick: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->

        val interactionSource = remember { MutableInteractionSource() }

        val isActiveDisableAnimationLeft = remember { mutableStateOf(false) }
        val isActiveDisableAnimationRight = remember { mutableStateOf(false) }

        val enabledState: (buttonState: ButtonState) -> Boolean = {
            when(it) {
                ButtonState.Enable -> true
                ButtonState.Disable -> false
            }
        }

        val context = LocalContext.current

        val disableAnimationLeftValue = animateFloatAsState(
            targetValue = if (isActiveDisableAnimationLeft.value) 0.2f else 0f,
            visibilityThreshold = 0.15f, label = "disableAnimationLeftValue"
        ) {
            if (it == 0.2f) {
                Log.d("Here", "Here")
                isActiveDisableAnimationLeft.value = false
            } else if (it == 0f) {
                isActiveDisableAnimationRight.value = true
            }
        }

        val disableAnimationRightValue = animateFloatAsState(
            targetValue = if (isActiveDisableAnimationRight.value) -0.2f else 0f,
            visibilityThreshold = 0.1f, label = "disableAnimationRightValue"
        ) {
            if (it == -0.2f) {
                isActiveDisableAnimationRight.value = false
            }
        }

        val onTouched: (isDisabled: Boolean) -> Unit = {
            if (!it) {
                isActiveDisableAnimationLeft.value = true
                context.vibe(Vibration.WARNING)
            }
        }

        Button(
            modifier = modifier
                .pointerInteropFilter {
                    when(it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            onTouched(enabledState(state))
                            true
                        }
                        else -> {
                            true
                        }
                    }
                }.offset(
                    (disableAnimationLeftValue.value * 10 + disableAnimationRightValue.value * 10).dp,
                    0.dp
                ),
            interactionSource = interactionSource,
            onClick = onClick,
            enabled = enabledState(state),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.P5,
                contentColor = colors.WHITE,
                disabledContainerColor = colors.G1,
                disabledContentColor = colors.G2,
            ),
            contentPadding = PaddingValues(vertical = 12.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = text,
                style = typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun BitgoeulButtonPre() {
    Column(
        modifier = Modifier.height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        BitgoeulButton(
            text = "Test",
            modifier = Modifier
                .width(319.dp)
                .height(52.dp),
            state = ButtonState.Enable
        ) {

        }
        BitgoeulButton(
            text = "Test2",
            modifier = Modifier
                .width(319.dp)
                .height(52.dp),
            state = ButtonState.Disable
        ) {

        }

    }
}
