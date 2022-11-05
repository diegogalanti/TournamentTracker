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
    if (gridLayout.childCount == 0)
        data?.forEach() {
            val teamFlagView =
                TeamFlagBinding.inflate(LayoutInflater.from(gridLayout.context), gridLayout, false)
            teamFlagView.flag = it
            gridLayout.addView(teamFlagView.root)
        }
}

@BindingAdapter("teamsWithResults")
fun bindGroupTeamsDetails(
    detailsCl: ConstraintLayout,
    teamWithGroupResult: List<TeamWithGroupResult>
) {
    val tableFixed = detailsCl.findViewById<TableLayout>(R.id.table_fixed)
    if (tableFixed.childCount == 1) {
        val url = "https://raw.githubusercontent.com/hampusborgos/country-flags/main/svg/"
        val tableScroll = detailsCl.findViewById<TableLayout>(R.id.table_scroll)

        teamWithGroupResult.forEach() { currentTeam ->
            val fixedRow = TableRow(tableFixed.context)
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
            bindImage(flag, url + currentTeam.flag)
            val team = MaterialTextView(linearL.context)
            team.text = currentTeam.name
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

            val scrollRow = TableRow(tableFixed.context)
            val points = MaterialTextView(fixedRow.context)
            points.text = currentTeam.points.toString()
            points.gravity = Gravity.CENTER;
            val played = MaterialTextView(fixedRow.context)
            played.text = currentTeam.played.toString()
            played.gravity = Gravity.CENTER;
            val won = MaterialTextView(fixedRow.context)
            won.text = currentTeam.won.toString()
            won.gravity = Gravity.CENTER;
            val drawn = MaterialTextView(fixedRow.context)
            drawn.text = currentTeam.drawn.toString()
            drawn.gravity = Gravity.CENTER;
            val lost = MaterialTextView(fixedRow.context)
            lost.text = currentTeam.lost.toString()
            lost.gravity = Gravity.CENTER;
            val goals = MaterialTextView(fixedRow.context)
            goals.text = currentTeam.goalDiff.toString()
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