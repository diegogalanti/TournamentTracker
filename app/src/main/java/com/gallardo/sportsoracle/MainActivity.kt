package com.gallardo.sportsoracle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gallardo.sportsoracle.viewmodels.GroupsViewModel

val groupsViewModel = GroupsViewModel()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        groupsViewModel.groups
    }
}