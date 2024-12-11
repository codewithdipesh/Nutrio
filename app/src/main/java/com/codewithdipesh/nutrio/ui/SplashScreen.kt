package com.codewithdipesh.nutrio.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.codewithdipesh.core.R
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onAnimationEnd: () -> Unit
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("splash_animation.json")
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1
    )

    // Check if animation has finished
    LaunchedEffect(progress) {
        if (progress == 1f) {
            onAnimationEnd()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.overflow_color_1)),
        contentAlignment = Alignment.Center
    ){
        LottieAnimation(
            composition = composition,
            iterations = 1
        )
    }
}