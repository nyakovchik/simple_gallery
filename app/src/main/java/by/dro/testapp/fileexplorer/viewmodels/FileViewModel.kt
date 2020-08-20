package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.dro.testapp.fileexplorer.model.MediaFile


abstract class FileViewModel(application: Application) : AndroidViewModel(application) {

    abstract val _listMedia : MutableLiveData<List<MediaFile>>
    val listMedia: LiveData<List<MediaFile>>
        get() = _listMedia



}