package com.nikolam.critiq;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.nikolam.critiq.databinding.ActivityMainBinding;
import com.nikolam.common.navigation.NavManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private NavController navController ; //

//    private ActivityMainBinding binding;

    @Inject
    NavManager navManager;

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

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        initNavManager();
    }
}