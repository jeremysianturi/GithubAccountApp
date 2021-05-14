package com.example.githubaccount.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Resource
import com.example.core.domain.model.User
import com.example.core.utils.ErrorMessageSplit
import com.example.githubaccount.R
import com.example.githubaccount.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName.toString()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    private val userViewModel: MainViewModel by viewModels()


    var page = 1
    var isLoading = false
    var limit = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //biar keyboard ga lgsg popup
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // search
        binding.layoutToolbar.edtSearch.doOnTextChanged { text, start, before, count ->
            userViewModel.searchQuery.value = text.toString()
        }

        // method
        setupObserver()
        buildList()


    }

    private fun setupObserver() {

        userViewModel.getUser().observe(this, { data ->
            Timber.tag(tag).d("observer_user $data")
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.progressBarProposal.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBarProposal.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_user_adapter ${data.data}")
                    }
                    is Resource.Error -> {
                        binding.progressBarProposal.visibility = View.GONE
                        val message = ErrorMessageSplit.message(data.message.toString())
                        val code = ErrorMessageSplit.code(data.message.toString())
                        Timber.d("check errornya dimana ya : $message dan $code")

                    }
                }

            }
        })

        userViewModel.search.observe(this, { data ->
            adapter.setData(data)

        })
    }

    private fun buildList() {

        adapter = MainAdapter()
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUser.adapter = adapter

        binding.rvUser.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

//        binding.rvUser.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                val visibleItemCount = (binding.rvUser.layoutManager as LinearLayoutManager).childCount
//                val pastVisibleItem =
//                    (binding.rvUser.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
//                val total = adapter.itemCount
//
//                if (binding.progressBarProposal.visibility == View.GONE){
//
//                    if ((visibleItemCount + pastVisibleItem) >= total){
//                        page++
//                        getPage()
//                    }
//
//                }
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
    }


//    fun getPage(){
//        binding.progressBarProposal.visibility = View.VISIBLE
//
//        val start = (page -1) *limit
//        val end = page * limit
//
//    }
}