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

import com.example.vehiclemanagemennt.Data.DB.Model.BillDetail;
import com.example.vehiclemanagemennt.Data.DB.Model.VehicleDetail;
import com.example.vehiclemanagemennt.Data.Network.Model.VehicleInfo;
import com.example.vehiclemanagemennt.R;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.BillViewHolder>{
    public static final String TAG = VehicleAdapter.class.getSimpleName();
    private Context mContext;
    private List<VehicleDetail> mListVehicle;

    public VehicleAdapter(Context context, List<VehicleDetail> mListVehicle) {
        this.mContext = context;
        this.mListVehicle = mListVehicle;
    }


    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vehicle_item, parent, false);
        return new VehicleAdapter.BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        final VehicleDetail billDetail = mListVehicle.get(position);
        holder.tvType.setText(billDetail.getTypeVehicle());
        holder.tvLicense.setText(billDetail.getLicenseVehicle());
        holder.tvEntry.setText(billDetail.getEntryTime());
        holder.tvLocate.setText(billDetail.getLocate());
        holder.tvColor.setText(billDetail.getColor());
        holder.tvUserId.setText(billDetail.getUsername());
        holder.tvImage.setText(billDetail.getImage());
    }

    @Override
    public int getItemCount() {
        return mListVehicle.size();
    }

    public static class BillViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUserId, tvLicense, tvEntry, tvColor, tvType, tvLocate, tvImage;
        private ImageView imgStatus;
        private ConstraintLayout item;
        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tv_username);
            tvLicense = itemView.findViewById(R.id.tv_licenseVehicle);
            tvEntry = itemView.findViewById(R.id.tv_entry);
            tvColor = itemView.findViewById(R.id.tv_color);
            tvLocate = itemView.findViewById(R.id.tv_locate);
            tvImage = itemView.findViewById(R.id.tv_image);
            tvType = itemView.findViewById(R.id.tv_type_vehicle);
        }
    }
}
