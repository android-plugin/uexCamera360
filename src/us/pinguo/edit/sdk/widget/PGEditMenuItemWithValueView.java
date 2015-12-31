package us.pinguo.edit.sdk.widget;

import android.content.Context;
import android.widget.TextView;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;


/**
 * Created by taoli on 14-7-11.
 */
public class PGEditMenuItemWithValueView extends PGEditMenuItemView {
    private TextView mValueTv;

    public PGEditMenuItemWithValueView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResources(Context context) {
        return EUExUtil.getResLayoutID("plugin_uexcamera360_menu_item_with_value");
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        mValueTv = (TextView) findViewById(EUExUtil.getResIdID("value"));
    }

    public void setIcon(int iconId) {
        if (null != mIconImageView) {
            mIconImageView.setImageResource(iconId);
        }
    }

    public void setValue(int value) {
        if (null != mValueTv) {
            mValueTv.setText(String.valueOf(value));
            mValueTv.setVisibility(VISIBLE);
        }
    }

    public void setValue(String value) {
        if (null != mValueTv) {
            mValueTv.setText(value);
            mValueTv.setVisibility(VISIBLE);
        }
    }

    public void hideValue() {
        if (null != mValueTv) {
            mValueTv.setVisibility(INVISIBLE);
        }
    }
}
