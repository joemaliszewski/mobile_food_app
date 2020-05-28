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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
        private List<Food> foodList;

    Context context;

        public RecyclerViewAdapter(List<Food> listdata, Context context) {
            this.foodList = listdata;
            this.context = context;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.fooditem, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }



    @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Food food = foodList.get(position);
            holder.name.setText(food.getName());
            holder.meal.setText(food.getMeal());
            holder.time.setText(food.getTime()+"mins");


            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FoodDetailsActivity.class);
                    intent.putExtra("food",food);
                    context.startActivity(intent);
                }
            });
        }


        @Override
        public int getItemCount() {
            return foodList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public TextView meal;
            public TextView time;
            public LinearLayout linearLayout;
            public ViewHolder(View itemView) {
                super(itemView);
                this.name = (TextView) itemView.findViewById(R.id.food_name);
                this.meal = (TextView) itemView.findViewById(R.id.meal);
                this.time = (TextView)itemView.findViewById(R.id.foodtime);
                this.linearLayout= (LinearLayout)itemView.findViewById(R.id.fooditem);
            }
        }
}
