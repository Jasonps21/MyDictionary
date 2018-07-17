package com.example.jason.mydictionary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jason.mydictionary.KamusDetail;
import com.example.jason.mydictionary.Model.KamusModel;
import com.example.jason.mydictionary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.KamusViewHolder> {

    private Context context;
    private ArrayList<KamusModel> kamusModels;
    private LayoutInflater mInflater;

    public KamusAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public KamusAdapter.KamusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamus_row, parent, false);
        return new KamusViewHolder(view);
    }

    public void addItem(ArrayList<KamusModel> mData){
        this.kamusModels = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final KamusAdapter.KamusViewHolder holder, final int position) {
        holder.tvKata.setText(kamusModels.get(position).getKata());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KamusDetail.class);
                intent.putExtra(KamusDetail.EXTRA_KATA, kamusModels.get(position).getKata());
                intent.putExtra(KamusDetail.EXTRA_ARTI, kamusModels.get(position).getArti());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kamusModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class KamusViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvKata)
        TextView tvKata;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;

        public KamusViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
