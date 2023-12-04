package com.bbva.ingenicodevlib.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bbva.ingenicodevlib.R
import com.bbva.ingenicodevlib.databinding.FragmentIdleBinding

class IdleFragment: Fragment(R.layout.fragment_idle) {
    private lateinit var binding: FragmentIdleBinding
    var listener: View.OnClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentIdleBinding.bind(view)

        binding.bbvaLogo.setOnClickListener(listener)
    }
}