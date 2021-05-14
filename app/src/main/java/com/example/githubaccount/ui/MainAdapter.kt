package com.example.githubaccount.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.User
import com.example.githubaccount.R
import com.example.githubaccount.databinding.ItemListUserBinding
import com.example.githubaccount.util.loadImage
import timber.log.Timber

class MainAdapter : RecyclerView.Adapter<MainAdapter.UserViewHolder>() {

    var onItemClick: ((User) -> Unit)? = null

    private val mData = ArrayList<User>()

    fun setData(newListData: List<User>?) {
        if (newListData == null) return
        mData.clear()
        mData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainAdapter.UserViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int {
        Timber.d("check size data : ${mData.size}")
        return mData.size
    }

    override fun onBindViewHolder(holder: MainAdapter.UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }



    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListUserBinding.bind(itemView)
        fun bind(data: User) {
            with(binding) {

                Timber.d("check isinya : ${data.login}")
                tvNameUser.text = data.login
                tvIdUser.text = data.id.toString()
                ivProfilepic.loadImage(itemView.context,data.avatarUrl)
                Timber.d("checkhcekcheck")
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}