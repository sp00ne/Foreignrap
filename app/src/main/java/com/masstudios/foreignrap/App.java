package com.masstudios.foreignrap;

import android.app.Application;

import com.masstudios.foreignrap.config.Constants;
import com.masstudios.foreignrap.dagger.components.ApplicationComponent;
import com.masstudios.foreignrap.dagger.components.DaggerApplicationComponent;
import com.masstudios.foreignrap.dagger.modules.AppModule;
import com.masstudios.foreignrap.dagger.modules.NetModule;
import timber.log.Timber;

/**
 * Main entry to application
 *
 * @author mofa
 * @since 2017-08-14
 */

public class App extends Application {

	private ApplicationComponent mApplicationComponent;

	@Override
	public void onCreate() {
//		AndroidIn
		super.onCreate();

		initTimber();

		mApplicationComponent = DaggerApplicationComponent.builder()
				.appModule(new AppModule(this))
				.netModule(new NetModule(Constants.BASE_URL))
				.build();

		mApplicationComponent.inject(this);
	}

	private void initTimber() {
		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
	}

	public ApplicationComponent getNetComponent() {
		return mApplicationComponent;
	}
}
