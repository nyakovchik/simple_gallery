package by.dro.testapp.fileexplorer.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import by.dro.testapp.fileexplorer.model.MediaFolder


fun getMediaPath(context: Context, mediaUri: Uri): List<MediaFolder>{

    val listFolders = mutableListOf<MediaFolder>()

    val fields = arrayOf(MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID)

    val cursor = context.contentResolver.query(mediaUri, fields, null, null, null)

    cursor?.also {
        try {

            cursor.moveToFirst()

            do {

                val name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                val folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                val dataPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))

                var folderPaths = dataPath.substring(0, dataPath.lastIndexOf(folder+"/"))
                folderPaths = "$folderPaths$folder/"


            }while (cursor.moveToNext())

        } finally {
            cursor.close()
        }
    }


    return listFolders
}