package com.nayan.me.preventsuperbug.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nayan.me.preventsuperbug.AntibioticDetailsActivity;
import com.nayan.me.preventsuperbug.R;
import com.nayan.me.preventsuperbug.entity.Medicine;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineHolder> {

    private List<Medicine> medicines;
    private Context context;

    public MedicineAdapter(Context context) {
        this.context = context;
    }

    public Medicine getItem(int pos) {
        return medicines.get(pos);
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.antibiotic_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MedicineHolder holder, final int position) {
        try {
            holder.medicineNameTv.setText(getItem(position).getName());
            if (getItem(position).getMedicineGroup() != null)
                holder.groupNameTv.setText(getItem(position).getMedicineGroup().getGroupName());
            if (getItem(position).getMedicineCompany() != null)
                holder.companyNameTv.setText(getItem(position).getMedicineCompany().getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AntibioticDetailsActivity.class);
                    intent.putExtra("medicine", new Gson().toJson(getItem(position)));
                    context.startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    class MedicineHolder extends RecyclerView.ViewHolder {
        TextView medicineNameTv;
        TextView groupNameTv;
        TextView companyNameTv;


        public MedicineHolder(@NonNull View itemView) {
            super(itemView);
            medicineNameTv = itemView.findViewById(R.id.medicine_name_tv);
            groupNameTv = itemView.findViewById(R.id.group_name_tv);
            companyNameTv = itemView.findViewById(R.id.company_name_tv);
        }
    }
}
