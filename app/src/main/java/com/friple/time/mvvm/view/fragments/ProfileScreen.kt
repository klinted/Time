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
import com.friple.time.mvvm.viewmodel.ProfileViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileScreen : DaggerFragment(), BackButtonListener {

    lateinit var mProfileViewModel: ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mProfileViewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)

        checkRouter()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_screen, container, false)
    }

    private fun checkRouter() {
        if (mProfileViewModel.router == null) {
            mProfileViewModel.router = (parentFragment as RouterProvider).router
        }
    }

    override fun onBackPressed(): Boolean {
        mProfileViewModel.onBackPressed()
        return true
    }

    companion object {
        fun newInstance(param1: String, param2: Int) =
            ProfileScreen().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putInt("ARG_PARAM2", param2)
                }
            }
    }
}