package cs.hse.rezunik.cosmicnews.android

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cs.hse.rezunik.cosmicnews.Item
import cs.hse.rezunik.cosmicnews.SpaceflightNewsSDK
import cs.hse.rezunik.cosmicnews.db.DatabaseDriverFactory
import kotlinx.coroutines.launch

class NewsViewModel(context: Context) : ViewModel(){
    private val sdk = SpaceflightNewsSDK(DatabaseDriverFactory(context))

    private val _newsList = mutableStateListOf<Item>()
    var errorMessage: String by mutableStateOf("")
    val newsList: List<Item>
        get() = _newsList

    fun getAllNews(){
        viewModelScope.launch {
            try{
                _newsList.clear()
                _newsList.addAll(sdk.getNews(forceReload = true))
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}