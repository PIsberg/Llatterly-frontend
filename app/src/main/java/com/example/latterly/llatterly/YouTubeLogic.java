package com.example.latterly.llatterly;


import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;

public class YouTubeLogic {

    public void playVideo(String videoId, int startTimeInSec) {

        //Globals.getInstance().getPlayer().seekRelativeMillis(startTimeInSec * 1000) ;
        Globals.getInstance().getPlayer().loadVideo(videoId, startTimeInSec * 1000);

        // Hiding player controls
        Globals.getInstance().getPlayer().setPlayerStyle(PlayerStyle.CHROMELESS); //TODO only once right?

        //TODO: print in log somehow all the time
        Globals.getInstance().getPlayer().getCurrentTimeMillis();
    }


    public void pauseVideo() {
        Globals.getInstance().getPlayer().pause();
    }


    public void playVideo() {
        Globals.getInstance().getPlayer().play();
    }
}