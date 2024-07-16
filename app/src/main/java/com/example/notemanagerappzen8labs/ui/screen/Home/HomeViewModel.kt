package com.example.notemanagerappzen8labs.ui.screen.Home

import androidx.lifecycle.ViewModel
import com.example.notemanagerappzen8labs.repositories.Api
import com.example.notemanagerappzen8labs.repositories.MainLog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val log: MainLog?,
    private val api: Api?
):ViewModel(){
}