package com.example.projetcubemobile

import android.opengl.Visibility
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.projetcubemobile.databinding.ActivityMainBinding
import com.example.projetcubemobile.tools.FragmentsTools
import com.example.projetcubemobile.ui.Fragments.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var bottomNav: BottomNavigationView

    private lateinit var fragmentTool: FragmentsTools

    private lateinit var appBarLayout: AppBarLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Singleton.mainActivity = this;

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        this.fragmentTool = FragmentsTools(this);

        this.appBarLayout = this.findViewById(R.id.id_activity_main_appBar)

        this.fragmentTool.loadFragment(HomeFragment())
        bottomNav = this.findViewById(R.id.Nav_Menu_Down) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_menu_down_button_home -> {
                    this.fragmentTool.loadFragment(HomeFragment())
                    true
                }
                R.id.nav_menu_down_button_loupe -> {
                    this.fragmentTool.loadFragment(WenFragment())
                    true
                }
                R.id.nav_menu_down_button_personne -> {
                    this.fragmentTool.loadFragment(SettingsFragment())
                    true
                }
                R.id.nav_menu_down_button_mail -> {
                    this.fragmentTool.loadFragment(MailFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navmenu_down, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }

    fun changAppBarVisibility() {
        if (this.appBarLayout.visibility == View.VISIBLE) {
            this.appBarLayout.visibility = View.GONE
        } else {
            this.appBarLayout.visibility = View.VISIBLE
        }
    }
}