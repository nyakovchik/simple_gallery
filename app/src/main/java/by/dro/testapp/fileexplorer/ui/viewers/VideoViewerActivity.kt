package by.dro.testapp.fileexplorer.ui.viewers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.viewmodels.VideoViewerViewModel
import by.dro.testapp.fileexplorer.viewmodels.VideoViewerViewModel.VideoState.*
import kotlinx.android.synthetic.main.activity_video_viewer.*
import java.lang.IllegalArgumentException

class VideoViewerActivity : AppCompatActivity(R.layout.activity_video_viewer) {

    private val viewModel: VideoViewerViewModel by viewModels()

    private var path: String? = null

    companion object{

        private const val ARG_PATH = "ARG_PATH"

        @JvmStatic
        fun newIntent(context: Context?, path: String) = Intent(context, VideoViewerActivity::class.java).apply {
            putExtra(ARG_PATH, path)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        viewModel.state.observe(this, Observer {
            when(it){
                INIT -> init()
                PLAY -> play()
                PAUSE -> pause()
                END -> init()
                else -> throw IllegalArgumentException()
            }
        })

        videoView.setOnCompletionListener {
            viewModel.state.value = END
        }

        videoLayout.setOnClickListener {
            viewModel.state.value = PLAY
        }


        path = intent.getStringExtra(ARG_PATH)

        viewModel.state.value = INIT

    }

    private fun init(){
        videoView.setVideoPath(path)
        videoView.seekTo( 1 )
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun play(){
        videoView.start()
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun pause(){
        videoView.pause()
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }


    override fun onResume() {
        super.onResume()
        if (!videoView.isPlaying) videoView.seekTo( 1 )
    }


    override fun onBackPressed() {
        when(viewModel.state.value) {
            PLAY -> return
            INIT -> super.onBackPressed()
            else -> viewModel.state.value = INIT
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            viewModel.state.value = PAUSE
            true
        }
        else super.onKeyDown(keyCode, event)
    }
}