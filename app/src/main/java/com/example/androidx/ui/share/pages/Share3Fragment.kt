package com.example.androidx.ui.share.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.androidx.*
import com.example.androidx.ARGUMENT_KEY_1
import com.example.androidx.ui.share.ShareViewModel

class Share3Fragment : Fragment() {

    private val viewModel: ShareViewModel by navGraphViewModels(R.id.main_navigation)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_share, container, false)

        // Override back pressed event
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.decrementCount()
            findNavController().popBackStack()
        }

        // Listen for Fragment request result
        setFragmentResultListener(REQUEST_KEY_2) { requestKey, bundle ->
            val result = bundle.getInt(ARGUMENT_KEY_2)
            Log.d(TAG, "Request result: $result")
        }

        // ViewModel
        viewModel.text.observe(viewLifecycleOwner) {
            root.findViewById<TextView>(R.id.text_share).text = it
        }

        root.findViewById<Button>(R.id.button_next).setOnClickListener {
            viewModel.incrementCount()
            findNavController().navigate(Share3FragmentDirections.toShare4())
        }

        return root
    }
}