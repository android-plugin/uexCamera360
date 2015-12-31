package us.pinguo.edit.sdk.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import us.pinguo.edit.sdk.widget.PGEditMenuItemView;

public class PGEditMenuItemWithValueView extends PGEditMenuItemView {

    private TextView mValueTv;

    public PGEditMenuItemWithValueView(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResources(Context context) {
        return EUExUtil.getResLayoutID("pg_sdk_edit_menu_item_with_value");
    }

    protected void init(Context context){
        super.init(context);
        mValueTv = (TextView) findViewById(EUExUtil.getResIdID("value"));

    }

    public void setIcon(Drawable drawable) {
        if(mIconImageView != null) {
            mIconImageView.setImageDrawable(drawable);
        }
    }

    @Override
    public void setValue(String value) {
        if (null != mValueTv) {
            mValueTv.setText(value);
            mValueTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideValue() {
        if (null != mValueTv) {
            mValueTv.setVisibility(View.INVISIBLE);
        }
    }

    public void hideName() {
       findViewById(EUExUtil.getResIdID("name")).setVisibility(View.GONE);
    }

    public void setItemBg(int color) {
        findViewById(EUExUtil.getResIdID("bg_view")).setBackgroundColor(color);
    }

}
