package com.codewithdipesh.onboarding_presentation.welcome
import  com.codewithdipesh.core.R

import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.codewithdipesh.core.navigation.Route
import com.codewithdipesh.core.util.UiEvent
import com.codewithdipesh.core_ui.LocalSpacing
import com.codewithdipesh.onboarding_presentation.components.ActionButton

@OptIn(UnstableApi::class)
@Composable
fun WelcomeScreen(
    onNavigate : (UiEvent.Navigate) ->Unit
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val uri = remember {
        getVideoUri(context)
    }
    val exoPlayer = remember{
        context.buildExoPlayer(uri)
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        //background video player
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge)
                .systemBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Nutrio logo
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = spacing.spaceMedium),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    painter = painterResource(R.drawable.nutrio),
                    contentDescription = ""
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                //headline
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = spacing.spaceMedium, horizontal = spacing.spaceLarge),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = stringResource(R.string.welcome_text_title) ,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                    Text(
                        text = stringResource(R.string.welcome_text_desc),
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(R.color.text_grey)
                    )

                }
                //Button
                ActionButton(
                    text = stringResource(R.string.get_started),
                    onClick = {
                        onNavigate(UiEvent.Navigate(Route.GENDER))
                    }
                )

            }


        }
    }

}

private fun getVideoUri(context: Context): Uri {
    return Uri.parse("android.resource://${context.packageName}/${R.raw.app_starting_video}")
}


private fun Context.buildExoPlayer(uri: Uri) : ExoPlayer {
    val player = ExoPlayer.Builder(this).build()
    val mediaItem = MediaItem.fromUri(uri)
    player.setMediaItem(mediaItem)
    player.repeatMode = Player.REPEAT_MODE_ALL
    player.prepare()
    player.playWhenReady = true

    return player
}
