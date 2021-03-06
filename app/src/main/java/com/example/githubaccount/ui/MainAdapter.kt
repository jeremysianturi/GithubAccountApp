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

class MainAdapter() : RecyclerView.Adapter<MainAdapter.UserViewHolder>() {

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
        if (mData.size == 0){
            return mData.size

        } else {
            return Integer.MAX_VALUE
        }
    }

    override fun onBindViewHolder(holder: MainAdapter.UserViewHolder, position: Int) {
        if (mData.size == 0){
//            holder.bind(mData[position])
        } else {
            val positionList = position % mData.size
            holder.bind(mData[positionList])
        }


    }



    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListUserBinding.bind(itemView)
        fun bind(data: User) {
            with(binding) {

                tvNameUser.text = data.login
                tvIdUser.text = data.id.toString()
                ivProfilepic.loadImage(itemView.context,data.avatarUrl)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(mData[adapterPosition])
            }
        }


    }

}