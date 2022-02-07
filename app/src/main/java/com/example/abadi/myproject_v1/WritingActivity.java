package com.example.abadi.myproject_v1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class WritingActivity extends AppCompatActivity {
    private int points;
    ImageView myPic;
    int indexPic =0;//change photo
    private int indexPicName =0;//element of name picture array"naPic"
    TextView txtPoint;// display points in checkAnswer function.
    EditText editText;// input text by the user
    Vibrator vibrator;
    int[] arrayPic= new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.g,
    R.drawable.m,R.drawable.o,R.drawable.s};// list photo in array
    String[] naPic=new String[]{"Apple","Banana","Cherry","Grap","Mango","Orange","Strawberry"};//name of picture (arrayPic) with capital letter.
    String[] naPicS=new String[]{"apple","banana","cherry","grap","mango","orange","strawberry"}; //name of picture (arrayPic) with small letter.

    MediaPlayer correctAnswer, wrongAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        myPic=(ImageView) findViewById(R.id.imgWriting);//define the place of image
        myPic.setImageResource(arrayPic[indexPic]);// change the photo depend on number of indexPic into array
        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        correctAnswer= MediaPlayer.create(this,R.raw.small_crowd_applause);
        wrongAnswer= MediaPlayer.create(this,R.raw.buzzer);
    }

    public void check(View view) {

        editText= (EditText) findViewById(R.id.editWrite);//get the recourse of editText from xml
        // define spill as an input user from editText. and remove all spaces (replaceAll)
        String txtSpill= editText.getText().toString().replaceAll(" ","");

        checkAnswer(indexPic,txtSpill);
    }
    void checkAnswer(int pic, String spill){

        txtPoint= (TextView) findViewById(R.id.txtPoint);
        Random random = new Random();


        if (spill.equals(naPic[indexPicName])||spill.equals(naPicS[indexPicName])) {// if the answer is correct 1- show toast 2- add point 3- go to the next image 4-delete editText

            if(points >=6)resultActivity(); //go to result activity
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();//show toast
            points++;// increase points
            indexPicName++;//
            indexPic++; // next image
            correctAnswer.start();
            if(indexPicName==6 || indexPic==6){
                indexPicName=0;
                indexPic=0;
            }
            Animation(myPic);
            myPic.setImageResource(arrayPic[indexPic]);// change the photo depend on number of indexPic into array
            editText.setText("");

        } else {
            points--;
            Toast.makeText(this, "Try again."+ naPic[indexPicName], Toast.LENGTH_SHORT).show();
//            long[] pattern = {0,200,500};//  if i want to mobile vibrate for long time
            wrongAnswer.start();
            vibrator.vibrate(1000);// make a mobile vibrate for 1 second

        }
        txtPoint.setText("Points: " + points);

    }
    private void Animation(ImageView ImgV){

        @SuppressLint("ResourceType") Animation animate = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.mixanim);
        ImgV.startAnimation(animate);
    }
    private void resultActivity(){
        Intent intent = new Intent(this,ResultActivity.class);
        startActivity(intent);
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
        // no inspection Simplifiab lle
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
//Style button : http://tech.chitgoks.com/2011/05/17/android-colored-gradient-buttons-using-xml/