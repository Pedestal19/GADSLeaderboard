package gabe.hosanna.gadsleaderboard.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import gabe.hosanna.gadsleaderboard.R;
import gabe.hosanna.gadsleaderboard.databinding.ActivityMainBinding;

public class SplashActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        goToHomescreen();

        new Handler().postDelayed(() -> {

        }, 5000);
    }

    public void goToHomescreen() {
        new Handler().postDelayed(() -> {
            //Navigate to next page.
            startActivity(new Intent(SplashActivity.this, HomepageActivity.class));
            finish();
        }, 5000);
    }
}