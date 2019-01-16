package com.example.faizanwar.weatherguru.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faizanwar.weatherguru.R;

import java.util.ArrayList;

import Data.LocatorPlaces;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<LocatorPlaces> mSearchListResults;
    private Context mContext;
    private PlaceClickListener clickListener;

    public interface PlaceClickListener {
        void onPlaceClicked(LocatorPlaces place);
    }

    public SearchResultAdapter(ArrayList<LocatorPlaces> msearchListResults, Context mContext) {
        this.mSearchListResults = msearchListResults;
        this.mContext = mContext;
//        this.clickListener = (PlaceClickListener) mContext;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.content_search_list_recycler_view, viewGroup, false);
        return new SearchResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SearchResultViewHolder searchResultViewHolder = (SearchResultViewHolder) viewHolder;
        final LocatorPlaces place = mSearchListResults.get(position);
        searchResultViewHolder.name.setText(place.getmPlaceName());
        searchResultViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onPlaceClicked(place);
            }
        });
        if (position == mSearchListResults.size() - 1) {
            searchResultViewHolder.divider.setVisibility(View.GONE);
        } else {
            searchResultViewHolder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mSearchListResults.size();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private View divider;
        private View container;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.SearchResultItemLayout);
            name = itemView.findViewById(R.id.SearchResultLabel);
            divider = itemView.findViewById(R.id.SearchResultDividerLayout);
        }
    }
}
