package com.nayan.me.preventsuperbug.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nayan.me.preventsuperbug.R;

import java.util.List;

public class MentorsDoctorAd extends BaseAdapter {

    private Context context;
    private List<String> doctorName;
    private List<Bitmap> doctorImg;
    private List<String> specialist;

    public MentorsDoctorAd(Context context, List<String> doctorName, List<Bitmap> doctorImg, List<String> specialist) {
        this.context = context;
        this.doctorName = doctorName;
        this.doctorImg = doctorImg;
        this.specialist = specialist;
    }

    @Override
    public int getCount() {
        return doctorName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.mentors_doctor_item_list, null);
        } else {
            gridView = convertView;
        }

        TextView dctrName = gridView.findViewById(R.id.dctr_name_tv);
        TextView specialistTv = gridView.findViewById(R.id.specialist_name);
        ImageView dctrIV = gridView.findViewById(R.id.doctor_iv);

        dctrName.setText(doctorName.get(position));
        specialistTv.setText(specialist.get(position));
        dctrIV.setImageBitmap(doctorImg.get(position));

        return gridView;
    }
}
