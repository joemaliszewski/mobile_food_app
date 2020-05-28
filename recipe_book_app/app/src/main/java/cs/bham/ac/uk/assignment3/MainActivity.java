package cs.bham.ac.uk.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        //navigation for host fragment
        return Navigation.findNavController(this, R.id.navigation_host_fragment).navigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_view);
        NavController navigationController = Navigation.findNavController(this, R.id.navigation_host_fragment);
        NavigationUI.setupWithNavController(bottomNav, navigationController);

    }



}
