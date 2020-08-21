package by.dro.testapp.fileexplorer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.model.MediaFile
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_holder_file.view.*


class FileAdapter(var click: (file: MediaFile) -> Unit): ListAdapter<MediaFile, FileVH>(DIFF_CALLBACK){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileVH {
        return FileVH(parent)
    }

    override fun onBindViewHolder(holder: FileVH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewAttachedToWindow(holder: FileVH) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            click(getItem(holder.adapterPosition))
        }
    }

    override fun onViewDetachedFromWindow(holder: FileVH) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MediaFile>() {
    override fun areItemsTheSame(oldItem: MediaFile, newItem: MediaFile): Boolean =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: MediaFile, newItem: MediaFile): Boolean = oldItem.path == newItem.path
}

class FileVH(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.view_holder_file, parent, false)
) {

    fun bind(file: MediaFile) {

        Glide.with(itemView)
            .load(file.path)
            .apply(RequestOptions().centerCrop())
            .placeholder(R.drawable.ic_launcher_background)
            .into(itemView.imageView)

        itemView.name.text = file.name

    }

}