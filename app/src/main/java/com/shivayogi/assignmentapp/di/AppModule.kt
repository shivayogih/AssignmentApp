package com.shivayogi.assignmentapp.di

import com.shivayogi.assignmentapp.data.remote.AssignmentAPI
import com.shivayogi.assignmentapp.repository.AssignmentRepository
import com.shivayogi.assignmentapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAssignmentRepository(
        api: AssignmentAPI
    ) = AssignmentRepository(api)

    @Singleton
    @Provides
    fun provideAssignmentApi(): AssignmentAPI {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).client(client).build().create(AssignmentAPI::class.java)
    }
}