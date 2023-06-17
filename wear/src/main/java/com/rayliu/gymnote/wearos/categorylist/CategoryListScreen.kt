package com.rayliu.gymnote.wearos.categorylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import com.rayliu.commonmain.domain.model.SportCategory
import com.rayliu.gymnote.wearos.components.CircularIndeterminateProgressBar
import com.rayliu.gymnote.wearos.ui.OptionItem
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
                OptionItem(
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