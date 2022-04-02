package com.kazakago.blueprint.presentation.view.hierarchy.github

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgId
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.global.theme.PreviewTheme
import com.kazakago.blueprint.presentation.view.global.util.clickableWithRipple
import java.net.URL

@Composable
fun GithubOrgRow(
    githubOrg: GithubOrg,
    onClickItem: (githubOrg: GithubOrg) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickableWithRipple(onClick = { onClickItem(githubOrg) })
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(githubOrg.imageUrl.toString())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(6.dp)),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.id, githubOrg.id.value),
                style = MaterialTheme.typography.labelMedium,
            )
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = githubOrg.name.value,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGithubOrgRow() {
    PreviewTheme {
        GithubOrgRow(
            githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
            onClickItem = {},
        )
    }
}
