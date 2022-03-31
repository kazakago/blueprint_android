package com.kazakago.blueprint.presentation.view.global.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.global.theme.AppTheme

@Composable
fun ErrorContent(
    error: Exception,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.error,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Button(onClick = onRetry) {
            Text(stringResource(id = R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorContent() {
    AppTheme {
        ErrorContent(
            error = IllegalAccessException("hogehogehogehogehogehogehogehogehogehoge"),
            onRetry = {},
        )
    }
}
