package by.dro.testapp.fileexplorer.ui

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import by.dro.testapp.fileexplorer.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.ktx.withPermissionsCheck

class MainActivity : AppCompatActivity() {


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
        val listFragments = supportFragmentManager.fragments

//        var handled = false

        listFragments.forEach {
            if (it is BaseExplorerFragment){
                /*handled = */it.onBackPressed()

//                if (handled)  return
            }
        }

//        if (!handled) super.onBackPressed()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return true
    }
}