package com.masstudios.foreignrap.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.TransitionRes;

import com.masstudios.foreignrap.R;

/**
 * The activity from which all other ativities extends. Contains base interactions
 *
 * @author mofa
 * @since 2017-08-28
 */

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void setFragment(Fragment fragment, @TransitionRes int fragmentTransition) {
		getFragmentManager().beginTransaction()
				.setTransition(fragmentTransition)
				.replace(R.id.base_frame_container, fragment)
				.commit();
	}
}
