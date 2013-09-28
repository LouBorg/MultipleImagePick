package com.luminous.pick;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.ImageView;

public class GetThumbnailTask extends AsyncTask<Long, Void, Bitmap> {
	
	private Context mContext;
	private ImageView mImageView;
	
	public GetThumbnailTask(Context context, ImageView imageView) {
		mContext = context;
		mImageView = imageView;
	}

	@Override
	protected Bitmap doInBackground(Long... params) {
		long imageId = params[0];
		
        Bitmap thumbnailBitmap = MediaStore.Images.Thumbnails.getThumbnail(
        		mContext.getContentResolver(),
        		imageId,
        		MediaStore.Images.Thumbnails.MICRO_KIND,
        		null
		);
        
        return thumbnailBitmap;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mImageView.setImageResource(R.drawable.no_media);
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		
		if (result != null) {
			mImageView.setImageBitmap(result);
		}
	}

}
