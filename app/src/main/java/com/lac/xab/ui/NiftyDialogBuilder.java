package com.lac.xab.ui;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lac.xab.ui.effects.BaseEffects;
import com.lac.xab.ui.effects.Effectstype;
import com.lac.xaboa.R;


public class NiftyDialogBuilder extends Dialog implements DialogInterface {


    Dialog dlg;
    Context context;

    private Effectstype type = null;

    private LinearLayout mLinearLayoutView;

    private RelativeLayout mRelativeLayoutView;

    private LinearLayout mLinearLayoutMsgView;

    private LinearLayout mLinearLayoutTopView;

    private FrameLayout mFrameLayoutCustomView;

    private View mDialogView;

    private View mDivider;

    private TextView mTitle;

    private TextView mMessage;

    private ImageView mIcon;

    private Button bt_dlg_left;

    private Button bt_dlg_right;

    private int mDuration = -1;

    private static int mOrientation = 1;

    private boolean isCancelable = true;

    private volatile static NiftyDialogBuilder instance;

    public NiftyDialogBuilder(Context context) {
        super(context);
        init(context);

    }

    public NiftyDialogBuilder(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    }

    public static NiftyDialogBuilder getInstance(Context context) {

        int ort = context.getResources().getConfiguration().orientation;
        if (mOrientation != ort) {
            mOrientation = ort;
            instance = null;
        }

        if (instance == null || ((Activity) context).isFinishing()) {
            synchronized (NiftyDialogBuilder.class) {
                if (instance == null) {
                    instance = new NiftyDialogBuilder(context, R.style.dialog_untran);
                }
            }
        }
        return instance;

    }

