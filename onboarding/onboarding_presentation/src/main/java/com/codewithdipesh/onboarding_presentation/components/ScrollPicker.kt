package com.codewithdipesh.onboarding_presentation.components

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp


@Composable
fun <T> ScrollPicker(
    width:Dp,
    itemHeight:Dp,
    numberOfDisplayItems:Int,
    items:List<T>,
    initialItem:T,
    itemScaleFont :Float = 1.5f,
    textStyle: TextStyle,
    textColor:Color,
    selectedTextColor :Color,
    onItemSelected:(T)->Unit
){
    val itemHalfHeight = LocalDensity.current.run { itemHeight.toPx()/2 }
    val scrollState = rememberLazyListState(0)
    var lastSelectedIndex by remember {
        mutableStateOf(0)
    }
    var itemState by remember {
        mutableStateOf(items)
    }

    LaunchedEffect(items){
        itemState = items
        var targetIndex = items.indexOf(initialItem) -1
        //below code is only in infinite scrolling
        //targetIndex += ((Int.MAX_VALUE/2) / items.size)* items.size
        //lastSelectedIndex = targetIndex
        scrollState.scrollToItem(targetIndex)
    }

    LazyColumn(
        modifier = Modifier
            .width(width)
            .height(itemHeight * numberOfDisplayItems),
        state = scrollState,
        flingBehavior = rememberSnapFlingBehavior(
            lazyListState = scrollState
        )
    ) {
        items(
            count = items.size
        ){ i->
            val item = itemState[i]
            Box(
                modifier = Modifier
                    .height(itemHeight)
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        val y = coordinates.positionInParent().y - itemHalfHeight
                        val parentHalfHeight =
                            (coordinates.parentCoordinates?.size?.height ?: 0) / 2f
                        val isSelected =
                            (y > parentHalfHeight - itemHalfHeight && y < parentHalfHeight + itemHalfHeight)
                        if (isSelected && lastSelectedIndex != i) {
                            onItemSelected(item)
                            lastSelectedIndex = i
                        }
                    },
                contentAlignment = Alignment.Center
            ){
                if(item !=itemState[0] && item != itemState[items.size-1]){
                    Text(
                        text = item.toString(),
                        style = textStyle,
                        color = if(lastSelectedIndex == i) {
                            selectedTextColor
                        } else {
                            textColor
                        },
                        fontSize = if (lastSelectedIndex == i) {
                            textStyle.fontSize * itemScaleFont
                        } else {
                            textStyle.fontSize
                        }
                    )
                }

            }

        }

    }
}