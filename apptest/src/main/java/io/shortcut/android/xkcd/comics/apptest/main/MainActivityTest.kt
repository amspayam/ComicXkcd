package io.shortcut.android.xkcd.comics.apptest.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.shortcut.comics.apptest.databinding.ActivityMainTestBinding

class MainActivityTest : AppCompatActivity() {

    private lateinit var binding: ActivityMainTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}