/*
 * Copyright (c) 2015.  The AppCan Open Source Project.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package org.zywx.wbpalmstar.plugin.uexcamera360;


import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 通用的工具方法
 */
public class CommonUtil {

    /**
     * 将assets目录下的文件拷到指下目录下。
     * @param context
     * @param path
     * @param destFile
     * @return
     */
    public static boolean saveFileFromAssetsToSystem(Context context, String path, File destFile) {
        BufferedInputStream bis = null;
        BufferedOutputStream fos = null;
        try {
            InputStream is = context.getAssets().open(path);
            bis =  new BufferedInputStream(is);
            fos = new BufferedOutputStream(new FileOutputStream(destFile));
            byte [] buf = new byte [2048];
            int i;
            while ((i= bis.read(buf))!= -1) {
                fos.write(buf, 0, i);
            }
            fos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
    public static boolean copyFile(File fromFile, File toFile) {
        BufferedInputStream bis = null;
        BufferedOutputStream fos = null;
        try {
            FileInputStream is = new FileInputStream(fromFile);
            bis =  new BufferedInputStream(is);
            fos = new BufferedOutputStream(new FileOutputStream(toFile));
            byte [] buf = new byte [2048];
            int i;
            while ((i= bis.read(buf))!= -1) {
                fos.write(buf, 0, i);
            }
            fos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }
}
