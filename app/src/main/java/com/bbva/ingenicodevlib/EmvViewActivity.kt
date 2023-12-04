package com.bbva.ingenicodevlib

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bbva.ingenicodevlib.databinding.ActivityEmvViewBinding
import com.bbva.ingenicodevlib.demo.Printer
import com.bbva.ingenicodevlib.fragment.IdleFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmvViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmvViewBinding

    private val idleFragment = IdleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmvViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, idleFragment).commit()
        idleFragment.listener = View.OnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                Printer().invoke()
            }
        }
    }
}