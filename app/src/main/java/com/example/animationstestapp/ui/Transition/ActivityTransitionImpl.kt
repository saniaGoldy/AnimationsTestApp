package com.example.animationstestapp.ui.Transition

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class ActivityTransitionImpl: TransitionToSecondActivity {
    override fun startTransition(
        context: Context,
        from: AppCompatActivity,
        destination: Class<out Any>,
        sharedView: Pair<View, String>
    ) {
        val intent = Intent(context, destination::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            from,
            sharedView
        )
        startActivity(context ,intent, options.toBundle())
    }
}