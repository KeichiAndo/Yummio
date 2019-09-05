package com.example.yummio;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yummio.model.Cake;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class DetailFragment extends Fragment {

    private SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;

    private Cake.Steps steps;

    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_layout, container, false);

        // Get a reference to ImageView in the fragment layout
        final TextView shortDesc = rootView.findViewById(R.id.tv_fragment_step_short);
        mPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.video_view);
        final TextView description = rootView.findViewById(R.id.tv_fragment_step_description);

        shortDesc.setText(getArguments().getString("SHORT"));
        description.setText(getArguments().getString("LONG"));

        getPlayer();

        return rootView;
    }

    private void getPlayer() {
        if (mPlayer == null) {
            Handler handler = new Handler();

            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(handler), new DefaultLoadControl());

            mPlayerView.setPlayer(mPlayer);

            String videoUrl = MainActivity.cakeArrayList.get(DetailActivity.CURRENT_MENU)
                    .getSteps().get(DetailActivity.CURRENT_PAGE).getVideoUrl();
            String thumbnailUrl = MainActivity.cakeArrayList.get(DetailActivity.CURRENT_MENU)
                    .getSteps().get(DetailActivity.CURRENT_PAGE).getThumbnailUrl();

            MediaSource mediaSource;

            if (!videoUrl.equals("")) {
                String userAgent = Util.getUserAgent(this.getContext(), "Yummio");
                mediaSource = new ExtractorMediaSource(Uri.parse(videoUrl),
                        new DefaultDataSourceFactory(this.getContext(), userAgent),
                        new DefaultExtractorsFactory(), null, null);
                mPlayer.prepare(mediaSource);
                mPlayer.setPlayWhenReady(true);
            } else if (!thumbnailUrl.equals("")) {
                String userAgent = Util.getUserAgent(this.getContext(), "Yummio");
                mediaSource = new ExtractorMediaSource(Uri.parse(thumbnailUrl),
                        new DefaultDataSourceFactory(this.getContext(), userAgent),
                        new DefaultExtractorsFactory(), null, null);
                mPlayer.prepare(mediaSource);
                mPlayer.setPlayWhenReady(true);
            } else {
                mPlayerView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
        mPlayer.release();
    }
}
