package com.yogi.interviewproject.di

import android.content.Context
import com.yogi.interviewproject.data.datasource.FirebaseDataSourceTracking
import com.yogi.interviewproject.data.location.LocationHelper
import com.yogi.interviewproject.data.remote.ApiService
import com.yogi.interviewproject.data.remote.DirectionsApi
import com.yogi.interviewproject.data.remote.ProductService
import com.yogi.interviewproject.data.repository.LocationRepositoryImpl
import com.yogi.interviewproject.data.repository.PostRepositoryImpl
import com.yogi.interviewproject.data.repository.ProductRepositoryImpl
import com.yogi.interviewproject.domain.repository.LocationRepositoy
import com.yogi.interviewproject.domain.repository.PostRepository
import com.yogi.interviewproject.domain.repository.ProductRepository
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
    fun provideFirebaseDataSource(): FirebaseDataSourceTracking {
        return FirebaseDataSourceTracking()
    }

    @Provides
    @Singleton
    fun provideLocationHelper(@ApplicationContext context: Context): LocationHelper {
        return LocationHelper(context)
    }

    @Provides
    @Singleton
    fun mapRepository(api: DirectionsApi,locationHelper: LocationHelper ): LocationRepositoy {
        return LocationRepositoryImpl(api, locationHelper ,FirebaseDataSourceTracking())
    }

}
