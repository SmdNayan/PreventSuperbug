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
import com.nayan.me.preventsuperbug.ArticleActivity;
import com.nayan.me.preventsuperbug.ArticleDetailsActivity;
import com.nayan.me.preventsuperbug.R;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.Medicine;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MedicineHolder> {

    private List<Article> medicines;
    private Context context;

    public ArticleAdapter(Context context) {
        this.context = context;
    }

    public Article getItem(int pos) {
        return medicines.get(pos);
    }

    public void setArticles(List<Article> medicines) {
        this.medicines = medicines;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MedicineHolder holder, final int position) {
        try {
            holder.title.setText(getItem(position).getArticleTitle());
            holder.shortDes.setText(getItem(position).getShortDesc());
            if (getItem(position).getUser() != null)
                holder.doctorName.setText(getItem(position).getUser().getFullName());
            if (getItem(position).getPublishedAt() != null)
                holder.pdate.setText(getItem(position).getPublishedAt());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ArticleDetailsActivity.class);
                    intent.putExtra("article", new Gson().toJson(getItem(position)));
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
        TextView title;
        TextView shortDes;
        TextView doctorName;
        TextView pdate;


        public MedicineHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            shortDes = itemView.findViewById(R.id.shortDes);
            doctorName = itemView.findViewById(R.id.doctorName);
            pdate = itemView.findViewById(R.id.pdate);
        }
    }
}
