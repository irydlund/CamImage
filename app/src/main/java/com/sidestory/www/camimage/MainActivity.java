package com.sidestory.www.camimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private int CAMERA_REQUEST;
    private ImageView appImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appImage = (ImageView) findViewById(R.id.imgImages);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
        AndroidImageAdapter adapterView = new AndroidImageAdapter(this);
        mViewPager.setAdapter(adapterView);
    }

    /**
     * On click event
     * @param v
     */
    public void btnCameraClick(View v){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        CAMERA_REQUEST = 10;
        // Public Directory fot storing image
        File publicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        // picture name
        String pictureName = getPictureName();
        File imageFile = new File(publicDirectory, pictureName);
        Uri pictureUri = Uri.fromFile(imageFile);
        // store image to gallery
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, CAMERA_REQUEST);

        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                appImage.setImageBitmap(cameraImage);
            }
        }
    }

    public String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp  = sdf.format(new Date());
        return "app_img" + timestamp + ".jpg";
    }


}
