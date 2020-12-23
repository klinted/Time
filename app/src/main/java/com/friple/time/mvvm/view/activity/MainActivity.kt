package com.friple.time.mvvm.view.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.friple.time.R
import com.friple.time.mvvm.common.Screens.Tab
import com.friple.time.mvvm.common.ViewModelProviderFactory
import com.friple.time.mvvm.common.navigation.BackButtonListener
import com.friple.time.mvvm.common.navigation.RouterProvider
import com.friple.time.mvvm.viewmodel.MainViewModel
import com.github.terrakok.cicerone.Router
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), RouterProvider {

    lateinit var mMainViewModel: MainViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    override lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)

        Log.d("TAG", " main onBackPressed: $router")

        initBottomBar()

        if (savedInstanceState == null) {
            selectTab("HOME")
        }
    }

    private fun initBottomBar() {

        // Sets bottom navigation
        bottom_navigation
            .addItem(BottomNavigationItem(R.drawable.ic_home, "Home"))
            .addItem(BottomNavigationItem(R.drawable.ic_statistic, "Stats"))
            .addItem(BottomNavigationItem(R.drawable.ic_profile, "Profile"))
            .setFirstSelectedPosition(0)
            .setActiveColor(R.color.color_selectedWhite)
            .setInActiveColor(R.color.color_unselectedWhite)
            .setBarBackgroundColor(R.color.color_invisible)
            .initialise()

        bottom_navigation.setTabSelectedListener(object :
            BottomNavigationBar.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                when (position) {
                    0 -> selectTab("HOME")
                    1 -> selectTab("STATS")
                    2 -> selectTab("PROFILE")
                }
                bottom_navigation.selectTab(position, false)
            }

            override fun onTabUnselected(position: Int) {}

            override fun onTabReselected(position: Int) {
                onTabSelected(position)
            }
        })
    }

    private fun selectTab(tab: String){
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments

        Log.d("Tabs", "fragments: $fragments")

        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }

        val newFragment = fm.findFragmentByTag(tab)

        Log.d("Tabs", "new fragment: $newFragment")

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return

        val transaction = fm.beginTransaction()

        if (newFragment == null) {
            transaction.add(
                R.id.fragment_container,
                Tab(tab).createFragment(fm.fragmentFactory), tab
            )
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }

        transaction.commitNow()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()) {
            return
        } else {
            router.exit()
        }
    }
}