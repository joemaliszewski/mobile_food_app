package cs.bham.ac.uk.assignment3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.List;

import cs.bham.ac.uk.assignment3.MainActivity;
import cs.bham.ac.uk.assignment3.R;
import cs.bham.ac.uk.assignment3.adapter.RecyclerViewAdapter;
import cs.bham.ac.uk.assignment3.entity.Food;



public class HomeFragement extends Fragment {
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);
        recyclerView = (RecyclerView) root.findViewById(R.id.productList);
        onRequestProducts();
        return root;
    }

    public void onRequestProducts(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, "https://www.sjjg.uk/eat/food-items", new Response.Listener<String>() {





            @Override
            public void onResponse(String response) {

                String reponsearray = response.toString();
                Log.d("pppppppppppppppppppppppppppp  "  ,reponsearray);

                List<Food> list = JSONObject.parseArray(response,Food.class);
//                list.get(1).getMeal()
                Log.d("fgfgfgfg",list.get(1).getMeal().toString());
                Log.d("fgfgfgfg",list.get(1).getTime().toString());


                Log.d("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD  ",response.toString());
                Log.d("FFFFFFFFFFFFFFFFFFFFFFF",list.toString());

                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list,getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adapter);



                        //recyclerView.getAdapter().

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
