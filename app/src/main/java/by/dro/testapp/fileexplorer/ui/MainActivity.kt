package by.dro.testapp.fileexplorer.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.viewmodels.FileViewModel
import by.dro.testapp.fileexplorer.viewmodels.PhotoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import permissions.dispatcher.ktx.withPermissionsCheck

class MainActivity : AppCompatActivity() {

    private val viewModel: PhotoViewModel by viewModels()
    private val screenAdapter = ScreenPagerAdapter(supportFragmentManager, lifecycle)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissions()


    }

    private fun permissions(){

        withPermissionsCheck(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            onNeverAskAgain = {},
            onPermissionDenied = { Log.d("kkk", " denied")},
            onShowRationale = {it.proceed()}
        ) {
            viewPager.adapter = screenAdapter
        }

    }

    override fun onBackPressed() {
//        super.onBackPressed()

        viewModel.back()
    }
}