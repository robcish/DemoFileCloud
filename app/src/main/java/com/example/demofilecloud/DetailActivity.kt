package com.example.demofilecloud

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demofilecloud.api.ApiImplementation
import com.example.demofilecloud.api.ApiInterface
import com.example.demofilecloud.api.mapDataItemToMediaClass
import com.example.demofilecloud.data.DataItem
import com.example.demofilecloud.databinding.ActivityDetailBinding
import com.giphy.sdk.core.models.enums.RenditionType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.create
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var apiImplementation: ApiImplementation
    private lateinit var binding: ActivityDetailBinding

    var dataItem: DataItem? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val api: ApiInterface = App.getRetrofitClient().create()
        apiImplementation = ApiImplementation(api)
        binding.gifView.isLongClickable = false

        apiImplementation.getDetail(id = intent.getStringExtra("id") ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    dataItem = it.data
                }, onError = {
                    Timber.e(it.cause)
                },
                onComplete = {
                    binding.gifView.setMedia(dataItem?.mapDataItemToMediaClass(), RenditionType.downsized)
                    binding.textView.text = prepareDetailText(dataItem)
                }
            )
    }

    private fun prepareDetailText(dataItem: DataItem?): String {
        var detailText = ""
        fun addField(name: String, value: String?) {
            if (!value.isNullOrEmpty()) {
                detailText = "$detailText$name:\n$value\n\n"
            }
        }

        addField("Title", dataItem?.title)
        addField("Rating", dataItem?.rating)
        addField("Username", dataItem?.username)
        addField("Top Level Domain", dataItem?.sourceTld)

        return detailText
    }

}