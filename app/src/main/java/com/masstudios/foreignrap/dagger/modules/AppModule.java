package com.masstudios.foreignrap.dagger.modules;

import javax.inject.Singleton;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * The application dagger module, used for instance for caching
 *
 * @author mofa
 * @since 2017-08-14
 */

@Module
public class AppModule {
	Application mApplication;

	public AppModule(Application application) {
		mApplication = application;
	}

	@Singleton
	@Provides
	Application providesApplication() {
		return mApplication;
	}
}
