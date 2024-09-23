package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class RateServiceActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_rate_service);

    }

    public void viewRatings(View e)
    {
        Button openViewRatingsButton = findViewById(R.id.viewRatingsButton);
        openViewRatingsButton.setOnClickListener(v ->
        {
            Intent intent = new Intent(RateServiceActivity.this, ViewRatings.class);
            startActivity(intent);

            ViewRatingsFragment viewRatingsFragment = new ViewRatingsFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, viewRatingsFragment)
                    .commit();

        });
    }
}