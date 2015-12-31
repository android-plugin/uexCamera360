package us.pinguo.edit.sdk.view;

import android.content.Context;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

/**
 * Created by litao on 14/12/9.
 */
public class PGEditTintSeekBarView extends PGEditThreeSeekBarView {

    @Override
    public String[] getTextNameArray(Context context) {

        String textNameGreen = context.getResources().getString(EUExUtil.getResStringID("pg_sdk_edit_tint_green"));
        String textNameRed = context.getResources().getString(EUExUtil.getResStringID("pg_sdk_edit_tint_red"));
        String textNameYellow = context.getResources().getString(EUExUtil.getResStringID("pg_sdk_edit_tint_yellow"));
        return new String[] {textNameGreen, textNameRed, textNameYellow};
    }
}
