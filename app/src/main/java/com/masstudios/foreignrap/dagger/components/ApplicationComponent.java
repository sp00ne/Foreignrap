package com.masstudios.foreignrap.dagger.components;

import javax.inject.Singleton;

import com.masstudios.foreignrap.App;
import com.masstudios.foreignrap.dagger.AppScope;
import com.masstudios.foreignrap.dagger.modules.AppModule;
import com.masstudios.foreignrap.dagger.modules.NetModule;
import com.masstudios.foreignrap.ui.main.MainFragment;
import dagger.Component;

/**
 * The net component which includes both the App and Net module
 *
 * @author mofa
 * @since 2017-08-14
 */

@AppScope
@Singleton
@Component(modules = { AppModule.class, NetModule.class })
public interface ApplicationComponent {
	void inject(App app);

	void inject(MainFragment fragment);
}