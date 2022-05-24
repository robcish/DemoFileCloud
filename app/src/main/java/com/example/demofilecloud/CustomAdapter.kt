package com.example.demofilecloud

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demofilecloud.databinding.ItemGifListBinding
import com.giphy.sdk.core.models.Media
import com.giphy.sdk.core.models.enums.RenditionType
import io.reactivex.functions.Action
import timber.log.Timber

class CustomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<Media> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemGifListBinding>(LayoutInflater.from(parent.context), R.layout.item_gif_list, parent, false)
        return ItemGifAdapterHolder(binding.root, binding)
    }

    class ItemGifAdapterHolder(
        view: View,
        val binding: ItemGifListBinding
    ) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gifHolder = holder as ItemGifAdapterHolder
        gifHolder.binding.gifView.setMedia(list[position], RenditionType.fixedWidthSmall)
        gifHolder.binding.textView.text = list[position].title ?: "untitled gif"

        val id = list[position].id
        gifHolder.binding.setClick {
            gifHolder.binding.root.context.let {
                it.startActivity(Intent(it, DetailActivity::class.java).apply { putExtra("id", id) })
            }
        }
    }

//    private inline fun <reified T : Activity> Context.createIntent() = Intent(this, T::class.java)

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: List<Media>) {
        this.list = list
        notifyDataSetChanged()
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