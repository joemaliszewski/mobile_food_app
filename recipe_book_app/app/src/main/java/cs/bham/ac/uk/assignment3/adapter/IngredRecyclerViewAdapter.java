package cs.bham.ac.uk.assignment3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cs.bham.ac.uk.assignment3.FoodDetailsActivity;
import cs.bham.ac.uk.assignment3.R;
import cs.bham.ac.uk.assignment3.entity.Food;

public class IngredRecyclerViewAdapter extends RecyclerView.Adapter<IngredRecyclerViewAdapter.ViewHolder>{
        private String[] foodList;


        public IngredRecyclerViewAdapter(String[] listdata) {
            this.foodList = listdata;

        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.layout_ingredients, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }



    @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final String item = foodList[position];
            holder.name.setText(item);
        }


        @Override
        public int getItemCount() {
            return foodList.length;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView name;


            public ViewHolder(View itemView) {
                super(itemView);
                this.name = (TextView) itemView.findViewById(R.id.item);

            }
        }
}
