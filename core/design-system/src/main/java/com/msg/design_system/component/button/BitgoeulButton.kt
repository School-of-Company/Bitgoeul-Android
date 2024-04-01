package com.msg.design_system.component.button

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.msg.design_system.R
import com.msg.design_system.component.icon.MainColorSettingIcon
import com.msg.design_system.theme.BitgoeulAndroidTheme
import com.msg.design_system.util.Vibration
import com.msg.design_system.util.vibe

@Composable
fun BitgoeulButton(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.Enable,
    onClick: () -> Unit,
) {
    BitgoeulAndroidTheme { colors, typography ->

        val interactionSource = remember { MutableInteractionSource() }

//        val isActiveDisableAnimationLeft = remember { mutableStateOf(false) }
//        val isActiveDisableAnimationRight = remember { mutableStateOf(false) }

        val enabledState: (buttonState: ButtonState) -> Boolean = {
            when (it) {
                ButtonState.Enable -> true
                ButtonState.Disable -> false
            }
        }

//        val context = LocalContext.current

//        val disableAnimationLeftValue = animateFloatAsState(
//            targetValue = if (isActiveDisableAnimationLeft.value) 0.2f else 0f,
//            visibilityThreshold = 0.15f, label = "disableAnimationLeftValue"
//        ) {
//            if (it == 0.2f) {
//                Log.d("Here", "Here")
//                isActiveDisableAnimationLeft.value = false
//            } else if (it == 0f) {
//                isActiveDisableAnimationRight.value = true
//            }
//        }
//
//        val disableAnimationRightValue = animateFloatAsState(
//            targetValue = if (isActiveDisableAnimationRight.value) -0.2f else 0f,
//            visibilityThreshold = 0.1f, label = "disableAnimationRightValue"
//        ) {
//            if (it == -0.2f) {
//                isActiveDisableAnimationRight.value = false
//            }
//        }
//
//        val onTouched: (isDisabled: Boolean) -> Unit = {
//            if (!it) {
//                isActiveDisableAnimationLeft.value = true
//                context.vibe(Vibration.WARNING)
//            }
//        }

        Button(
            modifier = modifier,
            interactionSource = interactionSource,
            enabled = enabledState(state),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.P5,
                contentColor = colors.WHITE,
                disabledContainerColor = colors.G1,
                disabledContentColor = colors.G2,
            ),
            onClick = onClick,
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

@Composable
fun DetailSettingButton(
    modifier: Modifier = Modifier,
    type: String,
    onClick: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->
        OutlinedButton(
            border = BorderStroke(1.dp, colors.P5),
            modifier = modifier,
            onClick = onClick,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = colors.WHITE,
                contentColor = colors.P5,
            ),
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            MainColorSettingIcon()

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${type} 세부 설정",
                color = colors.P5,
                style = typography.bodyLarge
            )
        }
    }
}

@Composable
fun NegativeBitgoeulButton(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.Enable,
    onClick: () -> Unit
) {
    BitgoeulAndroidTheme { colors, typography ->

        val interactionSource = remember { MutableInteractionSource() }

        val enabledState: (buttonState: ButtonState) -> Boolean = {
            when (it) {
                ButtonState.Enable -> true
                ButtonState.Disable -> false
            }
        }

        Button(
            modifier = modifier,
            interactionSource = interactionSource,
            enabled = enabledState(state),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.E5,
                contentColor = colors.WHITE,
                disabledContainerColor = colors.G1,
                disabledContentColor = colors.G2,
            ),
            onClick = onClick,
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

@Composable
fun ApplicationDoneButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    BitgoeulAndroidTheme { colors, type ->
        OutlinedButton(
            modifier = modifier,
            onClick = onClick,
            border = BorderStroke(1.dp, color = colors.E5),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = colors.WHITE,
                contentColor = colors.E5,
            ),
            shape = RoundedCornerShape(8.dp),
        ) {

            Text(
                text = text,
                color = colors.E5,
                style = type.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun BitgoeulButtonPre() {
    Column(
        modifier = Modifier.height(300.dp),
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

        DetailSettingButton(
            modifier = Modifier
                .width(319.dp)
                .height(52.dp),
            onClick = {},
            type = "강의"
        )

        ApplicationDoneButton(
            modifier = Modifier
                .width(319.dp)
                .height(52.dp),
            onClick = {},
            text = "수강 신청 취소"
        )
    }
}
