package org.zywx.wbpalmstar.plugin.uexcamera360;

public class JsConst {
    public static final String CALLBACK_EDIT = "uexCamera360.cbEdit";
    public static final String INTENT_PARAMS_SRC_PATH = "srcPath";
    public static final String INTENT_PARAMS_SAVE_PATH = "savePath";
    public static final int REQUEST_CODE_EDIT = 1;
    public static final int REQUEST_CODE_PICK = 2;
    public static final String PIC_SUFFIX_PNG = ".png";
    public static final String PIC_SUFFIX_JPG = ".jpg";
    public static final int CALLBACK_ERROR_CODE_SUCCESS = 0;//成功
    public static final int CALLBACK_ERROR_CODE_ERROR_FILE_PATH = -1;//源图片路径错误
    public static final int CALLBACK_EDIT_ERROR_CODE_NO_GALLERY = -2;//相册路径异常
    public static final int CALLBACK_PICK_ERROR_CODE_ERROR_PIC = -3;//获取图片路径失败
    public static final int CALLBACK_ERROR_CODE_CANCEL = -4;//用户取消
    public static final int CALLBACK_EDIT_ERROR_CODE_NOT_CHANGED = -5;//图片没有变化
    public static final int CALLBACK_EDIT_ERROR_CODE_OUTPUT_ERROR = -6;//输出图片路径错误

    public static final String CAMERA_PATH = "Camera";
}
