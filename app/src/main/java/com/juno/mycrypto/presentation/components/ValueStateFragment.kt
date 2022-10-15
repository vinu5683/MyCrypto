package com.juno.mycrypto.presentation.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.juno.mycrypto.R
import com.juno.mycrypto.mvvm.viewmodels.CryptoValueStateViewModel
import com.juno.mycrypto.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ValueStateFragment : Fragment() {

    private val valueViewModel: CryptoValueStateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_value_state, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        valueViewModel.getValueStateData()
        valueViewModel.cryptoValueStateData.observe(viewLifecycleOwner) {
            it?.let { resourceWrapper ->
                when (resourceWrapper.status) {
                    Status.SUCCESS -> {
                        Log.d(CryptoHomeActivity.TAG, "Success: Value Data-> ${resourceWrapper.data}\n\n\n")
                    }
                }
            }
        }
    }
}