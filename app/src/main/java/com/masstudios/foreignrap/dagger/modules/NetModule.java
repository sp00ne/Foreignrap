package com.masstudios.foreignrap.dagger.modules;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The http client dagger module - contains all the extensions used by dagger
 *
 * @author mofa
 * @since 2017-08-14
 */

@Module
public class NetModule {

	private static final long DISK_CACHE_SIZE_BYTES = 50 * 1024 * 1024; // 50MB

	String mBaseUrl;

	// Constructor needs one parameter to instantiate.
	public NetModule(String baseUrl) {
		this.mBaseUrl = baseUrl;
	}

	// Dagger will only look for methods annotated with @Provides
	@Provides
	@Singleton
	// Application reference must come from AppModule.class
	SharedPreferences providesSharedPreferences(Application application) {
		return PreferenceManager.getDefaultSharedPreferences(application);
	}

	@Provides
	@Singleton
	Cache provideOkHttpCache(Application app) {
		File cacheDir = new File(app.getCacheDir(), "http");
		return new Cache(cacheDir, DISK_CACHE_SIZE_BYTES);
	}

	@Provides
	@Singleton
	Gson provideGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

		return gsonBuilder.create();
	}

	@Provides
	@Singleton
	OkHttpClient provideOkHttpClient(Cache cache) {
		return new OkHttpClient.Builder()
				.readTimeout(1, TimeUnit.MINUTES)
				.connectTimeout(1, TimeUnit.MINUTES)
				.writeTimeout(1, TimeUnit.MINUTES)
				.cache(cache)
				.build();
	}

	@Provides
	@Singleton
	Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
		return new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create(gson))
				.baseUrl(mBaseUrl)
				.client(okHttpClient)
				.build();
	}
}
