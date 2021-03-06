package com.maodq.demo.internal.transition.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.maodq.demo.R
import kotlinx.android.synthetic.main.fragment_auto_transition.*

/**
 * AutoTransition
 */
class AutoTransitionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_auto_transition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_transition.setOnClickListener {
            // beginDelayedTransition(viewGroup)方法为传入transition，内部会默认使用AutoTransition
            // AutoTransition内部会创建Fade和ChangeBounds作为默认transition
            TransitionManager.beginDelayedTransition(ll_transition)
            tv_transition.visibility = if (tv_transition.visibility == View.VISIBLE)
                View.GONE else View.VISIBLE
        }
    }
}
