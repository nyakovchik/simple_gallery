package by.dro.testapp.fileexplorer.model

data class MediaFolder(
    val path: String,
    val name: String,
    val firstImg: String,
    val elementsCount: Int
)


data class MediaFile(val path: String, val mediaType: Int)

//data class MediaImg(override val path: String): MediaFile