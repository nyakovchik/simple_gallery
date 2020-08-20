package by.dro.testapp.fileexplorer.ui


import androidx.fragment.app.activityViewModels
import by.dro.testapp.fileexplorer.viewmodels.VideoViewModel


class VideoExplorerFragment : BaseExplorerFragment() {
    override val viewModel: VideoViewModel by activityViewModels()
}