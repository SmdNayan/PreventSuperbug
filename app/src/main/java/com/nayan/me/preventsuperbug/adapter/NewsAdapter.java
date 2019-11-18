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
import com.nayan.me.preventsuperbug.NewsDetailsActivity;
import com.nayan.me.preventsuperbug.R;
import com.nayan.me.preventsuperbug.entity.Article;
import com.nayan.me.preventsuperbug.entity.SuperbugNews;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<SuperbugNews> medicines;
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public SuperbugNews getItem(int pos) {
        return medicines.get(pos);
    }

    public void setNews(List<SuperbugNews> medicines) {
        this.medicines = medicines;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsHolder holder, final int position) {
        try {
            holder.title.setText(getItem(position).getNewsTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("news", new Gson().toJson(getItem(position)));
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

    class NewsHolder extends RecyclerView.ViewHolder {
        TextView title;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
