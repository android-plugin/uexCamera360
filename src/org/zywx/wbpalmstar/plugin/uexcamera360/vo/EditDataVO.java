package org.zywx.wbpalmstar.plugin.uexcamera360.vo;

import java.io.Serializable;

public class EditDataVO extends DataBaseVO implements Serializable{
    private static final long serialVersionUID = 1897291472558781608L;
    private String imgSrcPath;
    private boolean isSaveToGallery = true;
    private String imgSavePath;

    public String getImgSrcPath() {
        return imgSrcPath;
    }

    public void setImgSrcPath(String imgSrcPath) {
        this.imgSrcPath = imgSrcPath;
    }

    public boolean isSaveToGallery() {
        return isSaveToGallery;
    }

    public void setIsSaveToGallery(boolean isSaveToGallery) {
        this.isSaveToGallery = isSaveToGallery;
    }

    public String getImgSavePath() {
        return imgSavePath;
    }

    public void setImgSavePath(String imgSavePath) {
        this.imgSavePath = imgSavePath;
    }
}
