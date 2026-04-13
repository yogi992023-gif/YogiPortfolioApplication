package com.yogi.driverapp.di

import android.content.Context
import com.yogi.driverapp.data.datasource.DriverFirebaseDataSource
import com.yogi.driverapp.data.location.LocationHelper
import com.yogi.driverapp.data.remote.ApiService
import com.yogi.driverapp.data.remote.DirectionsApi
import com.yogi.driverapp.data.remote.ProductService
import com.yogi.driverapp.data.repository.LocationRepositoryImpl
import com.yogi.driverapp.data.repository.PostRepositoryImpl
import com.yogi.driverapp.data.repository.ProductRepositoryImpl
import com.yogi.driverapp.domain.repository.LocationRepositoy
import com.yogi.driverapp.domain.repository.PostRepository
import com.yogi.driverapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("PostRetrofit")
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @Named("productRetrofit")
    fun provideProductRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providePostApi(@Named("PostRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: ApiService): PostRepository {
        return PostRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProductApi(@Named("productRetrofit") retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun productRepository(api: ProductService): ProductRepository {
        return ProductRepositoryImpl(api)
    }

    @Provides
    @Singleton
    @Named("MapRetrofit")
    fun provideMapRetrofit( client: OkHttpClient ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideDirectionsApi(@Named("MapRetrofit") retrofit: Retrofit): DirectionsApi {
        return retrofit.create(DirectionsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseDataSource(): DriverFirebaseDataSource {
        return DriverFirebaseDataSource()
    }

    @Provides
    @Singleton
    fun provideLocationHelper(@ApplicationContext context: Context): LocationHelper {
        return LocationHelper(context)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(
        api: DirectionsApi,
        locationHelper: LocationHelper,
        dataSource: DriverFirebaseDataSource
    ): LocationRepositoy {
        // Pass the injected parameters here
        return LocationRepositoryImpl(api, locationHelper, dataSource)
    }

}
