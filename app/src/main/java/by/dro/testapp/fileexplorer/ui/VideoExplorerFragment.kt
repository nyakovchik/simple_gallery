package by.dro.testapp.fileexplorer.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.viewmodels.VideoViewModel



class VideoExplorerFragment : BaseExplorerFragment() {
    override val viewModel: VideoViewModel by activityViewModels()

}