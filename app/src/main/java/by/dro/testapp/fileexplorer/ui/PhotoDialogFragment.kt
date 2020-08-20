package by.dro.testapp.fileexplorer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import by.dro.testapp.fileexplorer.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog_fragment_photo.view.*


class PhotoDialogFragment: DialogFragment() {

    private var path: String? = null

    companion object {

        private const val ARG_PATH = "ARG_PATH"

        @JvmStatic
        fun newInstance(path: String) =
            PhotoDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PATH, path)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar)
         setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)

        arguments?.let {
            path = it.getString(ARG_PATH)
            Log.d("kkk", "path2 - $path")
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {

        Glide.with(requireActivity())
            .load(path)
            .into(view.fullPhoto)
    }
}