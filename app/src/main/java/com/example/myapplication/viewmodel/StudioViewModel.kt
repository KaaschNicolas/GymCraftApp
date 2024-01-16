package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Studio
import com.example.myapplication.repositories.StudioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudioViewModel @Inject constructor(
        private val studioRepository: StudioRepository,
    ) : ViewModel() {
        fun getStudio(id: Int): Studio {
            return studioRepository.getOneById(id)
        }

    }