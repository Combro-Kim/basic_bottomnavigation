package com.example.poin_df

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Transformations.map
import com.example.poin_df.category.CategoryFragment
import com.example.poin_df.event.EventFragment
import com.example.poin_df.home.HomeFragment
import com.example.poin_df.mypage.MyPageFragment
import com.example.poin_df.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource

class MainActivity : AppCompatActivity(),OnMapReadyCallback  {

    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val mapView: MapView by lazy {
        findViewById(R.id.mapView)
    }
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        mapView?.invalidate()

/*        val homeFragment = HomeFragment()
        val categoryFragment = CategoryFragment()
        val eventFragment = EventFragment()
        val myPageFragment = MyPageFragment()
        val searchFragment = SearchFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        replaceFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.category -> replaceFragment(categoryFragment)
                R.id.mypage -> replaceFragment(myPageFragment)
                R.id.event -> replaceFragment(eventFragment)
                R.id.search -> replaceFragment(searchFragment)
            }
            true
        }*/

    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map
        //줌 설정
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        //처음 화면 위치 고정 (강남대 : 37.271800, 127.127577)
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.271800, 127.127577))
        naverMap.moveCamera(cameraUpdate)

        val uiSettings =naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        locationSource = FusedLocationSource(this@MainActivity, LOCATION_PERMISSION_REQUEST_CODE)
        naverMap.locationSource = locationSource

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode != LOCATION_PERMISSION_REQUEST_CODE){
            return
        }
        if (locationSource.onRequestPermissionsResult(requestCode,permissions,grantResults)){
            if(!locationSource.isActivated){
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

    // FragmentContainer
/*    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }*/

/*    override fun onStart(){
        super.onStart()

        if (auth.currentUser == null){                                          //로그인이 되어있지않다면
            startActivity(Intent(this, LoginActivity::class.java)) //로그인 액티비티로 이동
        }
    }*/
}