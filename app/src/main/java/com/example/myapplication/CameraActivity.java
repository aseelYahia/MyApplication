package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttoncam, buttongallery;
    private ImageView imageviewprp;
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    //matrix each item is a bit - can turns it to a bitmap using code we then turn it into a file
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        buttongallery=findViewById(R.id.buttongallery);
        buttoncam=findViewById(R.id.buttoncam);
        imageviewprp=findViewById(R.id.imageviewprp);
        buttoncam.setOnClickListener(this);
        buttongallery.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view== buttoncam)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
        if(view==buttongallery){
            Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE);

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                //the image captured is saved in the data object
                bitmap = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                imageviewprp.setImageBitmap(bitmap);
            }
        }//if
        else if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            //URI - unified resource locator is something like URL but can hold different type of paths
            // examples: file:///something.txt, http://www.example.com/, ftp://example.com
            // A Uri object is usually used to tell a ContentProvider what we want to access by reference
            Uri targetUri = data.getData();
            try {
                //Decode an input stream into a bitmap.
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageviewprp.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }//onactivityresult
}