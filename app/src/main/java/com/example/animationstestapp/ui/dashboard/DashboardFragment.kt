package com.example.animationstestapp.ui.dashboard

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.animationstestapp.R
import com.example.animationstestapp.SharedViewActivity
import com.example.animationstestapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.sharedButton.setOnClickListener { startTransitionToSecondActivity() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startTransitionToSecondActivity(){
        val intent = Intent(this.requireContext(), SharedViewActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this.requireActivity(),
            android.util.Pair(binding.sharedButton, getString(R.string.shared_view_button))
        )
        startActivity(intent, options.toBundle())
    }
}