package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import by.dro.testapp.fileexplorer.model.MediaFile


class VideoViewModel(application: Application): FileViewModel(application) {

    override val selection: String? = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

    override val _listMedia = MutableLiveData<List<MediaFile>>()

    init {
        queryMediaStorage(application)
    }
}