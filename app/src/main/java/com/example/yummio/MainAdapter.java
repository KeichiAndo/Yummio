package com.example.yummio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.PlaceViewHolder> {

    private Context mContext;
    private ArrayList<Integer> cakeIds = new ArrayList<>();
    private ArrayList<String> cakeNames = new ArrayList<>();

    private final MainAdapterOnClickHandler mClickHandler;

    private int cakeId;

    public interface MainAdapterOnClickHandler {
        void onClick(int cakeId);
    }

    public MainAdapter(MainAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mText;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.tv_cake_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            cakeId = cakeIds.get(adapterPosition);
            Log.v("Cake ID", cakeId + "");
            try {
                mClickHandler.onClick(cakeId);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_main_layout, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.mText.setText(cakeNames.get(position));
    }

    @Override
    public int getItemCount() {
        if (cakeIds != null) {
            return cakeIds.size();
        } else return 0;
    }

    void setData(ArrayList<Integer> ids, ArrayList<String> names) {
        cakeIds = new ArrayList<>();
        cakeNames = new ArrayList<>();
        cakeIds.addAll(ids);
        cakeNames.addAll(names);
        notifyDataSetChanged();
    }
}
