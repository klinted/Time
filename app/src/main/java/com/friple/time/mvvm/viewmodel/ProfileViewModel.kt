package com.friple.time.mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class ProfileViewModel @Inject constructor(

) : ViewModel() {

    var router: Router? = null

    fun onBackPressed() {
        router?.exit()
        Log.d("TAG", "home onBackPressed: $router")

    }
}