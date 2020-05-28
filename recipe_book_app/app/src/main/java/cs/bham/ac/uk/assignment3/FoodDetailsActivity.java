package cs.bham.ac.uk.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import cs.bham.ac.uk.assignment3.adapter.IngredRecyclerViewAdapter;
import cs.bham.ac.uk.assignment3.entity.Food;
import cs.bham.ac.uk.assignment3.entity.Recipe;
import cs.bham.ac.uk.assignment3.adapter.RecyclerViewAdapter;

public class FoodDetailsActivity extends AppCompatActivity {
    TextView description;
    String name_value;
    TextView name;
    RecyclerView recyclerView;
    RecyclerView recyclerViewSteps;
    private List<Food> foodList;
    Button favButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity__food_details);
        recyclerView = (RecyclerView) findViewById(R.id.ingredient_recycle_view);
        recyclerViewSteps = (RecyclerView) findViewById(R.id.steps_recycle_view);
        name = (TextView) findViewById(R.id.food_name);
        description = (TextView) findViewById(R.id.food_description);
        favButton = (Button) findViewById(R.id.favourite_button);

        Intent intent = getIntent();
        final Food food = (Food)intent.getSerializableExtra("food");
        name_value = food.getName();
        final SharedPreferences pref = getApplicationContext().getSharedPreferences("Cooking", 0);
        final SharedPreferences.Editor editor = pref.edit();

        String json = pref.getString("favourite",null);
        if(json!=null){
            foodList = JSONObject.parseArray(json,Food.class);
            if(foodList.contains(food)) {
                favButton.setText("Remove Favourite");
            }
        }
        onRequestProducts(food.getId()+"");
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String json = pref.getString("favourite",null);
                if(json==null){
                    foodList= new ArrayList<>();
                    foodList.add(food);
                    String foods = JSON.toJSONString(foodList);
                    editor.putString("favourite", foods);
                    editor.commit();
                    Toast.makeText(FoodDetailsActivity.this, "add successfully", Toast.LENGTH_SHORT).show();
                    favButton.setText("Remove Favourite");
                }else{
                    foodList = JSONObject.parseArray(json,Food.class);
                    if(foodList.contains(food)){
                        foodList.remove(food);
                        if(foodList.size()==0){
                            editor.putString("favourite", null);
                            editor.commit();
                            Toast.makeText(FoodDetailsActivity.this, "removed successfully", Toast.LENGTH_SHORT).show();
                            favButton.setText("Add Favourite");
                        }
                        String foods = JSON.toJSONString(foodList);
                        editor.putString("favourite", foods);
                        editor.commit();
                        Toast.makeText(FoodDetailsActivity.this, "removed successfully", Toast.LENGTH_SHORT).show();
                        favButton.setText("Add Favourite");
                    }else{
                        foodList.add(food);
                        String foods = JSON.toJSONString(foodList);
                        editor.putString("favourite", foods);
                        editor.commit();
                        Toast.makeText(FoodDetailsActivity.this, "added successfully", Toast.LENGTH_SHORT).show();
                        favButton.setText("Remove Favourite");
                    }

                }



            }
        });
    }



    public void onRequestProducts(String id){
        RequestQueue requestQueue = Volley.newRequestQueue(FoodDetailsActivity.this);
        StringRequest request = new StringRequest(Request.Method.GET, "https://www.sjjg.uk/eat/recipe-details/"+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Recipe recipe = JSONObject.parseObject(response, Recipe.class);
                IngredRecyclerViewAdapter adapter = new IngredRecyclerViewAdapter(recipe.getIngredients());

                recyclerView.setLayoutManager(new LinearLayoutManager(FoodDetailsActivity.this));
                recyclerView.setAdapter(adapter);

                IngredRecyclerViewAdapter adapter2 = new IngredRecyclerViewAdapter(recipe.getSteps());
                recyclerViewSteps.setLayoutManager(new LinearLayoutManager(FoodDetailsActivity.this));
                recyclerViewSteps.setAdapter(adapter2);

                name.setText(name_value);
                description.setText(recipe.getDescription());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });

        requestQueue.add(request);

    }





}
