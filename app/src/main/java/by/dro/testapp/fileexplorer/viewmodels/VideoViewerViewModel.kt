package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class VideoViewerViewModel(application: Application): AndroidViewModel(application) {

    val state = MutableLiveData<VideoState>()

    enum class VideoState{
        INIT, PLAY, PAUSE, END
    }

}