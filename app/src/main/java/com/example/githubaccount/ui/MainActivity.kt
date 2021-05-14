package com.example.githubaccount.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.githubaccount.databinding.ActivityMainBinding
import com.example.githubaccount.util.ErrorBottomSheet
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
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setData(data.data)
                        Timber.tag(tag).d("observer_user_adapter ${data.data}")
                        binding.emptyData.root.visibility =
                            if (data.data?.isNotEmpty() == true) View.GONE else View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        val message = "Something wrong!"
                        ErrorBottomSheet.instance(message)
                            .show(supportFragmentManager, ErrorBottomSheet.TAG)

                    }
                }

            }
        })

        userViewModel.search.observe(this, { data ->
            adapter.setData(data)

            if (data.isEmpty()){
                binding.emptyData.root.visibility =
                    if (data.isNotEmpty()) View.GONE else View.VISIBLE
            }

        })
    }

    private fun buildList() {

        adapter = MainAdapter()
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        (binding.rvUser.layoutManager as LinearLayoutManager).scrollToPosition(Integer.MAX_VALUE/2)
        binding.rvUser.adapter = adapter

        binding.rvUser.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

    }


}