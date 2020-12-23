package com.friple.time.mvvm.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.friple.time.mvvm.view.containers.TabContainerFragment
import com.friple.time.mvvm.view.fragments.*
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun Tab(tabName: String) =
        FragmentScreen("tab", object : Creator<FragmentFactory, Fragment> {
        override fun create(argument: FragmentFactory): Fragment {
            return TabContainerFragment.newInstance(tabName)
        }

    })

    fun Home(containerName: String, number: Int) =
        FragmentScreen("tab", object : Creator<FragmentFactory, Fragment> {
            override fun create(argument: FragmentFactory): Fragment {
                return HomeScreen.newInstance(containerName, number)
            }

        })

    fun Stats(containerName: String, number: Int) =
        FragmentScreen("tab", object: Creator<FragmentFactory, Fragment>{
        override fun create(argument: FragmentFactory): Fragment {
            return StatsScreen.newInstance(containerName, number)
        }

    })

    fun Profile(containerName: String, number: Int) =
        FragmentScreen("tab", object: Creator<FragmentFactory, Fragment>{
            override fun create(argument: FragmentFactory): Fragment {
                return ProfileScreen.newInstance(containerName, number)
            }

        })
}