package by.dro.testapp.fileexplorer.util


import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import by.dro.testapp.fileexplorer.model.MediaFile


fun queryImageStorage(context: Context): List<MediaFile> {

    val result = mutableListOf<MediaFile>()

    val imageProjection = arrayOf(
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE,
        MediaStore.Images.Media.DATE_TAKEN,
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATA
    )


    val imageSortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

    val cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        imageProjection,
        null,
        null,
        imageSortOrder
    )

    cursor.use {
        it?.let {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val dateColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val size = it.getString(sizeColumn)
                val date = it.getString(dateColumn)
                val data = it.getString(dataColumn)

                result.add(MediaFile(data, MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE))

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

    return result
}

fun queryVideoStorage(context: Context): List<MediaFile> {

    val result = mutableListOf<MediaFile>()

    val imageProjection = arrayOf(
        MediaStore.Video.Media.DISPLAY_NAME,
        MediaStore.Video.Media.SIZE,
        MediaStore.Video.Media.DATE_TAKEN,
        MediaStore.Video.Media._ID,
        MediaStore.Video.Media.DATA
    )


    val imageSortOrder = "${MediaStore.Video.Media.DATE_TAKEN} DESC"

    val cursor = context.contentResolver.query(
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
        imageProjection,
        null,
        null,
        imageSortOrder
    )

    cursor.use {
        it?.let {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
            val dateColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val size = it.getString(sizeColumn)
                val date = it.getString(dateColumn)
                val data = it.getString(dataColumn)

                result.add(MediaFile(data, MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO))

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

    return result
}

fun queryMediaStorage(context: Context): List<MediaFile> {

    val result = mutableListOf<MediaFile>()

    val imageProjection = arrayOf(
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.SIZE,
        MediaStore.Files.FileColumns.DATE_TAKEN,
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DATA,
        MediaStore.Files.FileColumns.MEDIA_TYPE
    )

    val selection = (MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
            + " OR "
            + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
            + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)

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
                val date = it.getString(dateColumn)
                val data = it.getString(dataColumn)
                val mediaType = it.getInt(mediaTypeColumn)

                result.add(MediaFile(data, mediaType))

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

    return result
}