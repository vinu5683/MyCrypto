package com.juno.mycrypto.presentation.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.juno.mycrypto.R
import com.juno.mycrypto.mvvm.viewmodels.CryptoEmptyStateViewModel
import com.juno.mycrypto.mvvm.viewmodels.CryptoValueStateViewModel
import com.juno.mycrypto.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CryptoHomeActivity : AppCompatActivity() {

    private val valueViewModel: CryptoValueStateViewModel by viewModels()
    private val emptyViewModel: CryptoEmptyStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valueViewModel.getValueStateData()
        emptyViewModel.getEmptyStateData()

        valueViewModel.cryptoValueStateData.observe(this) {
            it?.let { resourceWrapper ->
                when (resourceWrapper.status) {
                    Status.SUCCESS -> {
                        Log.d(TAG, "Success: Value Data-> ${resourceWrapper.data}\n\n\n")
                    }
                }
            }
        }

        emptyViewModel.cryptoEmptyStateData.observe(this) {
            it?.let { resourceWrapper ->
                when (resourceWrapper.status) {
                    Status.SUCCESS -> {
                        Log.d(TAG, "Success: Empty Data-> ${resourceWrapper.data}\n\n\n")
                    }
                }
            }
        }


    }

    companion object{
        val TAG: String = this::class.java.name
    }
}