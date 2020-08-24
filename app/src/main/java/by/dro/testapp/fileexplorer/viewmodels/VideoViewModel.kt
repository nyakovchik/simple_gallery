package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import android.provider.MediaStore


class VideoViewModel(application: Application): FileViewModel(application) {

    override val selection: String? = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

    init {
        queryMediaStorage(application)
    }
}