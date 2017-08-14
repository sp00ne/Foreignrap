package com.masstudios.foreignrap.dagger.components;

import javax.inject.Singleton;

import com.masstudios.foreignrap.dagger.modules.AppModule;
import com.masstudios.foreignrap.dagger.modules.NetModule;
import com.masstudios.foreignrap.main.MainActivity;
import dagger.Component;

/**
 * The net component which includes both the App and Net module
 *
 * @author mofa
 * @since 2017-08-14
 */

@Singleton
@Component(modules = { AppModule.class, NetModule.class })
public interface NetComponent {
	void inject(MainActivity activity);
}