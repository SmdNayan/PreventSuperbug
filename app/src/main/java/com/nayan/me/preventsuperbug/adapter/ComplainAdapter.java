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
import com.nayan.me.preventsuperbug.ArticleDetailsActivity;
import com.nayan.me.preventsuperbug.ComplainDetailsActivity;
import com.nayan.me.preventsuperbug.R;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.Complain;

import java.util.List;

public class ComplainAdapter extends RecyclerView.Adapter<ComplainAdapter.ComplainHolder> {

    private List<Complain> complains;
    private Context context;

    public ComplainAdapter(Context context) {
        this.context = context;
    }

    public Complain getItem(int pos) {
        return complains.get(pos);
    }

    public void setComplains(List<Complain> complains) {
        this.complains = complains;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComplainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ComplainHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.complain_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ComplainHolder holder, final int position) {
        try {
            holder.providerName.setText(getItem(position).getProviderName());
            holder.farmacyName.setText(getItem(position).getFarmacyName());
            if (getItem(position).getComplainId() != null)
                holder.complainNo.setText("Complain No: #" + getItem(position).getComplainId());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ComplainDetailsActivity.class);
                    intent.putExtra("complain", new Gson().toJson(getItem(position)));
                    context.startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return complains.size();
    }

    class ComplainHolder extends RecyclerView.ViewHolder {
        TextView providerName;
        TextView farmacyName;
        TextView address;
        TextView complainNo;


        public ComplainHolder(@NonNull View itemView) {
            super(itemView);
            providerName = itemView.findViewById(R.id.providerName);
            farmacyName = itemView.findViewById(R.id.farmacyName);
            address = itemView.findViewById(R.id.address);
            complainNo = itemView.findViewById(R.id.complainNo);
        }
    }
}
