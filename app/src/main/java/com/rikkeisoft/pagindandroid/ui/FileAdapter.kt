package com.rikkeisoft.pagindandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rikkeisoft.pagindandroid.R
import com.rikkeisoft.pagindandroid.data.model.FileModel
import kotlinx.android.synthetic.main.item_file.view.imageFile
import kotlinx.android.synthetic.main.item_file.view.textNameFile
import kotlinx.android.synthetic.main.item_folder.view.imageFolder
import kotlinx.android.synthetic.main.item_folder.view.textNameFolder

class FileAdapter(
    private val files: MutableList<FileModel>,
    private val listener: (fileModel: FileModel) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            1 -> FolderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_folder,
                    parent,
                    false
                )
            )
            else -> FileViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_file,
                    parent, false
                )
            )
        }

    fun update(newData: MutableList<FileModel>) {
        files.clear()
        files.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = files.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> (holder as FolderViewHolder).onBind(files[position])
            else -> (holder as FileViewHolder).onBind(files[position])
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (files[position].isFile) {
            true -> 1
            else -> 0
        }

    inner class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(fileModel: FileModel) {
            itemView.run {
                imageFolder.setImageResource(R.drawable.ic_folder)
                textNameFolder.text = fileModel.nameFile
            }
            itemView.setOnClickListener { listener(fileModel) }
        }
    }

    inner class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(fileModel: FileModel) {
            itemView.run {
                imageFile.setImageResource(R.drawable.ic_file)
                textNameFile.text = fileModel.nameFile
            }
            itemView.setOnClickListener { listener(fileModel) }
        }
    }
}
