package us.pinguo.edit.sdk.view;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

import org.zywx.wbpalmstar.engine.universalex.EUExUtil;

import us.pinguo.edit.sdk.base.bean.PGEditMenuBean;
import us.pinguo.edit.sdk.base.bean.PGEditTiltShiftMenuBean;
import us.pinguo.edit.sdk.base.view.IPGEditTiltShiftSeekBarView;
import us.pinguo.edit.sdk.base.view.IPGEditTiltShiftSeekBarViewListener;
import us.pinguo.edit.sdk.base.view.PGEditTiltShiftOnSeekChangeListener;
import us.pinguo.edit.sdk.base.view.PGEditTiltShiftSeekBar;
import us.pinguo.edit.sdk.base.widget.AnimationAdapter;

/**
 * Created by litao on 14/12/8.
 */
public class PGEditTiltShiftSeekBarView implements View.OnClickListener, IPGEditTiltShiftSeekBarView {

    private PGEditSeekbarLayout mSeekLayout;
    private View mCancelBtn;
    private View mConfirmBtn;
    private PGEditTiltShiftSeekBar mSeekBar;
    private IPGEditTiltShiftSeekBarViewListener mListener;

    public void initView(Activity activity) {
        mSeekLayout = (PGEditSeekbarLayout) activity.findViewById(EUExUtil.getResIdID("tiltshift_seekbar_layout"));
        mSeekBar = (PGEditTiltShiftSeekBar) mSeekLayout.findViewById(EUExUtil.getResIdID("seek_bar"));
        mSeekBar.setTextHeight(activity.getResources().getDimension(EUExUtil.getResDimenID("pg_sdk_edit_tilt_shift_seekbar_text_size")));
        mSeekBar.setTags(PGEditTiltShiftMenuBean.Tags);
        mSeekBar.setThumbDrawable(activity.getResources().getDrawable(
                EUExUtil.getResDrawableID("pg_sdk_edit_tilt_shift_seekbar_thumb")));

        mCancelBtn = mSeekLayout.findViewById(EUExUtil.getResIdID("cancel"));
        mCancelBtn.setOnClickListener(this);

        mConfirmBtn = mSeekLayout.findViewById(EUExUtil.getResIdID("confirm"));
        mConfirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (null == mListener) {
            return;
        }

        if (mCancelBtn == v) {
            mListener.onCancelBtnClick();
            return;
        }

        if (mConfirmBtn == v) {
            mListener.onConfirmBtnClick();
            return;
        }
    }

    public void setListener(IPGEditTiltShiftSeekBarViewListener listener) {
        mListener = listener;
    }

    public void showValueSeekLayout(String tag) {

        mSeekLayout.setVisibility(View.VISIBLE);
        mSeekLayout.showWithAnimation();

        mSeekBar.setOnSeekChangeListener(null);
        mSeekBar.reset();
        mSeekBar.setPosition(tag);
        mSeekBar.setOnSeekChangeListener(new PGEditTiltShiftOnSeekChangeListener() {
            @Override
            public void onSeekChanged(String tag, int index) {
                if (null != mListener) {
                    mListener.onSeekValueChanged(tag, index);
                }
            }

            @Override
            public void onSeekStopped(String tag, int index) {
                if (null != mListener) {
                    mListener.onSeekStopped(tag, index);
                }
            }
        });
    }

    public void hideWithAnimation() {
        mSeekLayout.hideWithAnimation(new AnimationAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                mSeekLayout.setVisibility(View.GONE);
            }
        });
    }

    public void confirm() {
        onClick(mConfirmBtn);
    }

    public void cancel() {
        onClick(mCancelBtn);
    }

    public boolean isSeekBarVisible() {
        return mSeekLayout.getVisibility() == View.VISIBLE;
    }
}
