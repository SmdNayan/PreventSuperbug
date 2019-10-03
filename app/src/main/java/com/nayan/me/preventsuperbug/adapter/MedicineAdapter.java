package com.nayan.me.preventsuperbug.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nayan.me.preventsuperbug.R;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineHolder> {

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.antibiotic_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MedicineHolder extends RecyclerView.ViewHolder {

        public MedicineHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
