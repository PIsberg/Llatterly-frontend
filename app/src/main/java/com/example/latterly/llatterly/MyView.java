package com.example.latterly.llatterly;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.latterly.llatterly.model.VideoData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class MyView implements AsyncResponse {

    private MainActivity mainActivity;

    private Button alt1Button;
    private Button alt2Button;
    private Button alt3Button;
    private Button alt4Button;

    final Handler handler = new Handler();
    private TimerTask pauseVideotimerTask;
    private TimerTask pauseVideotimerTask2;

    private VideoData videoData;
    private int videoCounter = 1;
    public void initGui(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        Log.d("MyView", "MyView.initGui() - initilize app view");

        videoData = new VideoData();
        getNextVideo("vid" + videoCounter);
    }


    public void getNextVideo(String videoId) {
        new RetrieveVideoDataTask().execute(videoId);
    }

    public Button addButtonComponent(int componentId, View.OnClickListener buttonListener, String apa) {
        Button button = (Button) mainActivity.findViewById(componentId);
        button.setOnClickListener(buttonListener);
        return button;
    }

    // wait for asynch retrieveVideoDataTask to finish
    @Override
    public void processFinish(String output) {
        Log.d("processFinish", output);
    }


    public void test() {
        Log.d("test", "test");

        // 4x alt buttons
        alt1Button = this.addButtonComponent(R.id.alt1, new Answer1ButtonListener(), "");
        alt1Button.setText(videoData.getAnswer1());

        alt2Button = this.addButtonComponent(R.id.alt2, new Answer2ButtonListener(), "");
        alt2Button.setText(videoData.getAnswer2());

        alt3Button = this.addButtonComponent(R.id.alt3, new Answer3ButtonListener(), "");
        alt3Button.setText(videoData.getAnswer3());

        alt4Button = this.addButtonComponent(R.id.alt4, new Answer4ButtonListener(), videoData.getAnswer4());
        alt4Button.setText(videoData.getAnswer4());

        Log.d("initGUI answer1", videoData.getAnswer1());
        Globals.getInstance().getYouTubeLogic().playVideo(videoData.getYoutubeId(), (int) videoData.getStartTime());


        pauseVideoIn(videoData.getPauseTime() * 100);

    }


    private void pauseVideoIn(long milliSecondsDelay) {
        Timer timer = new Timer();

        pauseVideoTimerTask();

        Log.d("asdf1223", videoData.getPauseTime() + "");
        timer.schedule(pauseVideotimerTask, videoData.getPauseTime() * 100, 200000);
    }

    private void pauseVideoIn2(long milliSecondsDelay) {
        Timer timer = new Timer();

        pauseVideoTimerTask2();

        Log.d("asdf1223", videoData.getPauseTime() + "");
        timer.schedule(pauseVideotimerTask2, videoData.getPauseTime() * 100, 200000);
    }
//PAUSE
    public void pauseVideoTimerTask() {
        pauseVideotimerTask = new TimerTask() {
            public void run() {
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        Log.d("pauseVideotimerTask", "Pausing video");
                        Globals.getInstance().getYouTubeLogic().pauseVideo();
                        pauseVideotimerTask.cancel();
                    }
                });
            }
        };
    }
