package us.pinguo.edit.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;


import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import us.pinguo.edit.sdk.base.widget.PGEditableView;

/**
 * Created by marui on 14-1-22.
 */
public class PGEditableRelativeLayout extends RelativeLayout implements PGEditableView {

    public PGEditableRelativeLayout(Context context) {
        super(context, null, -1);
    }

    public PGEditableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs, -1);
    }

    public PGEditableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View getDeleteView() {
        return findViewById(EUExUtil.getResIdID("editable_delete"));
    }

}
