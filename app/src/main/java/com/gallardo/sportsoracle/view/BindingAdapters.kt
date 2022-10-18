package com.gallardo.sportsoracle.view

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.gallardo.sportsoracle.R
import com.gallardo.sportsoracle.databinding.GroupItemBinding
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.model.Team
import com.gallardo.sportsoracle.view.rvadapter.GroupsListAdapter


@BindingAdapter("groups_list")
fun bindGroupsListAdapter(
    recyclerView: RecyclerView,
    data: List<Group>?
) {
    val adapter = recyclerView.adapter as GroupsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("group_teams")
fun bindGroupTeams(
    gridLayout: GridLayout,
    data: List<Team>?
) {
    if (gridLayout.childCount == 0)
        data?.forEach() {
            val teamView =
                GroupItemBinding.inflate(LayoutInflater.from(gridLayout.context), gridLayout, false)
            teamView.team = it
            gridLayout.addView(teamView.root)
        }
}

@BindingAdapter("bind_image")
fun bindTeamImage(
    imageView: ImageView,
    url: String?
) {
    val url2 = "https://raw.githubusercontent.com/hampusborgos/country-flags/main/svg/$url"
    url2.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        val imageLoader = ImageLoader.Builder(imageView.context).components {
            add(SvgDecoder.Factory())
        }.placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image).build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url2)
            .target(imageView)
            .scale(Scale.FILL)
            .build()

        imageLoader.enqueue(request)
    }
//    val resId: Int = imageView.resources.getIdentifier(
//        url?.substringBeforeLast('.'),
//        "drawable",
//        imageView.context.packageName
//    )
//    imageView.setImageResource(if (resId == 0) R.drawable.brazil else resId)
}