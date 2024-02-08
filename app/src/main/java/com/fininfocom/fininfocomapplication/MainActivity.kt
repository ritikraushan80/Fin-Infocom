package com.fininfocom.fininfocomapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.fininfocom.fininfocomapplication.databinding.ActivityMainBinding
import com.fininfocom.fininfocomapplication.views.DashboardFragment
import com.fininfocom.fininfocomapplication.views.LogInActivity

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var toogle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    setFragment(DashboardFragment())

        binding.btmNavigation.setOnNavigationItemSelectedListener { menu ->

            when (menu.itemId) {

                R.id.user -> {
                    setFragment(DashboardFragment())
                    true
                }

                else -> false
            }
        }

        setSupportActionBar(binding.toolbar)
    }
    private fun setFragment(fr: Fragment) {
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.main, fr)
        frag.addToBackStack(null)
        frag.commit()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}
