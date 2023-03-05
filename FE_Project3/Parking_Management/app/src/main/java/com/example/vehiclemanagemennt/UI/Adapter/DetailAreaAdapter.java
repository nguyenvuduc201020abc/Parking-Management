package com.example.vehiclemanagemennt.UI.Adapter;

import android.content.Context;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailAreaAdapter extends RecyclerView.Adapter<DetailAreaAdapter.AreaViewHolder> {
    public static final String TAG = DetailAreaAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<AreaDetail> mListAreaDetail;
    private OnClickItemListener itemListener;
    public DetailAreaAdapter(Context context, ArrayList<AreaDetail> mListAreaDetail, OnClickItemListener itemListener) {
        this.mContext = context;
        this.mListAreaDetail = mListAreaDetail;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.area_item, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void setStateRestorationPolicy(@NonNull StateRestorationPolicy strategy) {
        super.setStateRestorationPolicy(strategy);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        final AreaDetail areaDetail = mListAreaDetail.get(position);
        holder.tvAreaName.setText(mListAreaDetail.get(position).getmNameArea());
        if (!areaDetail.isAvailable()) {
            Log.d(TAG, "onBindViewHolder: TRue ne " + areaDetail.getmNameArea());
            holder.imgStatus.setImageResource(R.drawable.ic_available);
        } else {
            holder.imgStatus.setImageResource(R.drawable.ic_not_available);
        }
        if (!areaDetail.isChosen()) {
            holder.item.setVisibility(View.GONE);
        } else {
            holder.item.setVisibility(View.VISIBLE);
        }
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onItemListener(mListAreaDetail.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListAreaDetail.size();
    }

    public static class AreaViewHolder extends RecyclerView.ViewHolder{
        private TextView tvAreaName;
        private ImageView imgStatus;
        private ConstraintLayout item;
        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAreaName = itemView.findViewById(R.id.text_area_detail);
            imgStatus = itemView.findViewById(R.id.img_status);
            item = itemView.findViewById(R.id.list_item);
        }
    }
    public interface OnClickItemListener {
        void onItemListener(AreaDetail areaDetail);
    }
}
