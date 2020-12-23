package com.friple.time.mvvm.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.friple.time.R
import com.friple.time.mvvm.common.ViewModelProviderFactory
import com.friple.time.mvvm.common.navigation.BackButtonListener
import com.friple.time.mvvm.common.navigation.RouterProvider
import com.friple.time.mvvm.viewmodel.StatsViewModel
import com.github.terrakok.cicerone.Router
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class StatsScreen : DaggerFragment(), BackButtonListener {

    lateinit var mStatsViewModel: StatsViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mStatsViewModel = ViewModelProvider(this, providerFactory).get(StatsViewModel::class.java)

        checkRouter()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_screen, container, false)
    }

    private fun checkRouter() {
        if (mStatsViewModel.router == null) {
            mStatsViewModel.router = (parentFragment as RouterProvider).router
        }
    }

    override fun onBackPressed(): Boolean {
        mStatsViewModel.onBackPressed()
        return true
    }

    companion object {
        fun newInstance(param1: String, param2: Int) =
            StatsScreen().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putInt("ARG_PARAM2", param2)
                }
            }
    }
}