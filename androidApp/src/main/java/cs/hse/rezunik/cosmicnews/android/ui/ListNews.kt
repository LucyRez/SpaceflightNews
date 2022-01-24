package cs.hse.rezunik.cosmicnews.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import cs.hse.rezunik.cosmicnews.Item
import cs.hse.rezunik.cosmicnews.NewsState
import cs.hse.rezunik.cosmicnews.android.NewsViewModel

@Composable
fun ListNews(vm: NewsViewModel) {

    LaunchedEffect(Unit, block = {
        vm.getAllNews()
    })

    LazyColumnFor(items = vm.newsList){ index, item ->
            NewsItem(item = item as Item)
            //Spacer(modifier = Modifier.height(10.dp) )
    }

}

@Composable
fun <Item> LazyColumnFor(
    items: List<Item>,
    modifier: Modifier = Modifier,
    horizontalGravity: Alignment.Horizontal = Alignment.Start,
    itemContent: @Composable() (LazyItemScope.(cs.hse.rezunik.cosmicnews.Item, Any?) -> Unit)
) {
}

@Composable
fun NewsItem(item: Item){
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Text(
            text = item.title,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight= FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(1f),
        )
        Image(
            painter = rememberImagePainter(item.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(260.dp)
                .padding(top = 20.dp)
        )
        Text(
            text = item.articleUrl,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = item.timePublished,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = item.newsSite,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}