package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import by.dro.testapp.fileexplorer.util.getMimeType
import java.io.File

class PhotoViewModel(application: Application): FileViewModel(application) {

    override fun filter(file: File) = file.isDirectory || file.path.getMimeType().contains("image")
}