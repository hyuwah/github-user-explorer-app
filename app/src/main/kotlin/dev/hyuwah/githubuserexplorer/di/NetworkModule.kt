package dev.hyuwah.githubuserexplorer.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.hyuwah.githubuserexplorer.BuildConfig
import dev.hyuwah.githubuserexplorer.data.remote.SearchServicesApi
import dev.hyuwah.githubuserexplorer.data.remote.UserServicesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    const val BASE_URL = "https://api.github.com/"

    @Singleton
    @Provides
    fun providesChuckerInterceptor(
        @ApplicationContext context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Singleton
    @Provides
    fun providesHttpLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLogInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(httpLogInterceptor)
            }
            .build()
    }

    private inline fun <reified T> createRestApiAdapter(okHttpClient: OkHttpClient, url: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(T::class.java)
    }

    @Singleton
    @Provides
    fun providesSearchServices(okHttpClient: OkHttpClient): SearchServicesApi {
        return createRestApiAdapter(okHttpClient, BASE_URL)
    }

    @Singleton
    @Provides
    fun providesUserServices(okHttpClient: OkHttpClient): UserServicesApi {
        return createRestApiAdapter(okHttpClient, BASE_URL)
    }
}