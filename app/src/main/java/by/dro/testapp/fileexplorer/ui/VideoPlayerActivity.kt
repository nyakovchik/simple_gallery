package by.dro.testapp.fileexplorer.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import by.dro.testapp.fileexplorer.R
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : AppCompatActivity() {

    private var path: String? = null

    companion object{

        private const val ARG_PATH = "ARG_PATH"

        @JvmStatic
        fun newIntent(context: Context?, path: String) = Intent(context, VideoPlayerActivity::class.java).apply {
            putExtra(ARG_PATH, path)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)



        path = intent.getStringExtra(ARG_PATH)

        videoView.setVideoPath(path)

        videoView.setOnCompletionListener {

            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            Log.d("kkk", "setOnCompletionListener")
        }

        videoLayout.setOnClickListener {
            Log.d("kkk", "setOnClickListener")
            play()
        }

    }

    private fun play(){
        videoView.start()

        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onResume() {
        super.onResume()
        if (!videoView.isPlaying) videoView.seekTo( 1 )
    }


    override fun onBackPressed() {
        if (!videoView.isPlaying) super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            videoView.pause()
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
        
        return true
    }
}