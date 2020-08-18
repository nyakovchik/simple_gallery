package by.dro.testapp.fileexplorer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.util.getInternalStoragePath
import by.dro.testapp.fileexplorer.util.getSDCardPath
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = ScreenPagerAdapter(supportFragmentManager, lifecycle)

        Log.d("kkk", "internal = ${this.getInternalStoragePath()}, external = ${this.getSDCardPath()}")
    }
}