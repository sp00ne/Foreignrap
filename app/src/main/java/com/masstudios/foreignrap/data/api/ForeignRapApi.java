package com.masstudios.foreignrap.data.api;

import java.util.List;

import com.masstudios.foreignrap.dagger.modules.NetModule;
import com.masstudios.foreignrap.data.models.Video;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * The interface for the retrofit REST system to fetch the json
 *
 * @author mofa
 * @since 2017-08-23
 */

public interface ForeignRapApi {

	@GET(NetModule.VIDEO_PLAYLIST_URL)
	Observable<List<Video>> getVideoPlaylist();
}
