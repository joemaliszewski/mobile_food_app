package cs.bham.ac.uk.assignment3.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import cs.bham.ac.uk.assignment3.R;
import cs.bham.ac.uk.assignment3.adapter.RecyclerViewAdapter;
import cs.bham.ac.uk.assignment3.entity.Food;

public class FavFragment extends Fragment {
    RecyclerView recyclerView;
     SharedPreferences pref;
     SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.favourite_fragment, null);
        recyclerView = (RecyclerView) root.findViewById(R.id.favourite_list);
        pref = getContext().getSharedPreferences("Cooking", 0);
        editor = pref.edit();
        onRequestProducts();
        return root;

    }
    public void onRequestProducts(){

        String json = pref.getString("favourite",null);
        if(json!=null){

            RecyclerViewAdapter adapter = new RecyclerViewAdapter( JSONObject.parseArray(json, Food.class),getContext());

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        }
    }

}





