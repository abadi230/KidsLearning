package com.example.abadi.myproject_v1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView name, certificateInf;
    EditText getName;
    ImageView insertPic, PicCert;
    Button btnCupture, btnGallery, btnCertificate;
    LinearLayout layoutInfo, layoutCertificate;
    private static final int CAMERA_REQUEST =1;// for camera
    private static final int GALLERY_REQUEST =100;// for gallery
    Uri imageUri;// to SET photo into uri from gallery

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initilise();
    }
    private void initilise(){

        btnCertificate = (Button)findViewById(R.id.btn_cert);
        btnCupture = (Button)findViewById(R.id.btn_cupture);
        btnGallery = (Button)findViewById(R.id.btn_Gallery);
        insertPic = (ImageView)findViewById(R.id.imageView1);
        PicCert = (ImageView)findViewById(R.id.img_cert);
        name= (TextView)findViewById(R.id.txtV_Name);
        certificateInf=(TextView)findViewById(R.id.txt_info);
        certificateInf.setText("Congratulations! You've been Passed this Exam WILL DONE!!");
        getName = (EditText)findViewById(R.id.editName);
        layoutInfo = (LinearLayout)findViewById(R.id.lay_Info);
        layoutCertificate = (LinearLayout)findViewById(R.id.lay_Cert);

    }

    public void takeCapture(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// THE INTENT  is add action --- the action is run camera
        if(cameraIntent.resolveActivity(getPackageManager())!=null){
            //run camera
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    public void openGallery(View view) {
        Intent gallery= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        //open gallery
        startActivityForResult(gallery, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if the user choose OK
        if(resultCode == RESULT_OK){
            //if everything processed successfully.
            if(requestCode == CAMERA_REQUEST){

                //get the picture from the camera.
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                //set image view
                insertPic.setImageBitmap(cameraImage);
                PicCert.setImageBitmap(cameraImage);

            }else if(requestCode == GALLERY_REQUEST){
                // get photo from gallery
                imageUri = data.getData();
                //set image view
                insertPic.setImageURI(imageUri);
                PicCert.setImageURI(imageUri);
            }
        }
    }

    public void addCertficate(View view) {

        layoutInfo.setVisibility(View.INVISIBLE);
        layoutCertificate.setVisibility(View.VISIBLE);
        name.setText(getName.getText());

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
        /** MENU BAR * */
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
