package com.masstudios.foreignrap.dagger.modules;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.masstudios.foreignrap.dagger.AppScope;
import com.masstudios.foreignrap.data.api.ForeignRapApi;
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

	public static final String VIDEO_PLAYLIST_URL = "http://www.foreignrap.com/data.json";

	private static final long DISK_CACHE_SIZE_BYTES = 50 * 1024 * 1024; // 50MB

	String mBaseUrl;

	// Constructor needs one parameter to instantiate.
	public NetModule(String baseUrl) {
		this.mBaseUrl = baseUrl;
	}

	// Dagger will only look for methods annotated with @Provides
	@Provides
	@AppScope
	// Application reference must come from AppModule.class
	SharedPreferences providesSharedPreferences(Application application) {
		return PreferenceManager.getDefaultSharedPreferences(application);
	}

	@Provides
	@AppScope
	Cache provideOkHttpCache(Application app) {
		File cacheDir = new File(app.getCacheDir(), "http");
		return new Cache(cacheDir, DISK_CACHE_SIZE_BYTES);
	}

	@Provides
	@AppScope
	Gson provideGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

		return gsonBuilder.create();
	}

	@Provides
	@AppScope
	OkHttpClient provideOkHttpClient(Cache cache) {
		return new OkHttpClient.Builder()
				.readTimeout(1, TimeUnit.MINUTES)
				.connectTimeout(1, TimeUnit.MINUTES)
				.writeTimeout(1, TimeUnit.MINUTES)
				.cache(cache)
				.build();
	}

	@Provides
	@Named("foreignRap")
	@AppScope
	Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
		return new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(mBaseUrl)
				.client(okHttpClient)
				.build();
	}

	@Provides
	@AppScope
	ForeignRapApi provideApi(@Named("foreignRap") Retrofit restAdapter) {
		return restAdapter.create(ForeignRapApi.class);
	}
}
