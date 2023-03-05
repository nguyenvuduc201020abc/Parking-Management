package com.example.vehiclemanagemennt.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclemanagemennt.Data.DB.Model.AreaDetail;
import com.example.vehiclemanagemennt.Data.DB.Model.BillDetail;
import com.example.vehiclemanagemennt.R;

import java.util.ArrayList;
import java.util.List;

public class BillInfoAdapter extends RecyclerView.Adapter<BillInfoAdapter.BillViewHolder>{
    public static final String TAG = BillInfoAdapter.class.getSimpleName();
    private Context mContext;
    private List<BillDetail> mListBillDetail;

    public BillInfoAdapter(Context context, List<BillDetail> mListAreaDetail) {
        this.mContext = context;
        this.mListBillDetail = mListAreaDetail;
    }


    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bill_item, parent, false);
        return new BillInfoAdapter.BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        final BillDetail billDetail = mListBillDetail.get(position);
        holder.tvBillId.setText(Integer.toString(billDetail.getBillId()));
        holder.tvLicense.setText(billDetail.getLicense());
        holder.tvEntry.setText(billDetail.getEntry());
        holder.tvOut.setText(billDetail.getOut());
        holder.tvCost.setText(Integer.toString(billDetail.getCost()));
    }

    @Override
    public int getItemCount() {
        return mListBillDetail.size();
    }

    public static class BillViewHolder extends RecyclerView.ViewHolder{
        private TextView tvBillId, tvLicense, tvEntry, tvOut, tvCost;
        private ImageView imgStatus;
        private ConstraintLayout item;
        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBillId = itemView.findViewById(R.id.tv_billId);
            tvLicense = itemView.findViewById(R.id.tv_licenseVehicle);
            tvEntry = itemView.findViewById(R.id.tv_entry_time);
            tvOut = itemView.findViewById(R.id.tv_out);
            tvCost = itemView.findViewById(R.id.tv_price);
        }
    }
}
