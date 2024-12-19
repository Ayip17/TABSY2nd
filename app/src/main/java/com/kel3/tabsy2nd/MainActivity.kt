package com.kel3.tabsy2nd

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Check if user is logged in
        if (FirebaseAuth.getInstance().currentUser  == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Close MainActivity
            return
        }

        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        val berandaFragment = BerandaFragment()
        val reservasiFragment = ReservasiFragment()
        val favoritFragment =FavoritFragment()
        val profilFragment = ProfilFragment()

        setCurrentFragment(berandaFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.icBeranda -> setCurrentFragment(berandaFragment)
                R.id.icReservasi -> setCurrentFragment(reservasiFragment)
                R.id.icFavorit-> setCurrentFragment(favoritFragment)
                R.id.icProfil -> setCurrentFragment(profilFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}