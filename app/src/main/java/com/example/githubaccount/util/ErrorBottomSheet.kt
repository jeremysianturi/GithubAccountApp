package com.example.githubaccount.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import com.example.githubaccount.databinding.ErrorBottomsheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ErrorBottomSheet : BottomSheetDialogFragment() {

    // ViewBinding Delegate
    private val binding: ErrorBottomsheetDialogBinding by viewBinding()

    private var codes: String? = ""

    companion object {

        const val TAG = "ERROR_BOTTOMSHEET"
        const val EXTRA_MESSAGE = "extra_message"

        fun instance(message: String): ErrorBottomSheet {

            // setup data code and message from activity
            val mBundle = Bundle()
            mBundle.putString(EXTRA_MESSAGE, message)

            //bind data to this bottomsheetFragment
            val fragment = ErrorBottomSheet()
            fragment.arguments = mBundle
            return fragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return binding.root //return root from binding delegation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // check error code
        binding.btnPositive.text = "OK"

        setupView()
    }


    private fun setupView() {
        binding.tvSubTitle.text = arguments?.getString(EXTRA_MESSAGE) ?: ""
    }

}