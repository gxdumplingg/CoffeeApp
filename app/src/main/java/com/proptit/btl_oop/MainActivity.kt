package com.proptit.btl_oop

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.proptit.btl_oop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.drawerLayout)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.drawerNavigation.setNavigationItemSelectedListener { menuItem ->
            binding.drawerLayout.closeDrawers()
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    navController.navigate(R.id.profileFragment)
                    false
                }
                R.id.nav_order -> {
                    navController.navigate(R.id.orderHistoryFragment)
                    false
                }

                R.id.nav_logout -> {
                    Firebase.auth.signOut()
//                    Log.e("Logout", "${auth.currentUser}")
                    navController.navigate(R.id.action_homeScreenFragment_to_signInScreenFragment)

                    true
                }

                else -> false
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signInScreenFragment,
                R.id.signUpScreenFragment,
                R.id.forgotPasswordFragment,
                R.id.coffeeDetailsFragment,
                R.id.beanDetailsFragment,
                R.id.addToCartFragment,
                R.id.detailsOrderHistoryFragment,
                R.id.profileFragment
                -> hideBottomNavigation()

                else -> showBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }
}