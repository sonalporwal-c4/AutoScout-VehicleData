package com.android.task.data.di

import com.android.task.data.network.VehicleAPI
import com.android.task.data.repository.VehicleDataSource
import com.android.task.data.repository.VehicleDataSourceImpl
import com.android.task.data.repository.VehicleRepositoryInterfaceImpl
import com.android.task.domain.repository.VehicleRepositoryInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object HiltDependenciesModule {

    /**
     * Returns the [HttpLoggingInterceptor] instance with logging level set to body
     */
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     * Provides an [OkHttpClient]
     * @param loggingInterceptor [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideOKHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient().apply {
        OkHttpClient.Builder().run {
            addInterceptor(loggingInterceptor)
            build()
        }
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    /**
     * Returns a [MoshiConverterFactory] instance
     */
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    /**
     * Returns an instance of the [VehicleAPI] interface for the retrofit class
     * @return [VehicleAPI] impl
     */
    @Provides
    fun provideRetrofitInstance(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): VehicleAPI =
        Retrofit.Builder().run {
            baseUrl("http://private-fe87c-simpleclassifieds.apiary-mock.com")
            addConverterFactory(ScalarsConverterFactory.create())
            addConverterFactory(gsonConverterFactory)
            client(client)
            build()
        }.run {
            create(VehicleAPI::class.java)
        }


    /**
     * Returns a [VehicleDataSource] impl
     * @param retrofitClient [VehicleAPI] retrofit interface
     */
    @Provides
    fun providesRetrofitService(retrofitClient: VehicleAPI): VehicleDataSource =
        VehicleDataSourceImpl(retrofitClient)

}