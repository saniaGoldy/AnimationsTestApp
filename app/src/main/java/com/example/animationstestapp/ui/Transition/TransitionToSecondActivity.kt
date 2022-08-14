package com.example.animationstestapp.ui.Transition

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

interface TransitionToSecondActivity {
    fun startTransition(context: Context,from: AppCompatActivity ,destination: Class<out Any>, sharedView: android.util.Pair<android.view.View, String>)
}