package com.lac.xab.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.gc.materialdesign.widgets.SnackBar;
import com.lac.xaboa.R;

public class PublicNoticeView extends LinearLayout {

	private static final String TAG = "LILITH";
	private Activity activity;
	private ViewFlipper viewFlipper;
	private View scrollTitleView;
	private Intent intent;
	private ImageView main_notice_img;
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 1:
				bindNotices();
				break;

			case -1:
				break;
			}
		}
	};

	/**
	 * 构造
	 * 
	 * @param activity
	 */
	public PublicNoticeView(Activity activity) {
		super(activity);
        activity = activity;
		init();
	}

	public PublicNoticeView(Activity activity, AttributeSet attrs) {
		super(activity, attrs);
        activity = activity;
		init();

	}

	/**
	 * 网络请求后返回公告内容进行适配
	 */
	protected void bindNotices() {
		// TODO Auto-generated method stub
		viewFlipper.removeAllViews();
		int i = 0;
		while (i < 5) {
			String text = "您有 <font color='red'>"+i+"</font> 条信息 ! ";
			TextView textView = new TextView(activity);
			textView.setText(Html.fromHtml(text));
			textView.setOnClickListener(new NoticeTitleOnClickListener(
                    activity, i + text));
			LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			viewFlipper.addView(textView, lp);
			i++;
		}
	}

	private void init() {
		bindLinearLayout();
		Message msg = new Message();
		msg.what = 1;
		mHandler.sendMessageDelayed(msg, 3000);
	}

	/**
	 * 初始化自定义的布局
	 */
	public void bindLinearLayout() {
		scrollTitleView = LayoutInflater.from(activity).inflate(
				R.layout.main_public_notice_title, null);
		LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		addView(scrollTitleView, layoutParams);

		viewFlipper = (ViewFlipper) scrollTitleView
				.findViewById(R.id.flipper_scrollTitle);
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(activity,
				android.R.anim.slide_in_left));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(activity,
				android.R.anim.slide_out_right));
		viewFlipper.startFlipping();
		View v = viewFlipper.getCurrentView();
		main_notice_img = (ImageView) findViewById(R.id.main_notice_img);
		AnimationDrawable animationDrawable = (AnimationDrawable) main_notice_img
				.getBackground();
		animationDrawable.start();
	}

	/**
	 * 获取公告资讯
	 */
	public void getPublicNotices() {
		// 网络请求获取
	}

	/**
	 * 公告title监听
	 * 
	 * @author Nono
	 * 
	 */
	class NoticeTitleOnClickListener implements OnClickListener {
		private Activity activity;
		private String titleid;

		public NoticeTitleOnClickListener(Activity activity, String whichText) {
			this.activity = activity;
			this.titleid = whichText;
		}

		public void onClick(View v) {
			// TODO Auto-generated method stub

			disPlayNoticeContent(activity, titleid);
		}

	}

	/**
	 * 显示notice的具体内容
	 * @param
	 */
	public void disPlayNoticeContent(Activity activity, String titleid) {
        SnackBar.init(activity,titleid);
	}

}