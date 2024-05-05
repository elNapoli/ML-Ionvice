package com.baldomeronapoli.mlinvoice.presenter.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baldomeronapoli.mlinvoice.presenter.base.BasePreview
import com.baldomeronapoli.mlinvoice.presenter.ui.theme.success
import com.baldomeronapoli.mlinvoice.presenter.ui.theme.successContainer
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import java.util.Locale

enum class FeedbackType {
    ERROR,
    SUCCESS,
    DEFAULT
}

// NOTE: por el momento no implementaré el succes ni el default, solo el error
@Composable
fun CardFeedback(
    modifier: Modifier = Modifier,
    message: String,
    type: FeedbackType = FeedbackType.ERROR
) {
    val color = if (type === FeedbackType.ERROR) MaterialTheme.colorScheme.error else success
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (type === FeedbackType.ERROR) MaterialTheme.colorScheme.errorContainer else successContainer,
        ),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Icon(
                imageVector = if (type === FeedbackType.ERROR) Icons.Outlined.ErrorOutline else Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = color
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = message.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                textAlign = TextAlign.Start,
                color = color
            )
        }
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewCardFeedback() {
    BasePreview {
        CardFeedback(message = "esto es una prueba", type = FeedbackType.SUCCESS)
    }
}

@ExperimentalPermissionsApi
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewCardFeedbackDark() {
    BasePreview {
        CardFeedback(message = "esto es una  pruebapruebapruebaprueba pruebapruebapruebaprueba pruebapruebapruebaprueba")

    }
}