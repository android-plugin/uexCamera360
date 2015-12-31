package org.zywx.wbpalmstar.plugin.uexcamera360.vo;

import java.io.Serializable;

public class ResultBaseVO extends DataBaseVO implements Serializable {
    private static final long serialVersionUID = 6755699754896762452L;
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
