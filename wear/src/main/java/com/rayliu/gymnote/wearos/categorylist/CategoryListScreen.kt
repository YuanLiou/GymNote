package com.rayliu.gymnote.wearos.categorylist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.scrollAway
import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel = koinViewModel()
) {
    val listState = rememberScalingLazyListState()
    Scaffold(
        timeText = {
            TimeText(modifier = Modifier.scrollAway(listState))
        },
        vignette = {
            Vignette(vignettePosition = VignettePosition.TopAndBottom)
        },
        positionIndicator = {
            PositionIndicator(scalingLazyListState = listState)
        }
    ) {
        val items = viewModel.provideCategories()
            .collectAsState(initial = emptyList()).value
        CategoryList(
            items.toImmutableList(),
            listState
        )
    }
}


@Composable
private fun CategoryList(
    sportCategories: ImmutableList<SportCategory>,
    listState: ScalingLazyListState
) {
    val contentModifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        state = listState
    ) {
        sportCategories.forEach {
            item { CategoryItem(categoryName = it.name, contentModifier) }
        }
    }
}

@Composable
private fun CategoryItem(
    categoryName: String,
    modifier: Modifier = Modifier
) {
    Chip(
        modifier = modifier.fillMaxWidth(),
        onClick = {},
        label = {
            Text(categoryName)
        }
    )
}

@Preview(
    group = "User Select Options",
    widthDp = PreviewConstants.WEAR_PREVIEW_ROW_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_ROW_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL,
    uiMode = PreviewConstants.WEAR_PREVIEW_UI_MODE,
    backgroundColor = PreviewConstants.WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = PreviewConstants.WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
private fun CategoryItemPreview() {
    CategoryItem(categoryName = "Testing Chip")
}