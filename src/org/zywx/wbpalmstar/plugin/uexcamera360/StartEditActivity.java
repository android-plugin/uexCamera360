package org.zywx.wbpalmstar.plugin.uexcamera360;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import us.pinguo.edit.sdk.PGEditActivity;
import us.pinguo.edit.sdk.base.PGEditSDK;

public class StartEditActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String srcPath = getIntent().getStringExtra(JsConst.INTENT_PARAMS_SRC_PATH);
        String savePath = getIntent().getStringExtra(JsConst.INTENT_PARAMS_SAVE_PATH);
        PGEditSDK.instance().startEdit(this, PGEditActivity.class, srcPath, savePath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PGEditSDK.PG_EDIT_SDK_REQUEST_CODE){
            switch (resultCode){
                case Activity.RESULT_OK:
                    setResult(Activity.RESULT_OK, data);
                    break;
                case PGEditSDK.PG_EDIT_SDK_RESULT_CODE_CANCEL:
                    setResult(PGEditSDK.PG_EDIT_SDK_RESULT_CODE_CANCEL);
                    break;
                case PGEditSDK.PG_EDIT_SDK_RESULT_CODE_NOT_CHANGED:
                    setResult(PGEditSDK.PG_EDIT_SDK_RESULT_CODE_NOT_CHANGED);
                    break;
                default:
                    break;
            }
            finish();
        }
    }
}
