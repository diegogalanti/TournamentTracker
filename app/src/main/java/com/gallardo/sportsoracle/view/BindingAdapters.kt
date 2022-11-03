package com.gallardo.sportsoracle.view

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.request.ImageRequest
import coil.size.Scale
import com.gallardo.sportsoracle.R
import com.gallardo.sportsoracle.databinding.TeamFlagBinding
import com.gallardo.sportsoracle.model.Group
import com.gallardo.sportsoracle.model.Match
import com.gallardo.sportsoracle.model.Team
import com.gallardo.sportsoracle.view.rvadapter.GroupsListAdapter
import com.google.android.material.textview.MaterialTextView


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
    if (gridLayout.childCount == 0)
        data?.forEach() {
            val teamFlagView =
                TeamFlagBinding.inflate(LayoutInflater.from(gridLayout.context), gridLayout, false)
            teamFlagView.flag = it
            gridLayout.addView(teamFlagView.root)
        }
}

@BindingAdapter(value = ["teams", "matches"], requireAll = true)
fun bindGroupTeamsDetails(
    detailsCl: ConstraintLayout,
    teams: List<Team>?, matches: List<Match>?
) {
    val tableFixed = detailsCl.findViewById<TableLayout>(R.id.table_fixed)
    if (tableFixed.childCount == 1) {
        Log.e("ABC", matches.toString())
        val url = "https://raw.githubusercontent.com/hampusborgos/country-flags/main/svg/"
        val tableScroll = detailsCl.findViewById<TableLayout>(R.id.table_scroll)
        teams?.forEach() {
            val fixedRow = TableRow(tableFixed.context)
            val scrollRow = TableRow(tableFixed.context)
            val linearL = LinearLayout(fixedRow.context)
            linearL.gravity = Gravity.CENTER_VERTICAL
            val pos = MaterialTextView(linearL.context)
            pos.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            (pos.layoutParams as LinearLayout.LayoutParams).setMargins(
                0,
                0,
                convertPixelsToDp(8, tableFixed.context),
                0
            )
            pos.text = tableFixed.childCount.toString()
            val flag = ImageView(linearL.context)
            flag.adjustViewBounds = true
            flag.clipToOutline = true
            flag.scaleType = ImageView.ScaleType.FIT_XY
            flag.setBackgroundResource(R.drawable.rounded_shape_16_16)
            flag.layoutParams = LinearLayout.LayoutParams(
                convertPixelsToDp(20, linearL.context),
                convertPixelsToDp(20, linearL.context)
            )
            (flag.layoutParams as LinearLayout.LayoutParams).setMargins(
                0,
                convertPixelsToDp(1, linearL.context),
                convertPixelsToDp(8, linearL.context),
                0
            )
            bindImage(flag, url + it.flag)
            val team = MaterialTextView(linearL.context)
            team.text = it.name
            team.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            (team.layoutParams as LinearLayout.LayoutParams).setMargins(
                0,
                0,
                convertPixelsToDp(8, linearL.context),
                0
            )
            linearL.addView(pos)
            linearL.addView(flag)
            linearL.addView(team)

            val points = MaterialTextView(fixedRow.context)
            points.text = "0"
            points.gravity = Gravity.CENTER;
            val played = MaterialTextView(fixedRow.context)
            played.text = "0"
            played.gravity = Gravity.CENTER;
            val won = MaterialTextView(fixedRow.context)
            won.text = "0"
            won.gravity = Gravity.CENTER;
            val drawn = MaterialTextView(fixedRow.context)
            drawn.text = "0"
            drawn.gravity = Gravity.CENTER;
            val lost = MaterialTextView(fixedRow.context)
            lost.text = "0"
            lost.gravity = Gravity.CENTER;
            val goals = MaterialTextView(fixedRow.context)
            goals.text = "5 - 3 = 2"
            goals.gravity = Gravity.CENTER;
            fixedRow.addView(linearL)
            scrollRow.addView(points)
            scrollRow.addView(played)
            scrollRow.addView(won)
            scrollRow.addView(drawn)
            scrollRow.addView(lost)
            scrollRow.addView(goals)
            tableFixed.addView(fixedRow)
            tableScroll.addView(scrollRow)
        }
    }
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