package com.builtlab.navigation.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.builtlab.navigation.core.repository.GemSpeakRepositoryImpl;
import com.builtlab.navigation.core.repository.IGemSpeakRepository;
import com.builtlab.navigation.modules.root.RootActivity;
import com.builtlab.navigation.R;
import com.builtlab.navigation.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        IGemSpeakRepository repo = new GemSpeakRepositoryImpl();
        SplashViewModel splashViewModel = new ViewModelProvider(this, new SplashViewModelFactory(repo)).get(SplashViewModel.class);
        splashViewModel.getHelloRs().observe(this, data -> {
            if (data != null) {
                binding.textView.setText(data);
                binding.textView.setVisibility(View.VISIBLE);
                finish();
                startActivity(new Intent(this, RootActivity.class));
            } else {
                binding.textView.setText(R.string.error_message);
                binding.textView.setVisibility(View.VISIBLE);
            }
        });
    }
}