package by.dro.testapp.fileexplorer.ui

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.dro.testapp.fileexplorer.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.ktx.withPermissionsCheck

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private val doubleBackTask = Runnable{ doubleBackToExitPressedOnce = false }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()

    }

    private fun checkPermissions(){

        withPermissionsCheck(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            onNeverAskAgain = {},
            onPermissionDenied = { Log.d("kkk", " denied")},
            onShowRationale = {it.proceed()}
        ) {
          initViewPager()
        }

    }

    private fun initViewPager(){
        viewPager.adapter = ScreenPagerAdapter(supportFragmentManager, lifecycle)

        val listTitle = listOf(
            getString(R.string.photo_title),
            getString(R.string.video_title),
            getString(R.string.all_title))


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = listTitle[position]
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {

            super.onBackPressed()

        } else{

            doubleBackToExitPressedOnce = true
            Toast.makeText(this, getString(R.string.back), Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(doubleBackTask, 2000)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return true
    }
}