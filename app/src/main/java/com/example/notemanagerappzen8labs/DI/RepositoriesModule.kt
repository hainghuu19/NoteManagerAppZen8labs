package com.example.notemanagerappzen8labs.DI

import com.example.notemanagerappzen8labs.repositories.Api
import com.example.notemanagerappzen8labs.repositories.ApiImpl
import com.example.notemanagerappzen8labs.repositories.MainLog
import com.example.notemanagerappzen8labs.repositories.MainLogImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindMainLog(
        log: MainLogImpl
    ): MainLog

    @Binds
    @Singleton
    abstract fun bindApi(
        api : ApiImpl
    ): Api
}