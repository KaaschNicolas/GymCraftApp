package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Eingangspunkt in die App
@HiltAndroidApp
class BaseApplication : Application()