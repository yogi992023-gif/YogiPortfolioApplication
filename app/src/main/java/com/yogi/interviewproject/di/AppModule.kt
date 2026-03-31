package com.yogi.interviewproject.di

import com.yogi.interviewproject.data.remote.ApiService
import com.yogi.interviewproject.data.remote.ProductService
import com.yogi.interviewproject.data.repository.PostRepositoryImpl
import com.yogi.interviewproject.data.repository.ProductRepositoryImpl
import com.yogi.interviewproject.domain.repository.PostRepository
import com.yogi.interviewproject.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}
