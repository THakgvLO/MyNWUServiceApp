package com.example.myapplication;

import android.os.*;
import android.view.View;
import android.net.Uri;
import android.widget.Button;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void homeButton(View h)
    {
        int button3 = R.id.NWU;
        Button openWebsiteButton = findViewById(button3);
        openWebsiteButton.setOnClickListener(v -> {
            // Create an Intent to open the website
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nwu.ac.za/"));
            startActivity(browserIntent);
        });
    }

    public void rateService(View r)
    {

        Button openRateServiceButton = findViewById(R.id.rateButton);
        openRateServiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RateServiceActivity.class);
            startActivity(intent);

        RateServiceFragment rateServiceFragment = new RateServiceFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, rateServiceFragment)
                    .commit();

        });
    }

    public void viewRatings(View e)
    {
        Button openViewRatingsButton = findViewById(R.id.viewRatingsButton);
        openViewRatingsButton.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, ViewRatings.class);
            startActivity(intent);

            ViewRatingsFragment viewRatingsFragment = new ViewRatingsFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, viewRatingsFragment)
                    .commit();

        });
    }

    public void LocateServices(View l)
    {
        Button showImageButton = findViewById(R.id.locateServiceButton); // Assuming you have a button in your activity layout
        showImageButton.setOnClickListener(v -> {
            String pdfPath = "path/to/your/pdf/file.pdf"; // Replace with your actual PDF path

            // Create an instance of ImageFragment using newInstance method
            LocateService locateFragment = LocateService.newInstance(pdfPath);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, locateFragment) // Use your actual container ID here
                    .addToBackStack(null) // Optional: add this transaction to back stack
                    .commit();
        });
    }
}
