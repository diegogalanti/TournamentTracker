package com.gallardo.sportsoracle.view

import android.content.Context
import android.util.DisplayMetrics
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

@BindingAdapter("bind_details")
fun bindGroupTeamsDetails(
    detailsCl: ConstraintLayout,
    data: List<Team>?
) {
    val url = "https://raw.githubusercontent.com/hampusborgos/country-flags/main/svg/"
    val tableFixed = detailsCl.findViewById<TableLayout>(R.id.table_fixed)
    val tableScroll = detailsCl.findViewById<TableLayout>(R.id.table_scroll)
    if (tableFixed.childCount == 1)
        data?.forEach() {
            val fixedRow = TableRow(tableFixed.context)
            fixedRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT)

            val scrollRow = TableRow(tableFixed.context)
            fixedRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT)
            val linearL = LinearLayout(fixedRow.context)
            linearL.orientation = LinearLayout.HORIZONTAL
            linearL.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT)
            linearL.gravity = Gravity.CENTER_VERTICAL
            val pos = TextView(linearL.context)
            pos.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            (pos.layoutParams as LinearLayout.LayoutParams).setMargins(0,0,convertPixelsToDp(8, tableFixed.context),0)
            pos.text = tableFixed.childCount.toString()
            val flag = ImageView(linearL.context)
            flag.adjustViewBounds = true
            flag.clipToOutline = true
            flag.scaleType = ImageView.ScaleType.FIT_XY
            flag.setBackgroundResource(R.drawable.rounded_shape_16_16)
            flag.layoutParams = LinearLayout.LayoutParams(convertPixelsToDp(20, linearL.context),convertPixelsToDp(20, tableFixed.context))
            (flag.layoutParams as LinearLayout.LayoutParams).setMargins(0,convertPixelsToDp(1, tableFixed.context),convertPixelsToDp(8, tableFixed.context),0)
            bindImage(flag, url+ it.flag, convertPixelsToDp(4, linearL.context))
            val team = TextView(linearL.context)
            team.text = it.name
            team.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            (team.layoutParams as LinearLayout.LayoutParams).setMargins(0,0,convertPixelsToDp(8, tableFixed.context),0)
            linearL.addView(pos)
            linearL.addView(flag)
            linearL.addView(team)

            val points = TextView(fixedRow.context)
            points.text = "0"
            val played = TextView(fixedRow.context)
            played.text = "0"
            val won = TextView(fixedRow.context)
            won.text = "0"
            val drawn = TextView(fixedRow.context)
            drawn.text = "0"
            val lost = TextView(fixedRow.context)
            lost.text = "0"
            val goals = TextView(fixedRow.context)
            goals.text = "5 - 3 = 2"
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
    bindImage(imageView, url, 0)
}

fun bindImage(imageView: ImageView,
              url: String, radius: Int)
{
    val request = ImageRequest.Builder(imageView.context)
        .data(url)
        .target(imageView)
        //.transformations(RoundedCornersTransformation(radius.toFloat()))
        .scale(Scale.FILL)
        .build()

    Coil.imageLoader(imageView.context).enqueue(request)
}