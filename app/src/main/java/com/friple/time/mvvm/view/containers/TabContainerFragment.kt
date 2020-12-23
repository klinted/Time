package com.friple.time.mvvm.view.containers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.friple.time.R
import com.friple.time.mvvm.common.Screens.Home
import com.friple.time.mvvm.common.Screens.Profile
import com.friple.time.mvvm.common.Screens.Stats
import com.friple.time.mvvm.common.navigation.BackButtonListener
import com.friple.time.mvvm.common.navigation.LocalCiceroneHolder
import com.friple.time.mvvm.common.navigation.RouterProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TabContainerFragment : DaggerFragment(), RouterProvider, BackButtonListener {

    // Creates navigator
    private val navigator: Navigator by lazy {
        AppNavigator(
            activity!!,
            R.id.ftc_container,
            childFragmentManager,
            childFragmentManager.fragmentFactory
        )
    }

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    // For check which tab opened
    private val containerName: String
        get() = arguments?.getString(EXTRA_NAME)!!

    private val cicerone: Cicerone<Router>
        get() = ciceroneHolder.getCicerone(containerName)

    override val router: Router
        get() = cicerone.router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "tab onBackPressed: $router")


        return inflater.inflate(R.layout.fragment_tab_container, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.ftc_container) == null) {
            when(containerName){
                // Home tab clicked
                "HOME" -> cicerone.router.replaceScreen(Home(containerName, 0))
                // Home tab clicked
                "STATS" -> cicerone.router.replaceScreen(Stats(containerName, 0))
                // Home tab clicked
                "PROFILE" -> cicerone.router.replaceScreen(Profile(containerName, 0))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.ftc_container)
        return if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()) {
            true
        } else {
            (activity as RouterProvider?)!!.router.exit()
            true
        }
    }

    companion object {
        private const val EXTRA_NAME = "tcf_extra_name"

        fun newInstance(name: String?) =
            TabContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_NAME, name)
                }
            }
    }
}