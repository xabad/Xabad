// +----------------------------------------------------------------------
// | FileName:   @${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: @${date}  @${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xab.utils;

import android.app.Activity;
import android.view.View;

import com.lac.xab.ui.NiftyDialogBuilder;
import com.lac.xab.ui.effects.Effectstype;
import com.lac.xaboa.R;


public class DialogUtils {
    static NiftyDialogBuilder dialogBuilder;

    public static void show(Activity activity, String message) {
        show(activity, R.drawable.ic_dlg_header,null,message,null,null,null,null);
    }
    public static void show(Activity activity, String title, String message) {
        show(activity,R.drawable.ic_dlg_header,title,message,null,null,null,null);
    }
    public static void show(Activity activity, String title, String message, String leftText) {
        show(activity,R.drawable.ic_dlg_header,title,message,leftText,null,null,null);
    }
    public static void show(Activity activity, String title, String message, String leftText, View.OnClickListener leftLsn) {
        show(activity,R.drawable.ic_dlg_header,title,message,leftText,null,leftLsn,null);
    }
    public static void show(Activity activity, String title, String message, String leftText, String rightText, View.OnClickListener leftLsn, View.OnClickListener rightLsn) {
        show(activity,R.drawable.ic_dlg_header,title,message,leftText,rightText,leftLsn,rightLsn);
    }
    public static void show(Activity activity, int iconId, String title, String message, String leftText, String rightText, View.OnClickListener leftLsn, View.OnClickListener rightLsn) {
        dismiss();
        dialogBuilder = NiftyDialogBuilder.getInstance(activity);
        Effectstype effect = Effectstype.Slidetop;
        dialogBuilder.withTitle(title)
                .seTouchViewtCancle(false)
                .withTitleColor("#FFFFFF")
                .withDividerColor("#11000000")
                .withMessage(message)
                .withMessageColor("#FFFFFF")
                .withIcon(activity.getResources().getDrawable(iconId))
                .isCancelableOnTouchOutside(true) // def | isCancelable(true)
                .withDuration(700) // def
                .withEffect(effect) // def Effectstype.Slidetop
                .withButtonLeftText(leftText) // def gone
                .withButtonRightText(rightText) // def gone
                .setButtonLeftClick(leftLsn)
                .setButtonRightClick(rightLsn).show();
    }

    public static void dismiss(){
        if (dialogBuilder!=null && dialogBuilder.isShowing()){
            dialogBuilder.dismiss();
        }
    }
}
