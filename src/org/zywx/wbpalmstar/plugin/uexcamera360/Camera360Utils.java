package org.zywx.wbpalmstar.plugin.uexcamera360;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

public class Camera360Utils {
    public static String getGalleryFolder() {
        File storagePath = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                JsConst.CAMERA_PATH);
        if (!storagePath.exists()){
            boolean isSuccess = storagePath.mkdirs();
            if (!isSuccess){
                return null;
            }
        }
        return storagePath.getPath();
    }

    public static String getPicUrl(Context context, Uri selectedImage) {
        String[] filePathColumns = new String[]{MediaStore.Images.Media.DATA};
        Cursor c = context.getContentResolver().query(selectedImage,
                filePathColumns, null, null, null);
        if (c != null){
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String srcPath = c.getString(columnIndex);
            c.close();
            return srcPath;
        }else{
            return null;
        }
    }
}
