package com.example.latterly.llatterly;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.youtube.player.YouTubePlayerView;

import android.view.View.OnClickListener;

public class MainActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    // YouTube player view
    private YouTubePlayerView youTubeView;
    private MyView view;
    private YouTubeLogic youTubeLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        // Initializing video player with developer key
        Log.d("MyActivity", "MainActivity.getView() - initilize youtube view");
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

        view = new MyView();
        view.initGui(this);
        Globals.getInstance().setMyView(view);
        youTubeLogic = new YouTubeLogic();
        Globals.getInstance().setYouTubeLogic(youTubeLogic);

        LinearLayout parent = (LinearLayout) findViewById(R.id.linearlayout1);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        //parent.addView(mAdView);

        AdRequest adRequest = new AdRequest.Builder().addTestDevice("004C53D3F7A6AEE1F1E4E9BEB5024F73").build();
        //AdRequest adRequest = new AdRequest.Builder().build(); // For production

        mAdView.loadAd(adRequest);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        String errorMessage = "";
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
        Log.d("MyActivity", "MainActivity.onInitializationFailure() - errorMessage "+ errorMessage);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        Log.d("MyActivity", "MainActivity.onInitializationSuccess()");
        if (!wasRestored) {

            Log.d("MyActivity", "MainActivity.onInitializationSuccess() !wasRestored");

            Globals.getInstance().setPlayer(player);

            //youTubeLogic.playVideo(MockData.getInstance().getNextVideoId());

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyActivity", "MainActivity.onActivityResult() � requestCode: "+ requestCode + " resultCode: " + resultCode);
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

}

