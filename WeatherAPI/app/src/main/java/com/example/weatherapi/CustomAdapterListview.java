package com.example.weatherapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapterListview extends BaseAdapter {
    Context context;
    ArrayList<Thoitiet> arrayList;

    public CustomAdapterListview(Context context, ArrayList<Thoitiet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.activity_custom_listview,null);
//      LayoutInflater layoutInflater = LayoutInflater.from(context);
//      view = layoutInflater.inflate(R.layout.activity_custom_listview,null);

        Thoitiet thoitiet = arrayList.get(i);

        TextView textViewDay = view.findViewById(R.id.textViewNgayThang);
        TextView textViewTrangThai = view.findViewById(R.id.textViewTrangThai);
        ImageView imageViewListView = view.findViewById(R.id.imageViewlistView);
        TextView textViewMaxTemp = view.findViewById(R.id.textViewMaxtemp);
        TextView textViewMinTemp = view.findViewById(R.id.textViewMinTemp);

        textViewDay.setText(thoitiet.Day);
        textViewMaxTemp.setText(thoitiet.MaxTemp+"oC");
        textViewMinTemp.setText(thoitiet.MinTemp+"oC");
        textViewTrangThai.setText(thoitiet.Status);

        Picasso.with(context).load("http://openweathermap.org/img/wn/"+thoitiet.Image+"@2x.png").into(imageViewListView);

        return view;
    }
}
