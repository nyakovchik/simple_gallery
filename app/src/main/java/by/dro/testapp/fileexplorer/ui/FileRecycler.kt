package by.dro.testapp.fileexplorer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.dro.testapp.fileexplorer.R
import by.dro.testapp.fileexplorer.util.fromFile
import by.dro.testapp.fileexplorer.util.fromRes
import kotlinx.android.synthetic.main.view_holder_file.view.*
import java.io.File


class FileAdapter(var click: (file: File) -> Unit): ListAdapter<File, FileVH>(DIFF_CALLBACK){
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

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<File>() {
    override fun areItemsTheSame(oldItem: File, newItem: File): Boolean =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: File, newItem: File): Boolean =
        oldItem.absolutePath == newItem.absolutePath && oldItem.length() == newItem.length()
}

class FileVH(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.view_holder_file, parent, false)
) {

    fun bind(file: File) {

        if (file.isDirectory){
            itemView.imageView.fromRes(R.drawable.ic_dossier)
        } else{
            itemView.imageView.fromFile(file)
        }

        itemView.textView.text = file.name
    }

}