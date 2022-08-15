package com.example.animationstestapp.ui.home

import android.animation.AnimatorInflater.loadAnimator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.animationstestapp.R
import com.example.animationstestapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        with(binding) {
            valAnimButton.setOnClickListener { button ->
                useValueAnimatorXML(button)
            }
            objAnimButton.setOnClickListener { button ->
                useObjectAnimator(button)
            }
            viewPropAnimButton.setOnClickListener { button ->
                useViewPropertyAnimator(button)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun useValueAnimatorXML(view: View) {
        with(loadAnimator(this.requireContext(), R.animator.animate_alpha) as ValueAnimator) {
            addUpdateListener { animation -> view.alpha = animation?.animatedValue as Float }
            start()
        }
    }

    private fun useObjectAnimator(view: View) {
        with(ObjectAnimator.ofFloat(view, "rotation", 0f, 360f)) {
            duration = ANIMATION_DURATION
            start()
        }
    }

    private fun useViewPropertyAnimator(view: View) {
        view.animate()
            .alpha(1f)
            .rotation(360f)
            .scaleX(2f)
            .scaleY(2f)
            .setDuration(ANIMATION_DURATION)
            .withEndAction { propertyAnimatorResetScale(view) }
            .start()
    }

    private fun propertyAnimatorResetScale(view: View){
        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .rotation(0f)
            .setDuration(ANIMATION_DURATION)
            .start()
    }

    companion object{
        const val ANIMATION_DURATION = 2000L
    }
}