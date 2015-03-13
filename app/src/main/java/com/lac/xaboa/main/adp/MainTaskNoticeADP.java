// +----------------------------------------------------------------------
// | FileName:   ${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: ${date}  ${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.main.adp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lac.xab.utils.DateTimeUtils;
import com.lac.xaboa.R;
import com.lac.xaboa.db.model.Notice;

import java.util.ArrayList;
import java.util.List;

public class MainTaskNoticeADP extends BaseAdapter {

    private LayoutInflater inflater;
    Context context;

    List<Notice> datas = new ArrayList<Notice>();

    public MainTaskNoticeADP(Context context, List<Notice> list) {
        this.context = context;
        this.datas = list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String date = "";
        NoticeHolder nh = null;
        Notice notice =datas.get(position);
        if (convertView == null) {
            nh = new NoticeHolder();
            convertView = inflater.inflate(R.layout.item_main_task_notice, null);
            nh.tv_main_task_notice_date = (TextView) convertView.findViewById(R.id.tv_main_task_notice_date);
            nh.tv_main_task_notice_title = (TextView) convertView.findViewById(R.id.tv_main_task_notice_title);
            nh.tv_main_task_notice_content = (TextView) convertView.findViewById(R.id.tv_main_task_notice_content);
            convertView.setTag(nh);
        } else {
            nh = (NoticeHolder) convertView.getTag();
        }
        date = DateTimeUtils.getValueByTime(Long.parseLong(notice.getCreate_time()));
        nh.tv_main_task_notice_date.setText(date);
        nh.tv_main_task_notice_title.setText(notice.getName());
        nh.tv_main_task_notice_content.setText("\t"+notice.getContent());
        return convertView;
    }

    public static class NoticeHolder {
        public TextView tv_main_task_notice_date;
        public TextView tv_main_task_notice_title;
        public TextView tv_main_task_notice_content;
    }
}
