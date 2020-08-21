package by.dro.testapp.fileexplorer.ui.explorers

import androidx.fragment.app.activityViewModels
import by.dro.testapp.fileexplorer.viewmodels.PhotoViewModel


class PhotoExplorerFragment : BaseExplorerFragment() {

    override val viewModel: PhotoViewModel by activityViewModels()

}