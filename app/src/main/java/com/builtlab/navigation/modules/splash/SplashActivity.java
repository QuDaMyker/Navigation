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

import com.builtlab.navigation.actions.onTwoSideActions;
import com.builtlab.navigation.core.repository.GemSpeakRepositoryImpl;
import com.builtlab.navigation.core.repository.IGemSpeakRepository;
import com.builtlab.navigation.core.storage.UserStorage;
import com.builtlab.navigation.modules.root.RootActivity;
import com.builtlab.navigation.R;
import com.builtlab.navigation.databinding.ActivitySplashBinding;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        UserStorage userStorage;
        try {
            userStorage = new UserStorage(this);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IGemSpeakRepository repo = new GemSpeakRepositoryImpl();
        splashViewModel = new ViewModelProvider(this, new SplashViewModelFactory(repo, userStorage)).get(SplashViewModel.class);
        checkConnectedServer(new onTwoSideActions<Boolean>() {
            @Override
            public void onSuccess(Boolean users) {
                reLogin("");
            }

            @Override
            public void onError(String errorMessage) {
                reLogin(errorMessage);
            }
        });
    }

    private void checkConnectedServer(final onTwoSideActions<Boolean> callback) {
        splashViewModel.getHelloRs().observe(this, data -> {
            if (data != null) {
                binding.textView.setText(data);
                binding.textView.setVisibility(View.VISIBLE);
                callback.onSuccess(true);
            } else {
                String errorMessage = getString(R.string.error_message);
                binding.textView.setText(errorMessage);
                binding.textView.setVisibility(View.VISIBLE);
                callback.onError(errorMessage);
            }
        });
    }

    private void reLogin(String error) {
        splashViewModel.login().observe(this, isSuccess -> {
            if (isSuccess != null && isSuccess) {
                finish();
                startActivity(new Intent(this, RootActivity.class));
            } else {
                binding.textView.setText(error);
                binding.textView.setVisibility(View.VISIBLE);
            }
        });
    }
}