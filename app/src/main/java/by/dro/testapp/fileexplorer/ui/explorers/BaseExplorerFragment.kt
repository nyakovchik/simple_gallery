package by.dro.testapp.fileexplorer.ui.explorers

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.ui.viewers.PhotoViewerActivity
import by.dro.testapp.fileexplorer.ui.viewers.VideoViewerActivity
import by.dro.testapp.fileexplorer.viewmodels.FileViewModel
import kotlinx.android.synthetic.main.fragment_base_explorer.*


abstract class BaseExplorerFragment : Fragment(R.layout.fragment_base_explorer) {

    abstract val viewModel: FileViewModel

    private val adapter = FileAdapter {

        when (it.mediaType) {

            MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO ->
                startActivity(
                    VideoViewerActivity.newIntent(
                        context,
                        it.path
                    )
                )

            MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE ->
                startActivity(
                    PhotoViewerActivity.newIntent(
                        context,
                        it.path
                    )
                )
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fileRecycler.layoutManager = getLayoutManager(requireContext())
        fileRecycler.adapter = adapter

        viewModel.listMedia.observe(viewLifecycleOwner, Observer {

            adapter.submitList(it)

        })


    }


    private fun getLayoutManager(context: Context): RecyclerView.LayoutManager {

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int = size.x

        val spanCount = (width / context.resources.getDimension(R.dimen.file_vh_width)).toInt()

        return GridLayoutManager(context, spanCount)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.sortByDate -> viewModel.sortBy { it.date }
            R.id.sortByName -> viewModel.sortBy { it.name }
        }

        return super.onOptionsItemSelected(item)
    }
}