package by.dro.testapp.fileexplorer.ui.viewers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.dro.testapp.fileexplorer.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo_viewer.*

class PhotoViewerActivity : AppCompatActivity(R.layout.activity_photo_viewer) {

    private var path: String? = null

    companion object{

        private const val ARG_PATH = "ARG_PATH"

        @JvmStatic
        fun newIntent(context: Context?, path: String) = Intent(context, PhotoViewerActivity::class.java).apply {
            putExtra(ARG_PATH, path)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        path = intent.getStringExtra(ARG_PATH)

        Glide.with(this)
            .load(path)
            .into(fullPhoto)
    }
}