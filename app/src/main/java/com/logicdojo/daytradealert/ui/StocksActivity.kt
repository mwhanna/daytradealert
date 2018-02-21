package com.logicdojo.daytradealert.ui

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.logicdojo.daytradealert.R
import com.logicdojo.daytradealert.model.ExchangeType
import kotlinx.android.synthetic.main.activity_stocks.*

/**
 * Activity for stocks list
 **/
class StocksActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val stockListFragment : Fragment = StockListFragment()
        fragmentManager.beginTransaction().add(R.id.list_fragment_container, stockListFragment).commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.stocks_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_portfolio -> {
                val portfolioFragment = PortfolioFragment()
                fragmentManager.beginTransaction().add(R.id.list_fragment_container, portfolioFragment).commit()
            }
            R.id.nav_canada_mj -> {
                val stockListFragment = StockListFragment.newInstance(ExchangeType.TSX)
                fragmentManager.beginTransaction().add(R.id.list_fragment_container, stockListFragment).commit()
            }
            R.id.nav_crypto -> {
                val cryptoListFragment = CryptoListFragment()
                fragmentManager.beginTransaction().add(R.id.list_fragment_container, cryptoListFragment).commit()
            }
            R.id.nav_nasdaq -> {
                val stockListFragment = StockListFragment.newInstance(ExchangeType.NASDAQ)
                fragmentManager.beginTransaction().add(R.id.list_fragment_container, stockListFragment).commit()
            }
            R.id.nav_buy -> {

            }
            R.id.nav_sell -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
