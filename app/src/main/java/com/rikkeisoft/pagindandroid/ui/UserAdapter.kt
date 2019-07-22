package com.rikkeisoft.pagindandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rikkeisoft.pagindandroid.R
import com.rikkeisoft.pagindandroid.data.model.User
import kotlinx.android.synthetic.main.item_loading.view.progressLoading
import kotlinx.android.synthetic.main.item_user.view.UserAvatar
import kotlinx.android.synthetic.main.item_user.view.UserName

class UserAdapter : PagedListAdapter<User, ViewHolder>(UserDiffCallback) {

    private var isLoading = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            1 -> LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            )
            else -> UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            1 -> (holder as LoadingViewHolder).onBind()
            else -> (holder as UserViewHolder).onBind(getItem(position), position)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (isLoading && position == itemCount - 1) {
            true -> 1
            else -> 0
        }

    fun setLoading(isLoading: Boolean) {
        val previousState = isLoading
        this.isLoading = isLoading
        if (isLoading != previousState) {
            if (!isLoading) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        }
        notifyItemInserted(super.getItemCount())
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(user: User?, position: Int) {
            Glide.with(itemView)
                .load(user?.avatarUrl)
                .into(itemView.UserAvatar)
            itemView.UserName.text = user?.login
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {
            itemView.progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}
