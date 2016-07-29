package org.zywx.wbpalmstar.plugin.uexcamera360;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import org.zywx.wbpalmstar.base.BUtility;
import org.zywx.wbpalmstar.engine.DataHelper;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.plugin.uexcamera360.vo.EditDataVO;
import org.zywx.wbpalmstar.plugin.uexcamera360.vo.ResultEditVO;
import org.zywx.wbpalmstar.widgetone.WidgetOneApplication;

import java.io.File;
import java.io.IOException;

import us.pinguo.edit.sdk.PGEditImageLoader;
import us.pinguo.edit.sdk.base.PGEditResult;
import us.pinguo.edit.sdk.base.PGEditSDK;

public class EUExCamera360 extends EUExBase {

    private static final String TAG = "EUExCamera360";
    private EditDataVO mEditData = null;
    private int mCurrentId = 0;
    private String cbEditFun;

    public EUExCamera360(Context context, EBrowserView eBrowserView) {
        super(context, eBrowserView);
    }

    public static void onApplicationCreate(Context context) {
        if (context instanceof WidgetOneApplication) {
            WidgetOneApplication application = (WidgetOneApplication) context;
            PGEditImageLoader.initImageLoader(application);
            PGEditSDK.instance().initSDK(application);
        }
    }

    @Override
    protected boolean clean() {
        return false;
    }

