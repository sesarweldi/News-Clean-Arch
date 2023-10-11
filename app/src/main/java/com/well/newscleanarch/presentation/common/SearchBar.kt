package com.well.newscleanarch.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.well.newscleanarch.R
import com.well.newscleanarch.presentation.Dimens
import com.well.newscleanarch.ui.theme.*
import com.well.newscleanarch.util.StringConstant

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChanged: (String) -> Unit,
    onSearch: () -> Unit,
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }

    Box(modifier = modifier) {
        TextField(
            value = text, onValueChange = onValueChanged,
            readOnly = readOnly,
            modifier = Modifier
                .fillMaxWidth()
                .searchBarBorder(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.IconSearchSize)
                )
            },
            placeholder = {
                Text(text = StringConstant.SEARCH, style = TextCaption, color = PlaceHolderColor)
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = BackgroundColor,
                cursorColor = Color.Black,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            textStyle = TextCaption,
            interactionSource = interactionSource,
        )
    }
}

fun Modifier.searchBarBorder() = composed {
    border(
        width = 1.dp,
        color = Color.Black,
        shape = RoundedCornerShape(8.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    NewsCleanArchTheme {
        SearchBar(text = "", readOnly = false, onValueChanged = {}) {
        }
    }
}