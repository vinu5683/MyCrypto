package com.juno.mycrypto.presentation.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.juno.mycrypto.R
import com.juno.mycrypto.databinding.FragmentCryptoHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoHomeFragment : Fragment() {

    lateinit var binding: FragmentCryptoHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCryptoHomeBinding.inflate(layoutInflater, container, false)
        binding.btnEmptyStateCrypto.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_cryptoHomeFragment_to_emptyStateFragment)
        }

        binding.btnValueStateCrypto.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_cryptoHomeFragment_to_valueStateFragment)
        }

        return binding.root
    }


}