package by.dro.testapp.fileexplorer.ui


import androidx.fragment.app.activityViewModels
import by.dro.testapp.fileexplorer.viewmodels.AllFileViewModel



class AllFilesExplorerFragment : BaseExplorerFragment() {
    override val viewModel: AllFileViewModel by activityViewModels()

}