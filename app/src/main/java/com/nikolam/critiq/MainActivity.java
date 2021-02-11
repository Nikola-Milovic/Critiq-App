package com.nikolam.critiq;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import com.nikolam.critiq.databinding.ActivityMainBinding;
import com.nikolam.critiq.navigation.NavManager;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private NavController navController;// = findNavController(R.id.navHostFragment)

    private ActivityMainBinding binding;

    private NavManager navManager;

    private void initNavManager() {
        navManager.setOnNavEvent(uri ->
                navController.navigate(uri)
        );

        navManager.setPopBackStack ((Void) ->
            navController.popBackStack()
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}