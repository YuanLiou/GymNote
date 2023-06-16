package com.rayliu.gymnote.wearos.categorylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Text
import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.gymnote.wearos.components.CircularIndeterminateProgressBar
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CategoryListScreen(
    sportCategories: ImmutableList<SportCategory>,
    showLoadingScreen: Boolean,
    listState: ScalingLazyListState,
    onCategoryClicked: (SportCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (showLoadingScreen) {
            CircularIndeterminateProgressBar(isDisplayed = true)
        } else {
            CategoryList(
                sportCategories,
                listState,
                onCategoryClicked = onCategoryClicked
            )
        }
    }
}

@Composable
private fun CategoryList(
    sportCategories: ImmutableList<SportCategory>,
    listState: ScalingLazyListState,
    onCategoryClicked: (SportCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentModifier = modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    ScalingLazyColumn(
        modifier = modifier.fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        state = listState
    ) {
        sportCategories.forEach {
            item {
                CategoryItem(
                    categoryName = it.name,
                    onItemClick = {
                        onCategoryClicked(it)
                    },
                    contentModifier
                )
            }
        }
    }
}

@Composable
private fun CategoryItem(
    categoryName: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Chip(
        modifier = modifier.fillMaxWidth(),
        onClick = onItemClick,
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
    CategoryItem(
        categoryName = "Testing Chip",
        onItemClick = {}
    )
}