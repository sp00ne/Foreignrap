package com.masstudios.foreignrap.ui.main;

import javax.inject.Inject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.masstudios.foreignrap.dagger.components.ApplicationComponent;
import com.masstudios.foreignrap.ui.base.BaseActivity;

/**
 * The starting activity for the entire application
 *
 * @author mofa
 * @since 2017-08-07
 */

public class MainActivity extends BaseActivity {

	@Inject
	ApplicationComponent mApplicationComponent;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFragment(MainFragment.getInstance(getFragmentManager()), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	}

}
