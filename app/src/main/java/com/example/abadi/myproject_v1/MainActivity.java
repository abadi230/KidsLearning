package com.example.abadi.myproject_v1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {
    VideoView videoV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVideoV();

    }

    public void buRead(View view) {
        Intent intent = new Intent(this, ReadingActivity.class);
        startActivity(intent);
    }

    public void buWrite(View view) {
        Intent intent = new Intent(this, WritingActivity.class);
        startActivity(intent);
    }
    private void setVideoV(){//set video as background
        videoV= (VideoView)findViewById(R.id.videobackground);
//        String videoPath="android.resource://com.example.abadi.myproject_v1"+R.raw.bal;
        Uri uri= Uri.parse("android.resource://com.example.abadi.myproject_v1/raw/bal");
        videoV.setVideoURI(uri);
        videoV.start();
        videoV.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Auto replay the video
            // reference: https://stackoverflow.com/questions/9097597/android-videoview-repetition
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menu from res
        MenuInflater myMenu= getMenuInflater();
        // set the menu
        myMenu.inflate(R.menu.main_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /** Menu * */
        int id = item.getItemId();

        if(id==R.id.itm_reading){
            Intent intent = new Intent(this, ReadingActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.itm_writing){
            Intent intent = new Intent(this, WritingActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.exit){
            //close app.
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

}
