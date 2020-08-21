package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.dro.testapp.fileexplorer.model.MediaFile
import kotlinx.coroutines.launch


abstract class FileViewModel(application: Application) : AndroidViewModel(application) {

    abstract val selection: String?

    abstract val _listMedia : MutableLiveData<List<MediaFile>>

    val listMedia: LiveData<List<MediaFile>>
        get() = _listMedia


    fun <R : Comparable<R>> sortBy(selector: (MediaFile) -> R?){
        viewModelScope.launch {
            val list = _listMedia.value
            _listMedia.value = list?.sortedBy(selector)
        }
    }

    fun queryMediaStorage(context: Context) {

        viewModelScope.launch {
            val result = mutableListOf<MediaFile>()

            val imageProjection = arrayOf(
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_TAKEN,
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.MEDIA_TYPE
            )

            val imageSortOrder = "${MediaStore.Video.Media.DATE_TAKEN} DESC"

            val queryUri: Uri = MediaStore.Files.getContentUri("external")

            val cursor = context.contentResolver.query(
                queryUri,
                imageProjection,
                selection,
                null,
                imageSortOrder
            )

            cursor.use {
                it?.let {
                    val idColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
                    val nameColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                    val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
                    val dateColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_TAKEN)
                    val dataColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
                    val mediaTypeColumn = it.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE)

                    while (it.moveToNext()) {
                        val id = it.getLong(idColumn)
                        val name = it.getString(nameColumn)
                        val size = it.getString(sizeColumn)
                        val date = it.getLong(dateColumn)
                        val data = it.getString(dataColumn)
                        val mediaType = it.getInt(mediaTypeColumn)

                        result.add(MediaFile(id, name, size, date, data, mediaType))

                        val contentUri = ContentUris.withAppendedId(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            id
                        )
                        // add the URI to the list
                        // generate the thumbnail
//                val thumbnail = context.contentResolver.loadThumbnail(contentUri, Size(480, 480), null)

                    }
                } ?: kotlin.run {
                    Log.e("TAG", "Cursor is null!")
                }
            }

            _listMedia.value = result
        }
    }

}