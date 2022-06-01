package com.l_es.communityrecipes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.l_es.communityrecipes.R;

import java.util.List;


/**
 * Created by Lidor on 04/20/2022.
 * Developer name: L-ES
 *  _        _   _____     ____    ______
 * | |      |_| |  __ \   / __ \  |  O   |
 * | |      | | | |  | | | |  | | |   ___/
 * | |____  | | | |__| | | |__| | | | \
 * |______| |_| |_____/   \____/  |_|__\
 *  ____         ____
 * |  __|       |  __|
 * |  __|   _   |__  |
 * |____|  |_|  |____|
 */
public class RVCategoriesAdapter extends RecyclerView.Adapter<RVCategoriesAdapter.CategoriesViewHolder> {

    private final List<String> categories;
    private OnItemClickListener mListener;
    protected LayoutInflater categoriesInflater;
    protected Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        public TextView category_name;

        public CategoriesViewHolder(View itemView, OnItemClickListener _listener) {
            super(itemView);
            category_name = itemView.findViewById(R.id.neumorphic_category_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(_listener != null){
                        int position = getBindingAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            _listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public RVCategoriesAdapter(Context context, List<String> _categories){
        this.categories = _categories;
        this.context = context;
        this.categoriesInflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = this.categoriesInflater.inflate(R.layout.categories_layout, parent, false);
        return new CategoriesViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        holder.category_name.setText(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
