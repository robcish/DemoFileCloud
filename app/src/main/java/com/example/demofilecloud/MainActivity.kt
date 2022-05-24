package com.example.demofilecloud

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demofilecloud.api.ApiImplementation
import com.example.demofilecloud.api.ApiInterface
import com.example.demofilecloud.api.mapDataItemToMediaClass
import com.example.demofilecloud.databinding.ActivityMainBinding
import com.giphy.sdk.core.models.Media
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.create
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var apiImplementation: ApiImplementation
    private lateinit var binding: ActivityMainBinding
    private var trendList: List<Media> = listOf()
    private var searchList: List<Media> = listOf()

    private val searchInputBehaviorSubject = BehaviorSubject.create<String>()
    private val searchDisposable = CompositeDisposable()
    private val searchFlowable = searchInputBehaviorSubject.toFlowable(BackpressureStrategy.LATEST)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        getTrentList()
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setClickCV {
            startActivity(Intent(this, CVActivity::class.java))
        }
        binding.recycler.adapter = CustomAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(this)

        val api: ApiInterface = App.getRetrofitClient().create()
        apiImplementation = ApiImplementation(api)

        initSearch()
    }

    private fun initSearch() {
        binding.setClear { clearSearch() }
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                searchInputBehaviorSubject.onNext(editable.toString())
            }
        })

        searchDisposable.add(searchFlowable
            .debounce(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isEmpty()) {
                    binding.info.text = getString(R.string.info_trending_gifs)
                    (binding.recycler.adapter as CustomAdapter).updateList(trendList)
                } else {
                    getSearchList(it)
                }
            }
        )
    }

    private fun clearSearch() {
        if (binding.search.text.isNotEmpty()) {
            binding.info.text = getString(R.string.info_trending_gifs)
            (binding.recycler.adapter as CustomAdapter).updateList(trendList)
            binding.search.setText("")
        }
    }

    @SuppressLint("CheckResult")
    private fun getSearchList(tag: String) {
        binding.info.text = getString(R.string.info_searched_gifs)
        apiImplementation.search(limit = 25, tag = tag)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    val list = it.data?.mapNotNull { dataItem ->
                        dataItem?.let {
                            dataItem.mapDataItemToMediaClass()
                        }
                    }
                    if (list?.isNotEmpty() == true) {
                        searchList = list
                    }
                }, onError = {
                    Timber.e(it.cause)
                },
                onComplete = {
                    if (searchList.isNotEmpty())
                        (binding.recycler.adapter as CustomAdapter).updateList(searchList)
                }
            )
    }

    @SuppressLint("CheckResult")
    private fun getTrentList() {
        binding.info.text = getString(R.string.info_trending_gifs)
        apiImplementation.getTrendList(limit = 25)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    val list = it.data?.mapNotNull { dataItem ->
                        dataItem?.let {
                            dataItem.mapDataItemToMediaClass()
                        }
                    }
                    if (list?.isNotEmpty() == true) {
                        trendList = list
                    }
                }, onError = {
                    Timber.e(it.cause)
                },
                onComplete = {
                    if (trendList.isNotEmpty())
                        (binding.recycler.adapter as CustomAdapter).updateList(trendList)
                }
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        searchDisposable.clear()
    }

    object RxActions {
        @JvmStatic
        fun runSafe(action: Action?) {
            try {
                action?.run()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

}