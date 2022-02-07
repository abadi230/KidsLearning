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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ReadingActivity extends AppCompatActivity {
    ImageButton imgB1, imgB2, imgB3;
    TextView txt;
    ArrayList<Integer> photo= new ArrayList<Integer>();//resource photos
    ArrayList<String> namePhoto = new ArrayList<String>();
    Vibrator vibrator;
    int points =0;

    Random random=new Random();
    private int R1,R2,R3;//random images.

    String resultTxt;

    MediaPlayer correctAnswer, wrongAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        // Adding items to array list.
        photo.add(R.drawable.a); photo.add(R.drawable.b); photo.add(R.drawable.c); photo.add(R.drawable.g);
        photo.add(R.drawable.m); photo.add(R.drawable.o); photo.add(R.drawable.s);
        namePhoto.add("Apple");namePhoto.add("Banana");namePhoto.add("Cherry");namePhoto.add("Grap");
        namePhoto.add("Mango");namePhoto.add("Orange");namePhoto.add("Strawberry");

        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        correctAnswer= MediaPlayer.create(this,R.raw.small_crowd_applause);
        wrongAnswer= MediaPlayer.create(this,R.raw.buzzer);

        roll();
    }

    public void buimg1(View view) { check(namePhoto.get(R1)); }

    public void buimg2(View view) { check(namePhoto.get(R2)); }

    public void buimg3(View view) { check(namePhoto.get(R3)); }

    public void roll() {
        R1 =random.nextInt(6);
        R2 =random.nextInt(6);
        R3 =random.nextInt(6);
        while (R1==R2 || R1==R3|| R2==R3)R2=random.nextInt(6);

        int[]R4=new int[]{R1,R2,R3};//the element of array R4 = the value of R1,R2,R3

        //set the imageButtons to display the random photos
        imgB1= (ImageButton)findViewById(R.id.img1);
        imgB1.setImageResource(photo.get(R1));//put random photo from array list into imageButton1
        Animation(imgB1);
        imgB2= (ImageButton)findViewById(R.id.img2);
        imgB2.setImageResource(photo.get(R2));
        Animation(imgB2);
        imgB3= (ImageButton)findViewById(R.id.img3);
        imgB3.setImageResource(photo.get(R3));
        Animation(imgB3);

        // set the TextView to display the random text between element R4
        txt= (TextView)findViewById(R.id.txtRead);//the text inside TextView
        int Rtxt=getRandom(R4);//random between elements R4
        resultTxt= namePhoto.get(Rtxt);
        txt.setText(resultTxt);
    }

    private void check(String num1){
        if(num1.equals(resultTxt)){
            if(points ==6)resultActivity(); //go to result activity
            correctAnswer.start();
            points++;
            Toast.makeText(this, "Correct  \n Points: "+ points, Toast.LENGTH_SHORT).show();
            roll();
        }else {
            wrongAnswer.start();
            points--;
            Toast.makeText(this, "Try again \n Points: " + points, Toast.LENGTH_SHORT).show();

            vibrator.vibrate(1000);// make a mobile vibrate for 1 second
        }
    }
    public static int getRandom(int[] array) {// random between elements of array for namePicture
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    private void Animation(ImageButton ImgBtn){

        @SuppressLint("ResourceType") Animation animate = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.mixanim);
        ImgBtn.startAnimation(animate);
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
