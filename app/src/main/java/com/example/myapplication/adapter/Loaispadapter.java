package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Loaispadapter extends BaseAdapter {
    List<loaisp> arrayListloaisp;
    Context context;
    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    public Loaispadapter(ArrayList<loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder {
        TextView textViewloaisp;
        ImageView imgViewloaisp;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  layoutInflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.textViewloaisp = (TextView) convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgViewloaisp = (ImageView) convertView.findViewById(R.id.loaispimg);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        loaisp loaispp = (loaisp) getItem(position);
        viewHolder.textViewloaisp.setText(loaispp.getTenLoaisp());
        Picasso.with(context).load(loaispp.getHinhAnhsp()).placeholder(R.drawable.common_full_open_on_phone)
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(viewHolder.imgViewloaisp);

        return convertView;
    }
}
