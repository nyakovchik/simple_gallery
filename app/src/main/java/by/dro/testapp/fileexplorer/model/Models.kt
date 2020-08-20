package by.dro.testapp.fileexplorer.model

data class MediaFolder(
    val path: String,
    val name: String,
    val firstImg: String,
    val elementsCount: Int
)


data class MediaFile(val path: String)

//data class MediaImg(override val path: String): MediaFile