package com.sample.noti.core.data.impl.di

import com.sample.noti.core.data.api.repository.AuthRepository
import com.sample.noti.core.data.api.repository.CatFactsRepository
import com.sample.noti.core.data.api.repository.RemoteConfigRepository
import com.sample.noti.core.data.api.repository.UserRepository
import com.sample.noti.core.data.impl.repository.AuthRepositoryImpl
import com.sample.noti.core.data.impl.repository.CatFactsRepositoryImpl
import com.sample.noti.core.data.impl.repository.RemoteConfigRepositoryImpl
import com.sample.noti.core.data.impl.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindRemoteConfigRepository(remoteConfigRepositoryImpl: RemoteConfigRepositoryImpl): RemoteConfigRepository

    @Binds
    @Singleton
    abstract fun bindCatFactsRepository(catFactsRepositoryImpl:CatFactsRepositoryImpl): CatFactsRepository
}