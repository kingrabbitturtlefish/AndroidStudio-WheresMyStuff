package edu.gatech.cs2340.wheresmystuff.controllers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.wheresmystuff.R;
import edu.gatech.cs2340.wheresmystuff.model.Item;
import edu.gatech.cs2340.wheresmystuff.model.LostItem;
import edu.gatech.cs2340.wheresmystuff.model.Model;

public class LostItemListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostitem_list);

        // Setup recycler view
        View recyclerView = findViewById(R.id.lostitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        Log.d("Lost Item List", "recycler view init");
        Model model = Model.getInstance();
        recyclerView.setAdapter(new LostItemListRVAdapter(model.getLostItemManager().getItems()));

    }

    class LostItemListRVAdapter extends
            RecyclerView.Adapter<LostItemListRVAdapter.ViewHolder> {

        // Items to be shown on list
        private final List<Item> lostItems;

        /**
         * Constructor
         *
         * @param items the items to be displayed in the recycler view
         */
        LostItemListRVAdapter(List<Item> items) {
            lostItems = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lostitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.d("Lost Item List", "Binding model data");
            holder.lostItem = lostItems.get(position);
            holder.nameTextView.setText(lostItems.get(position).getName());
            holder.descriptionTextView.setText(lostItems.get(position).getDescription());
            holder.locationTextView.setText(((LostItem)lostItems.get(position)).getPlaceLost());
            // TODO: Set up click listener here later on in the project
        }

        @Override
        public int getItemCount() {
            return lostItems.size();
        }

        /*
         * Inner ViewHolder class used to cache information about binding between model
         * and text views.
         */
         class ViewHolder extends RecyclerView.ViewHolder {
            final TextView nameTextView;
            final TextView descriptionTextView;
            final TextView locationTextView;
            Item lostItem;

            ViewHolder(View view) {
                super(view);
                nameTextView = (TextView) view.findViewById(R.id.tv_name);
                descriptionTextView = (TextView) view.findViewById(R.id.tv_description);
                locationTextView = (TextView) view.findViewById(R.id.tv_location);
            }
        }
    }
}
