package us.pinguo.edit.sdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

public class PGEditLightingSeekbarLayout extends PGEditSeekbarLayout {


    public PGEditLightingSeekbarLayout(Context context) {
        super(context);
    }

    public PGEditLightingSeekbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init() {
        LayoutInflater.from(getContext().getApplicationContext())
                .inflate(EUExUtil.getResLayoutID("pg_sdk_edit_lighting_seekbar_layout"), this, true);
    }



}
