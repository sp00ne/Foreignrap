package com.masstudios.foreignrap;

import android.app.Application;

import com.masstudios.foreignrap.config.Constants;
import com.masstudios.foreignrap.dagger.components.DaggerNetComponent;
import com.masstudios.foreignrap.dagger.components.NetComponent;
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

	private NetComponent mNetComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		initTimber();

		mNetComponent = DaggerNetComponent.builder()
				.appModule(new AppModule(this))
				.netModule(new NetModule(Constants.BASE_URL))
				.build();
	}

	private void initTimber() {
		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
	}

	public NetComponent getNetComponent() {
		return mNetComponent;
	}
}
