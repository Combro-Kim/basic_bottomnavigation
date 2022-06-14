package com.example.poin_df

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.poin_df.category.CategoryFragment
import com.example.poin_df.event.EventFragment
import com.example.poin_df.home.HomeFragment
import com.example.poin_df.mypage.MyPageFragment
import com.example.poin_df.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val auth:FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val categoryFragment = CategoryFragment()
        val eventFragment = EventFragment()
        val myPageFragment = MyPageFragment()
        val searchFragment = SearchFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        replaceFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                    R.id.category -> replaceFragment(categoryFragment)
                R.id.mypage -> replaceFragment(myPageFragment)
                R.id.event -> replaceFragment(eventFragment)
                R.id.search -> replaceFragment(searchFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }

    override fun onStart(){
        super.onStart()

        if (auth.currentUser == null){                                          //로그인이 되어있지않다면
            startActivity(Intent(this, LoginActivity::class.java)) //로그인 액티비티로 이동
        }
    }
}