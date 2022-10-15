package com.juno.mycrypto.presentation.components

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.juno.mycrypto.R
import com.juno.mycrypto.databinding.ActivityMainBinding
import com.juno.mycrypto.mvvm.viewmodels.CryptoEmptyStateViewModel
import com.juno.mycrypto.mvvm.viewmodels.CryptoValueStateViewModel
import com.juno.mycrypto.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CryptoHomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    companion object {
        val TAG: String = this::class.java.name
    }
}