    private void init(Context context) {
        dlg = this;
        this.context = context;
        mDialogView = View.inflate(context, R.layout.dlg_custom, null);

        mLinearLayoutView = (LinearLayout) mDialogView.findViewById(R.id.ll_dlg);
        mRelativeLayoutView = (RelativeLayout) mDialogView.findViewById(R.id.rl_dlg);
        mLinearLayoutTopView = (LinearLayout) mDialogView.findViewById(R.id.ll_dlg_top);
        mLinearLayoutMsgView = (LinearLayout) mDialogView.findViewById(R.id.ll_dlg_content);
        mFrameLayoutCustomView = (FrameLayout) mDialogView.findViewById(R.id.fl_dlg_custom);

        mTitle = (TextView) mDialogView.findViewById(R.id.tv_dlg_title);
        mMessage = (TextView) mDialogView.findViewById(R.id.tv_dlg_message);
        mIcon = (ImageView) mDialogView.findViewById(R.id.iv_dlg_icon);
        mDivider = mDialogView.findViewById(R.id.v_dlg_divider);
        bt_dlg_left = (Button) mDialogView.findViewById(R.id.bt_left);
        bt_dlg_right = (Button) mDialogView.findViewById(R.id.bt_right);

        setContentView(mDialogView);

        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                mLinearLayoutView.setVisibility(View.VISIBLE);
                if (type == null) {
                    type = Effectstype.Slidetop;
                }
                start(type);
            }
        });
    }

    public void toDefault() {
        mTitle.setTextColor(getColor(R.color.defBlack));
        mDivider.setBackgroundColor(getColor(R.color.defDividerColor));
        mMessage.setTextColor(getColor(R.color.defBlack));
        mLinearLayoutView.setBackgroundColor(getColor(R.color.defDialogColor));
    }

    private int getColor(int colorResId) {
        return context.getResources().getColor(colorResId);
    }

    public NiftyDialogBuilder withDividerColor(String colorString) {
        mDivider.setBackgroundColor(Color.parseColor(colorString));
        return this;
    }

    public NiftyDialogBuilder withDividerColor(int color) {
        mDivider.setBackgroundColor(context.getResources().getColor(color));
        return this;
    }

    public NiftyDialogBuilder withTitle(CharSequence title) {
        if(title ==null) {
            title=context.getResources().getString(R.string.dlg_dft_title);
        }
        toggleView(mLinearLayoutTopView, title);
        mTitle.setText(title);
        return this;
    }

    public NiftyDialogBuilder withTitleColor(String colorString) {
        mTitle.setTextColor(Color.parseColor(colorString));
        return this;
    }

    public NiftyDialogBuilder withMessage(int textResId) {
        toggleView(mLinearLayoutMsgView, textResId);
        mMessage.setText(textResId);
        return this;
    }

    public NiftyDialogBuilder withMessage(CharSequence msg) {
        toggleView(mLinearLayoutMsgView, msg);
        mMessage.setText(msg);
        return this;
    }

    public NiftyDialogBuilder withMessageColor(String colorString) {
        mMessage.setTextColor(Color.parseColor(colorString));
        return this;
    }
    public NiftyDialogBuilder seTouchViewtCancle(boolean flag) {
        if(flag){
            mRelativeLayoutView.setOnClickListener(defaultLsn);
        }else{
            mRelativeLayoutView.setOnClickListener(null);
        }
        return this;
    }
    public NiftyDialogBuilder withIcon(int drawableResId) {
        mIcon.setImageResource(drawableResId);
        return this;
    }

    public NiftyDialogBuilder withIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
        return this;
    }

    public NiftyDialogBuilder withDuration(int duration) {
        this.mDuration = duration;
        return this;
    }

    public NiftyDialogBuilder withEffect(Effectstype type) {
        this.type = type;
        return this;
    }

    public NiftyDialogBuilder withButtonDrawable(int resid) {
        bt_dlg_left.setBackgroundResource(resid);
        bt_dlg_right.setBackgroundResource(resid);
        return this;
    }

    public NiftyDialogBuilder withButtonLeftText(CharSequence text) {
        if (text == null) {
            text = context.getResources().getString(R.string.confirm);
        }
        bt_dlg_left.setVisibility(View.VISIBLE);
        bt_dlg_left.setText(text);
        return this;
    }

    public NiftyDialogBuilder withButtonRightText(CharSequence text) {
        if (text == null) {
            text = context.getResources().getString(R.string.cancle);
        }
        bt_dlg_right.setVisibility(View.VISIBLE);
        bt_dlg_right.setText(text);
        return this;
    }

    public NiftyDialogBuilder setButtonLeftClick(View.OnClickListener click) {
        if (click == null) {
            click = defaultLsn;
        }
        bt_dlg_left.setOnClickListener(click);
        return this;
    }

    public NiftyDialogBuilder setButtonRightClick(View.OnClickListener click) {
        if (click == null) {
            click = defaultLsn;
        }
        bt_dlg_right.setOnClickListener(click);
        return this;
    }


    public NiftyDialogBuilder setCustomView(int resId, Context context) {
        View customView = View.inflate(context, resId, null);
        if (mFrameLayoutCustomView.getChildCount() > 0) {
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(customView);
        return this;
    }

    public NiftyDialogBuilder setCustomView(View view, Context context) {
        if (mFrameLayoutCustomView.getChildCount() > 0) {
            mFrameLayoutCustomView.removeAllViews();
        }
        mFrameLayoutCustomView.addView(view);

        return this;
    }

    public NiftyDialogBuilder isCancelableOnTouchOutside(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public NiftyDialogBuilder isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    private void toggleView(View view, Object obj) {
        if (obj == null) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    private View.OnClickListener defaultLsn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (dlg != null && dlg.isShowing()) {
                dlg.dismiss();
            }
        }
    };

    @Override
    public void show() {
        super.show();
    }

    private void start(Effectstype type) {
        BaseEffects animator = type.getAnimator();
        if (mDuration != -1) {
            animator.setDuration(Math.abs(mDuration));
        }
        animator.start(mRelativeLayoutView);
    }

    @Override
    public void dismiss() {
        super.dismiss();

    }
}
