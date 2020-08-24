package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import android.provider.MediaStore

private val selection: String? = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
        + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE)

class PhotoViewModel(application: Application): FileViewModel(application, selection)