    private void startPickPic(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, JsConst.REQUEST_CODE_PICK);
    }

    public String edit(String[] params) {
        String path = null;
        if (params != null && params.length > 0) {
            String json = params[0];
            if (params.length > 1){
                cbEditFun = params[1];
            }
            mEditData = DataHelper.gson.fromJson(json, EditDataVO.class);
            if (TextUtils.isEmpty(mEditData.getId())){
                mEditData.setId(generateId());
            }
            path = mEditData.getImgSrcPath();
            if (mEditData.isSaveToGallery()){
                mEditData.setImgSavePath(null);
            }else{
                if (TextUtils.isEmpty(mEditData.getImgSavePath())){
                    ResultEditVO result = new ResultEditVO();
                    result.setErrorCode(JsConst.CALLBACK_EDIT_ERROR_CODE_OUTPUT_ERROR);
                    callBackEditResult(result);
                    return null;
                }else{
                    String realPath = BUtility.makeRealPath(
                            BUtility.makeUrl(mBrwView.getCurrentUrl(), mEditData.getImgSavePath()),
                            mBrwView.getCurrentWidget().m_widgetPath,
                            mBrwView.getCurrentWidget().m_wgtType);
                    File file = new File(realPath);
                    if (!file.exists()){
                        boolean isSuccess = file.mkdirs();
                        if (!isSuccess){
                            ResultEditVO result = new ResultEditVO();
                            result.setErrorCode(JsConst.CALLBACK_EDIT_ERROR_CODE_OUTPUT_ERROR);
                            callBackEditResult(result);
                            return null;
                        }
                    }
                    mEditData.setImgSavePath(realPath);
                }
            }
        }else{
            mEditData = new EditDataVO();
        }

        if (TextUtils.isEmpty(path)){
            startPickPic();
        }else{
            path = getRealPath(path); //获取在文件系统中的绝对路径，针对res://开头的图片，会拷到/sdcard中
            if(!path.startsWith(File.separator)){
                ResultEditVO editVO = new ResultEditVO();
                editVO.setErrorCode(JsConst.CALLBACK_ERROR_CODE_ERROR_FILE_PATH);
                callBackEditResult(editVO);
            }else{
                startEdit(path);
            }
        }
        return mEditData.getId();
    }

    private String generateId(){
        mCurrentId++;
        return String.valueOf(mCurrentId);
    }

    private void startEdit(final String srcPath) {
        ResultEditVO result = new ResultEditVO();
        if (mEditData != null){
            String savePath;
            if (mEditData.isSaveToGallery()){
                savePath = Camera360Utils.getGalleryFolder();
                if (TextUtils.isEmpty(savePath)){
                    result.setErrorCode(JsConst.CALLBACK_EDIT_ERROR_CODE_NO_GALLERY);
                    callBackEditResult(result);
                    return;
                }
            }else{
                savePath = mEditData.getImgSavePath();
            }
            final String outputFile = getOutputPath(srcPath,savePath);
            if (TextUtils.isEmpty(outputFile)){
                result.setErrorCode(JsConst.CALLBACK_EDIT_ERROR_CODE_OUTPUT_ERROR);
                callBackEditResult(result);
                return;
            }

            Intent intent = new Intent(mContext, StartEditActivity.class);
            intent.putExtra(JsConst.INTENT_PARAMS_SRC_PATH, srcPath);
            intent.putExtra(JsConst.INTENT_PARAMS_SAVE_PATH, outputFile);
            startActivityForResult(intent, JsConst.REQUEST_CODE_EDIT);
        }
    }

    private String getOutputPath(String srcPath, String path) {
        if (!TextUtils.isEmpty(path) && path.startsWith(File.separator)){
            String name = String.valueOf(System.currentTimeMillis());
            if (srcPath.toLowerCase().endsWith(JsConst.PIC_SUFFIX_PNG)){
                name = name + JsConst.PIC_SUFFIX_PNG;
            }else{
                name = name + JsConst.PIC_SUFFIX_JPG;
            }
            if (path.endsWith(File.separator)){
                path = path + name;
            }else{
                path = path + File.separator + name;
            }
            return path;
        }else{
            return null;
        }
    }

    private void callBackEditResult(ResultEditVO result){
        result.setId(mEditData.getId());
        if (!TextUtils.isEmpty(cbEditFun)){
            callbackToJs(Integer.parseInt(cbEditFun), false, DataHelper.gson.toJsonTree(result));
        }else{
            callBackPluginJs(JsConst.CALLBACK_EDIT, DataHelper.gson.toJson(result));
        }
    }

    private void callBackPluginJs(String methodName, String jsonData){
        String js = SCRIPT_HEADER + "if(" + methodName + "){"
                + methodName + "('" + jsonData + "');}";
        Log.i(TAG, "callBackPluginJs:" + js);
        onCallback(js);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ResultEditVO result = new ResultEditVO();
        switch (requestCode){
            case JsConst.REQUEST_CODE_PICK:
                switch (resultCode){
                    case Activity.RESULT_OK:
                        if (data == null){
                            result.setErrorCode(JsConst.CALLBACK_PICK_ERROR_CODE_ERROR_PIC);
                            callBackEditResult(result);
                            return;
                        }
                        Uri selectedImage = data.getData();
                        final String srcPath = Camera360Utils.getPicUrl(mContext, selectedImage);
                        if (!TextUtils.isEmpty(srcPath)){
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startEdit(srcPath);
                                }
                            }, 2);
                        }else{
                            result.setErrorCode(JsConst.CALLBACK_PICK_ERROR_CODE_ERROR_PIC);
                            callBackEditResult(result);
                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        result.setErrorCode(JsConst.CALLBACK_ERROR_CODE_CANCEL);
                        callBackEditResult(result);
                        break;
                    default:
                        break;
                }
                break;
            case JsConst.REQUEST_CODE_EDIT:
                switch (resultCode){
                    case Activity.RESULT_OK:
                        PGEditResult editResult = PGEditSDK.instance().handleEditResult(data);
                        result.setErrorCode(JsConst.CALLBACK_ERROR_CODE_SUCCESS);
                        result.setSaveFilePath(editResult.getReturnPhotoPath());
                        callBackEditResult(result);
                        break;
                    case PGEditSDK.PG_EDIT_SDK_RESULT_CODE_CANCEL:
                        result.setErrorCode(JsConst.CALLBACK_ERROR_CODE_CANCEL);
                        callBackEditResult(result);
                        break;
                    case PGEditSDK.PG_EDIT_SDK_RESULT_CODE_NOT_CHANGED:
                        result.setErrorCode(JsConst.CALLBACK_EDIT_ERROR_CODE_NOT_CHANGED);
                        callBackEditResult(result);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public String getRealPath(String path){
        String realPath=BUtility.makeRealPath(
                BUtility.makeUrl(mBrwView.getCurrentUrl(), path),
                mBrwView.getCurrentWidget().m_widgetPath,
                mBrwView.getCurrentWidget().m_wgtType);

        String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());

        //先将assets文件写入到临时文件夹中
        if (path.startsWith(BUtility.F_Widget_RES_SCHEMA)) {
            //为res对应的文件生成一个临时文件到系统中
            File dir = new File(Environment.getExternalStorageDirectory(),
                    File.separator + "temp");
            if (!dir.exists()) {
                dir.mkdirs();
            } else {
                //及时清理这个缓存文件夹中的数据
                for (File file: dir.listFiles()) {
                    file.delete();
                }
            }
            File destFile = new File(dir, fileName);
            try {
                destFile.deleteOnExit();
                destFile.createNewFile();
            } catch (IOException e) {
                Log.i(TAG, "[Create File]" +  e.getMessage());
                return null;
            }
            if (realPath.startsWith("/data")){
                CommonUtil.copyFile(new File(realPath), destFile);
                return destFile.getAbsolutePath();
            }else if( CommonUtil.saveFileFromAssetsToSystem(mContext, realPath, destFile)) {
                return destFile.getAbsolutePath();
            } else {
                Log.i(TAG, "[getRealPath error]");
                return null;
            }
        } else {
            return realPath;
        }
    }
}
