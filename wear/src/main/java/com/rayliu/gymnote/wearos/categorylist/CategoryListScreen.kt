package com.rayliu.gymnote.wearos.categorylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.rotary.RotaryDefaults.scrollBehavior
import androidx.wear.compose.foundation.rotary.rotary
import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.gymnote.wearos.components.CircularIndeterminateProgressBar
import com.rayliu.gymnote.wearos.theme.GymNoteTheme
import com.rayliu.gymnote.wearos.theme.PreviewConstants
import com.rayliu.gymnote.wearos.ui.OptionItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun CategoryListScreen(
    sportCategories: ImmutableList<SportCategory>,
    showLoadingScreen: Boolean,
    listState: ScalingLazyListState,
    focusRequester: FocusRequester,
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
                focusRequester,
                onCategoryClicked = onCategoryClicked
            )
        }
    }
}

@Composable
private fun CategoryList(
    sportCategories: ImmutableList<SportCategory>,
    listState: ScalingLazyListState,
    focusRequester: FocusRequester,
    onCategoryClicked: (SportCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentModifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
    ScalingLazyColumn(
        modifier =
        modifier.rotary(
            scrollBehavior(scrollableState = listState),
            focusRequester = focusRequester
        ).fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        state = listState
    ) {
        sportCategories.forEach {
            item {
                OptionItem(
                    title = it.name,
                    onItemClick = {
                        onCategoryClicked(it)
                    },
                    contentModifier
                )
            }
        }
    }
}

@Preview(
    group = "Screen Preview",
    widthDp = PreviewConstants.WEAR_PREVIEW_DEVICE_WIDTH_DP,
    heightDp = PreviewConstants.WEAR_PREVIEW_DEVICE_HEIGHT_DP,
    apiLevel = PreviewConstants.WEAR_PREVIEW_API_LEVEL
)
@Composable
private fun CategoryListScreenPreview() {
    GymNoteTheme {
        CategoryListScreen(
            sportCategories = persistentListOf(
                SportCategory(id = 4851, name = "Mitchell Dotson"),
                SportCategory(id = 8975, name = "Madeline Randolph")
            ),
            showLoadingScreen = false,
            listState = ScalingLazyListState(),
            focusRequester = FocusRequester(),
            onCategoryClicked = {},
            modifier = Modifier
        )
    }
}
