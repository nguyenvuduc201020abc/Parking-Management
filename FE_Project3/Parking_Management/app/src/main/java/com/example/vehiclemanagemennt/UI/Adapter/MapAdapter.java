package com.example.vehiclemanagemennt.UI.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vehiclemanagemennt.Data.DB.Model.AreaDetail;
import com.example.vehiclemanagemennt.R;
import com.example.vehiclemanagemennt.UI.AreaDetail.AreaDetailActivity;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MapViewHolder> {
    public static final String TAG = DetailAreaAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<AreaDetail> mListAreaDetail;
    private OnClickItemListener itemListener;
    public MapAdapter(Context context, ArrayList<AreaDetail> mListAreaDetail, OnClickItemListener itemListener) {
        this.mContext = context;
        this.mListAreaDetail = mListAreaDetail;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.area_item, parent, false);
        return new MapViewHolder(view);
    }

    @Override
    public void setStateRestorationPolicy(@NonNull StateRestorationPolicy strategy) {
        super.setStateRestorationPolicy(strategy);
    }

    @Override
    public void onBindViewHolder(@NonNull MapViewHolder holder, int position) {
        final AreaDetail areaDetail = mListAreaDetail.get(position);
        holder.tvAreaName.setText(mListAreaDetail.get(position).getmNameArea());
        if (!areaDetail.isChosen()) {
            holder.cardView.setBackgroundColor(Color.parseColor("#D9D9D9"));
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onItemListener(mListAreaDetail.get(position));
                if (areaDetail.isChosen()) {
                    holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                } else {
                    holder.cardView.setBackgroundColor(Color.parseColor("#D9D9D9"));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListAreaDetail.size();
    }

    public static class MapViewHolder extends RecyclerView.ViewHolder{
        private TextView tvAreaName;
        private ImageView imgStatus;
        private ConstraintLayout item;
        private CardView cardView;
        public MapViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tvAreaName = itemView.findViewById(R.id.text_area_detail);
            imgStatus = itemView.findViewById(R.id.img_status);
            item = itemView.findViewById(R.id.list_item);
        }
    }
    public interface OnClickItemListener {
        void onItemListener(AreaDetail areaDetail);
    }
}