//STOP
    public void pauseVideoTimerTask2() {
        pauseVideotimerTask2 = new TimerTask() {
            public void run() {
                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        Log.d("pauseVideotimerTask", "Pausing video");
                        Globals.getInstance().getYouTubeLogic().pauseVideo();
                        pauseVideotimerTask2.cancel();

                        videoCounter++;
                        MyView.this.getNextVideo("vid" + videoCounter);
                    }
                });
            }
        };
    }

    class Answer1ButtonListener implements View.OnClickListener {

        public void onClick(android.view.View v) {

            String answerResult = "Incorrect";
            if(MyView.this.videoData.getCorrectAnswer() == 1) {
                answerResult = "Correct";
            }
/*
            AlertDialog.Builder builder = new AlertDialog.Builder(MyView.this.mainActivity);
            builder.setTitle("Answer");
            builder.setMessage(answerResult);
            builder.setPositiveButton("OK", null);
            builder.show();
*/

            Context context = mainActivity.getApplicationContext();
            CharSequence text = answerResult;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Globals.getInstance().getYouTubeLogic().playVideo();

            pauseVideoIn2(videoData.getStopTime() * 100);

        }



    }

    class Answer2ButtonListener implements View.OnClickListener {

        public void onClick(android.view.View v) {
            String answerResult = "Incorrect";
            if(MyView.this.videoData.getCorrectAnswer() == 2) {
                answerResult = "Correct";
            }
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(MyView.this.mainActivity);
            builder.setTitle("Answer");
            builder.setMessage(answerResult);
            builder.setPositiveButton("OK", null);
            builder.show();
            */
            Context context = mainActivity.getApplicationContext();
            CharSequence text = answerResult;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            Globals.getInstance().getYouTubeLogic().playVideo();

            pauseVideoIn2(videoData.getStopTime() * 100);
        }
    }

    class Answer3ButtonListener implements View.OnClickListener {

        public void onClick(android.view.View v) {
            String answerResult = "Incorrect";
            if(MyView.this.videoData.getCorrectAnswer() == 3) {
                answerResult = "Correct";
            }
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(MyView.this.mainActivity);
            builder.setTitle("Answer");
            builder.setMessage(answerResult);
            builder.setPositiveButton("OK", null);
            builder.show();
            */
            Context context = mainActivity.getApplicationContext();
            CharSequence text = answerResult;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Globals.getInstance().getYouTubeLogic().playVideo();

            pauseVideoIn2(videoData.getStopTime() * 100);

        }
    }

    class Answer4ButtonListener implements View.OnClickListener {

        public void onClick(android.view.View v) {
            String answerResult = "Incorrect";
            if(MyView.this.videoData.getCorrectAnswer() == 4) {
                answerResult = "Correct";
            }
            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(MyView.this.mainActivity);
            builder.setTitle("Answer");
            builder.setMessage(answerResult);
            builder.setPositiveButton("OK", null);
            builder.show();
            */

            Context context = mainActivity.getApplicationContext();
            CharSequence text = answerResult;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Globals.getInstance().getYouTubeLogic().playVideo();
            pauseVideoIn2(videoData.getStopTime() * 100);
        }
    }

    class RetrieveVideoDataTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... videoId) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            final String FORECAST_BASE_URL =
                    "https://latterlyapi.appspot.com/_ah/api/latterly/v1/videodata/{id}";

            Log.d("RetrieveVideoDataTask", videoId[0]);
            String builtURL = FORECAST_BASE_URL.replace("{id}", videoId[0]);

            Log.d("RetrieveVideoDataTask2", builtURL);
            URL url = null;
            try {
                url = new URL(builtURL.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            // Create the request to OpenWeatherMap, and open the connection
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                urlConnection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            try {
                urlConnection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Read the input stream into a String
            InputStream inputStream = null;
            try {
                inputStream = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            try {
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            return buffer.toString();
        }
        protected void onPostExecute(String data) {
            Log.d("Videodatajson", data);

            MyView.this.videoData = mapVideoData(data);

            Log.d("onPostExecute answer1",  MyView.this.videoData.getAnswer1());
            MyView.this.test();
        }



        public VideoData mapVideoData(String videoDataJsonStr) {
            VideoData videoData = new VideoData();



            try {
                JSONObject videoDataJsonObject = new JSONObject(videoDataJsonStr);
                String answer1 = videoDataJsonObject.getString("answer1");
                String answer2 = videoDataJsonObject.getString("answer2");
                String answer3 = videoDataJsonObject.getString("answer3");
                String answer4 = videoDataJsonObject.getString("answer4");
                String youtubeId = videoDataJsonObject.getString("youtubeId");
                String question = videoDataJsonObject.getString("question");
                long startTime = videoDataJsonObject.getLong("startTime");
                int correctAnswer = videoDataJsonObject.getInt("correctAnswer");
                long pauseTime = videoDataJsonObject.getLong("pauseTime");
                long stopTime = videoDataJsonObject.getLong("stopTime");

                videoData.setAnswer1(answer1);
                videoData.setAnswer2(answer2);
                videoData.setAnswer3(answer3);
                videoData.setAnswer4(answer4);

                videoData.setYoutubeId(youtubeId);
                videoData.setQuestion(question);
                videoData.setStartTime(startTime);
                videoData.setCorrectAnswer(correctAnswer);
                videoData.setPauseTime(pauseTime);
                videoData.setStopTime(stopTime);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            //   }

            return videoData;
        }

    }

}


interface AsyncResponse {
    void processFinish(String output);
}



