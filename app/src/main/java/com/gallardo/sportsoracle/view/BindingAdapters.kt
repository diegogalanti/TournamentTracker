package com.gallardo.sportsoracle.view

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.request.ImageRequest
import coil.size.Scale
import com.gallardo.sportsoracle.R
import com.gallardo.sportsoracle.databinding.FixedRowBinding
import com.gallardo.sportsoracle.databinding.ScrollRowBinding
import com.gallardo.sportsoracle.databinding.TeamFlagBinding
import com.gallardo.sportsoracle.model.*
import com.gallardo.sportsoracle.view.rvadapter.GroupsListAdapter
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("groups_list")
fun bindGroupsListAdapter(
    recyclerView: RecyclerView,
    data: List<Group>?
) {
    val adapter = recyclerView.adapter as GroupsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("bind_flags")
fun bindGroupTeamsFlags(
    gridLayout: GridLayout,
    data: List<String>?
) {
    if (gridLayout.childCount != 0) {
        data?.forEachIndexed { index, s ->
            bindTeamImage((gridLayout[index] as ConstraintLayout)[0] as ImageView, s)
        }
    } else {
        data?.forEach() {
            val teamFlagView =
                TeamFlagBinding.inflate(LayoutInflater.from(gridLayout.context), gridLayout, false)
            teamFlagView.flag = it
            gridLayout.addView(teamFlagView.root)
        }
    }
}

@BindingAdapter("teamsWithResults")
fun bindGroupTeamsDetails(
    detailsCl: ConstraintLayout,
    teamWithGroupResult: List<TeamWithGroupResult>
) {
    val tableFixed = detailsCl.findViewById<TableLayout>(R.id.table_fixed)
    val tableScroll = detailsCl.findViewById<TableLayout>(R.id.table_scroll)

    if (tableFixed.childCount == 1) {
        teamWithGroupResult.forEachIndexed { index, currentTeam ->
            val fixedRow =
                FixedRowBinding.inflate(LayoutInflater.from(tableFixed.context), tableFixed, false)
            fixedRow.position = (index + 1).toString()
            fixedRow.flag = currentTeam.flag
            fixedRow.name = currentTeam.name
            tableFixed.addView(fixedRow.root)
            val scrollRow = ScrollRowBinding.inflate((LayoutInflater.from(tableScroll.context)),tableScroll,false)
            scrollRow.points = currentTeam.points.toString()
            scrollRow.played = currentTeam.played.toString()
            scrollRow.won = currentTeam.won.toString()
            scrollRow.drawn = currentTeam.drawn.toString()
            scrollRow.lost = currentTeam.lost.toString()
            scrollRow.goalDiff = currentTeam.goalDiff
            tableScroll.addView(scrollRow.root)
        }
    } else {
        teamWithGroupResult.forEachIndexed { index, currentTeam ->
            (((tableFixed[index + 1] as TableRow)[0] as LinearLayout)[0] as MaterialTextView).text =
                (index + 1).toString()
            bindTeamImage(
                (((tableFixed[index + 1] as TableRow)[0] as LinearLayout)[1] as ImageView),
                currentTeam.flag
            )
            (((tableFixed[index + 1] as TableRow)[0] as LinearLayout)[2] as MaterialTextView).text =
                currentTeam.name
            ((tableScroll[index + 1] as TableRow)[0] as MaterialTextView).text = currentTeam.points.toString()
            ((tableScroll[index + 1] as TableRow)[1] as MaterialTextView).text = currentTeam.played.toString()
            ((tableScroll[index + 1] as TableRow)[2] as MaterialTextView).text = currentTeam.won.toString()
            ((tableScroll[index + 1] as TableRow)[3] as MaterialTextView).text = currentTeam.drawn.toString()
            ((tableScroll[index + 1] as TableRow)[4] as MaterialTextView).text = currentTeam.lost.toString()
            ((tableScroll[index + 1] as TableRow)[5] as MaterialTextView).text = currentTeam.goalDiff

        }
    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun convertPixelsToDp(px: Int, context: Context): Int {
    return px * (context.resources
        .displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

@BindingAdapter("bind_image")
fun bindTeamImage(
    imageView: ImageView,
    file: String?
) {
    val url = "https://raw.githubusercontent.com/hampusborgos/country-flags/main/svg/$file"
    bindImage(imageView, url)
}

fun bindImage(
    imageView: ImageView,
    url: String
) {
    val request = ImageRequest.Builder(imageView.context)
        .data(url)
        .target(imageView)
        .scale(Scale.FILL)
        .build()

    Coil.imageLoader(imageView.context).enqueue(request)
}