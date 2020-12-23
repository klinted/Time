package com.friple.time.mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.friple.time.R
import com.friple.time.mvvm.common.ViewModelProviderFactory
import com.friple.time.mvvm.common.navigation.BackButtonListener
import com.friple.time.mvvm.common.navigation.RouterProvider
import com.friple.time.mvvm.viewmodel.HomeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeScreen : DaggerFragment(), BackButtonListener {

    lateinit var mHomeViewModel: HomeViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mHomeViewModel = ViewModelProvider(this, providerFactory).get(HomeViewModel::class.java)

        checkRouter()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    private fun checkRouter() {
        if (mHomeViewModel.router == null) {
            mHomeViewModel.router = (parentFragment as RouterProvider).router
        }
    }

    override fun onBackPressed(): Boolean {
        mHomeViewModel.onBackPressed()
        return true
    }

    companion object {
        fun newInstance(param1: String, param2: Int) =
            HomeScreen().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putInt("ARG_PARAM2", param2)
                }
            }
    }
}