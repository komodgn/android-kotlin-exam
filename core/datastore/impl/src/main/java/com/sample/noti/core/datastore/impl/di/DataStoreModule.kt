package com.sample.noti.core.datastore.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sample.noti.core.datastore.api.datasource.TokenDataSource
import com.sample.noti.core.datastore.impl.datasource.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private val Context.tokenDataSource by preferencesDataStore(name = "TOKEN_DATASTORE")

    @TokenDataStore
    @Singleton
    @Provides
    fun provideTokenDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.tokenDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreBindModule {

    @Binds
    @Singleton
    abstract fun bindTokenDataSource(
        tokenDataSourceImpl: TokenDataSourceImpl
    ): TokenDataSource

}
