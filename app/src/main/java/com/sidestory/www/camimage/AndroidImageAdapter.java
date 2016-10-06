package com.sidestory.www.camimage;

/**
 * Created by gaura on 10/6/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.FilenameFilter;

public class AndroidImageAdapter extends PagerAdapter {
    Context mContext;

    AndroidImageAdapter(Context context) {
        this.mContext = context;
    }

    private File imgDirectory = new File("/storage/emulated/0/DCIM/Camera/");
    File[] allImages = imgDirectory.listFiles(new FilenameFilter(){
        public boolean accept(File dir, String name)
        {
            return ((name.endsWith(".jpg"))||(name.endsWith(".png")));
        }
    });

    @Override
    public int getCount() {
        return 1;
    }
    private int[] sliderImagesId = new int[]{
            R.drawable.image1, R.drawable.image2, R.drawable.cat,
            R.drawable.image1, R.drawable.image2, R.drawable.cat,
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        Bitmap bitmap = BitmapFactory.decodeFile(allImages[position]);
//        mImageView.setImageResource(getImageFromSdCard(filename));
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
    public Drawable getImageFromSdCard(String imageName) {
        Drawable d = null;
        try {
            String path = Environment.getExternalStorageDirectory().toString();
            Bitmap bitmap = BitmapFactory.decodeFile(path + "/" + imageName + ".png");
            d = new BitmapDrawable(bitmap);
        } catch (IllegalArgumentException e) {
            // TODO: handle exception
        }
        return d;
    }
}