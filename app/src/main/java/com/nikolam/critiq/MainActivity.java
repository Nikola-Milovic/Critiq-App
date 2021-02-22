package com.nikolam.critiq;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.nikolam.common.navigation.NavManager;
import com.nikolam.critiq.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    NavManager navManager;
    private NavController navController; //
    private ActivityMainBinding binding;

    private void initNavManager() {
        Timber.d("Nav manager is %s", navManager.toString());
        navManager.setOnNavEvent(uri ->
                navController.navigate(uri)
        );

        navManager.setPopBackStack((Void) ->
                navController.popBackStack()
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        initNavManager();
    }
}