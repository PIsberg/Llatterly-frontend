package com.example.latterly.llatterly;

import com.google.android.youtube.player.YouTubePlayer;

public class Globals {
    private static Globals instance;


    static {
        instance = new Globals();
    }

    private Globals() {
    }

    public static Globals getInstance() {
        return Globals.instance;
    }

    private MyView myView;
    private YouTubePlayer player;
    private YouTubeLogic youTubeLogic;


    public void setMyView(MyView myView) {
        this.myView = myView;
    }

    public MyView getMyView() {
        return myView;
    }

    public void setPlayer(YouTubePlayer player) {
        this.player = player;
    }

    public YouTubePlayer getPlayer() {
        return player;
    }

    public void setYouTubeLogic(YouTubeLogic youTubeLogic) {
        this.youTubeLogic = youTubeLogic;
    }

    public YouTubeLogic getYouTubeLogic() {
        return youTubeLogic;
    }
}
