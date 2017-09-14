package com.masstudios.foreignrap.ui.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.util.Log;
import android.widget.Toast;

import com.masstudios.foreignrap.App;
import com.masstudios.foreignrap.data.api.ForeignRapApi;
import com.masstudios.foreignrap.data.models.Video;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * The view element of the MVP model
 *
 * @author mofa
 * @since 2017-08-20
 */

public class MainFragment extends BrowseFragment {
	public static final String TAG = MainFragment.class.getSimpleName();

	@Inject
	ForeignRapApi mApi;

	List<Video> mVideosList = new ArrayList<>();

	public MainFragment() {
	}

	public static MainFragment getInstance(FragmentManager fragmentManager) {
		MainFragment fragment = (MainFragment) fragmentManager.findFragmentByTag(MainFragment.TAG);
		if (fragment == null) {
			return MainFragment.newInstance();
		}

		return fragment;
	}

	public static MainFragment newInstance() {
		return new MainFragment();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		App.getInstance().getAppComponent().inject(this);

		mVideosList.clear();
		mApi.getVideoPlaylist()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<List<Video>>() {
					@Override
					public void accept(@NonNull List<Video> videos) throws Exception {
						// onNext
						mVideosList.addAll(videos);
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(@NonNull Throwable throwable) throws Exception {
						// onError
						Log.d(TAG, "getVideoPlaylist: " + throwable.getMessage());
						Toast.makeText(getActivity(), "Could not fetch the videos :(", Toast.LENGTH_SHORT).show();
					}
				});
	}
}
