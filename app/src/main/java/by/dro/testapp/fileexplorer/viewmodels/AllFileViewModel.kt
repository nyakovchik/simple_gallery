package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import by.dro.testapp.fileexplorer.model.MediaFile
import by.dro.testapp.fileexplorer.util.queryMediaStorage

class AllFileViewModel(application: Application): FileViewModel(application) {

    override val _listMedia = MutableLiveData<List<MediaFile>>()


    init {
        val list = queryMediaStorage(application)
        _listMedia.value = list
    }
}