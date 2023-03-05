package com.example.vehiclemanagemennt.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vehiclemanagemennt.Data.DB.Model.Area;
import com.example.vehiclemanagemennt.R;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Area> mListArea;
    private LayoutInflater layoutInflater;

    public GridAdapter(Context context, List<Area> mListArea) {
        this.mContext = context;
        this.mListArea = mListArea;
    }

    @Override
    public int getCount() {
        return mListArea.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null) {
            view = layoutInflater.inflate(R.layout.grid_item, null);
        }

        TextView txtNameArea = view.findViewById(R.id.text_area);
        TextView txtNumber = view.findViewById(R.id.text_number_parks);
        TextView txtVacancy = view.findViewById(R.id.text_vacancy);
        txtNameArea.setText("Khu " + mListArea.get(i).getmNameArea());
        txtNumber.setText("Số lượng: " + mListArea.get(i).getmNoPark());
        txtVacancy.setText("Còn trống: " + mListArea.get(i).getmNoVacancy());
        return view;
    }
}
