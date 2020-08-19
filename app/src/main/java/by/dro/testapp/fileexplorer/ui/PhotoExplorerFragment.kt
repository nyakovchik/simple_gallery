package by.dro.testapp.fileexplorer.ui

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.util.getMimeType
import by.dro.testapp.fileexplorer.viewmodels.PhotoViewModel
import kotlinx.android.synthetic.main.fragment_photo_explorer.*
import java.io.File

class PhotoExplorerFragment : Fragment(R.layout.fragment_photo_explorer) {

    private val viewModel: PhotoViewModel by activityViewModels()
    private val adapter = FileAdapter{
        if (it.isDirectory) viewModel.openInternal(it.absolutePath)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        viewModel.path.observe(viewLifecycleOwner, Observer {

            Log.d("kkk", "internal - $it")

            if (it == null){
                openRoot()
                return@Observer
            }

            File(it).list()?.forEach {p ->
                Log.d("kkk", "path - $p")
            }

            File(it).listFiles()?.toList()?.filter {file ->
                file.isDirectory || file.path.getMimeType().contains("image")
            }.also {array ->
                adapter.submitList(array)
            }


        })

        openRoot()
    }

    private fun openRoot(){

        val list = mutableListOf<File>()

        if (viewModel.internalPath != "") list.add(File(viewModel.internalPath))

        if (viewModel.sdCardPath != "") list.add(File(viewModel.sdCardPath))

        adapter.submitList(list)
    }

    private fun initRecycler(){

        photoRecycler.layoutManager = getLayoutManager(requireContext())
        photoRecycler.adapter = adapter

    }

    private fun getLayoutManager(context: Context): RecyclerView.LayoutManager {

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int = size.x
//        val height: Int = size.y

        val spanCount = (width / context.resources.getDimension(R.dimen.file_vh_width)).toInt()

        return GridLayoutManager(context, spanCount)
    }
}