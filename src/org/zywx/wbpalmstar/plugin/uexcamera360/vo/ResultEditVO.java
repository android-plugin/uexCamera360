package org.zywx.wbpalmstar.plugin.uexcamera360.vo;

import java.io.Serializable;

public class ResultEditVO extends ResultBaseVO implements Serializable{
    private static final long serialVersionUID = -636415460536052068L;
    private String saveFilePath;

    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }
}
