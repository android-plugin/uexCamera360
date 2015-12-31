package us.pinguo.edit.sdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public class PGEditFaceThirdBottomLayout extends PGEditSeekbarLayout {

    public PGEditFaceThirdBottomLayout(Context context) {
        super(context);
    }

    public PGEditFaceThirdBottomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init() {
        LayoutInflater.from(getContext().getApplicationContext())
                .inflate(EUExUtil.getResLayoutID("pg_sdk_edit_face_third_buttom_layout"), this, true);
    }

}
