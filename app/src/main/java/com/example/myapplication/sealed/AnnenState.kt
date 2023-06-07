package com.example.myapplication.sealed

import com.example.myapplication.model.Matris
import com.example.myapplication.model.User

sealed class AnnenState {
    class Success(val data: MutableList<Matris>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}