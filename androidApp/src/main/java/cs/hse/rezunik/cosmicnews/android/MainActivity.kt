package cs.hse.rezunik.cosmicnews.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import cs.hse.rezunik.cosmicnews.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cs.hse.rezunik.cosmicnews.NewsState
import cs.hse.rezunik.cosmicnews.SpaceflightNewsSDK
import cs.hse.rezunik.cosmicnews.android.ui.ListNews
import cs.hse.rezunik.cosmicnews.db.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()

    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var progressBarView: FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val sdk = SpaceflightNewsSDK(DatabaseDriverFactory(this))

    private val newsRvAdapter = NewsRvAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = NewsViewModel(this)
        super.onCreate(savedInstanceState)


//        setContent {
//            ListNews(vm)
//        }


        title = "Spaceflight News"
        setContentView(R.layout.activity_main)

        newsRecyclerView = findViewById(R.id.newsListRv)
        progressBarView = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeContainer)

        newsRecyclerView.adapter = newsRvAdapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            displayNews(true)
        }

        displayNews(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    private fun displayNews(needReload: Boolean) {
        progressBarView.isVisible = true
        mainScope.launch {
            kotlin.runCatching {
                sdk.getNews(needReload)
            }.onSuccess {
                newsRvAdapter.news = it
                newsRvAdapter.notifyDataSetChanged()
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            progressBarView.isVisible = false
        }
    }
}
