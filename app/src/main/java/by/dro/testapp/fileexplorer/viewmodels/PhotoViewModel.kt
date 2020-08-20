package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import by.dro.testapp.fileexplorer.model.MediaFile
import by.dro.testapp.fileexplorer.util.queryImageStorage

class PhotoViewModel(application: Application): FileViewModel(application) {

    override val _listMedia = MutableLiveData<List<MediaFile>>()

    init {
        val list = queryImageStorage(application)
        _listMedia.value = list
    }
}