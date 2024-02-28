package com.example.vehiclecapacityanalysis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.List;

public class MainVehicle extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private TextView titleText;
    private TextView subtitleText;
    private Button computeButton;
    private EditText weightEditText;
    private boolean isStreetSelected = true; // Set "Street" as the initial mode
    private VehicleCalculator vehicleCalculator;
    private PopupWindow popupWindow;

    public MainVehicle() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_main);

        // Initialize UI elements
        mainLayout = findViewById(R.id.vehicle_layout);
        titleText = findViewById(R.id.title_text);
        subtitleText = findViewById(R.id.subtitle_text);
        computeButton = findViewById(R.id.computeButton);
        weightEditText = findViewById(R.id.weightEditText);

        // Initialize custom toggle and set listeners
        CustomToggle customToggle = findViewById(R.id.custom_toggle);
        customToggle.setOnToggleListener(isOn -> {
            isStreetSelected = !isOn; // Invert the selection logic
            vehicleCalculator.setSelectedMode(isStreetSelected ? "Street" : "UAM"); // Invert the mode selection
            updateUIBasedOnTheme();
        });

        customToggle.setOnClickListener(v -> {
            isStreetSelected = !isStreetSelected; // Invert the selection logic
            vehicleCalculator.setSelectedMode(isStreetSelected ? "Street" : "UAM"); // Invert the mode selection
            updateUIBasedOnTheme();
        });

        // Initialize the VehicleCalculator
        vehicleCalculator = new VehicleCalculator();
        setupPopupWindow();

        // Set a click listener for the computeButton
        computeButton.setOnClickListener(v -> {
            vehicleCalculator.setSelectedMode(isStreetSelected ? "Street" : "UAM");
            computeBestVehicles();
        });

        // Update UI based on the selected theme
        updateUIBasedOnTheme();
    }

    private void setupPopupWindow() {
        // Inflate the best vehicle layout for the popup
        ConstraintLayout bestVehicleLayout = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.best_vehicle_layout, null);
        ImageView closeButton = bestVehicleLayout.findViewById(R.id.closeButton);
        TextView suggestedTextView = bestVehicleLayout.findViewById(R.id.suggestedTextView);

        // Initialize the popup window
        popupWindow = new PopupWindow(bestVehicleLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        popupWindow.setElevation(8);

        // Set a click listener for the close button in the popup
        closeButton.setOnClickListener(v -> popupWindow.dismiss());
    }

    private void updateUIBasedOnTheme() {
        setThemeColors();
        updateLogoVisibility(); // Call the updateLogoVisibility method here
    }


    private void updateLogoVisibility() {
        ImageView themeIcon1 = findViewById(R.id.theme_icon1);
        ImageView themeIcon2 = findViewById(R.id.theme_icon2);

        if (isStreetSelected) {
            themeIcon1.setVisibility(View.INVISIBLE);
            themeIcon2.setVisibility(View.VISIBLE);
        } else {
            themeIcon1.setVisibility(View.VISIBLE);
            themeIcon2.setVisibility(View.INVISIBLE);
        }
    }


    private void setThemeColors() {
        int backgroundColor = isStreetSelected ? R.color.light_background : R.color.dark_background;
        int textColor = isStreetSelected ? R.color.text_color_dark : R.color.text_color_light;

        mainLayout.setBackgroundColor(ContextCompat.getColor(this, backgroundColor));
        titleText.setTextColor(ContextCompat.getColor(this, textColor));
        subtitleText.setTextColor(ContextCompat.getColor(this, textColor));

        int buttonBackground = isStreetSelected ? R.drawable.light_compute_button_background : R.drawable.dark_compute_button_background;
        int buttonText = isStreetSelected ? R.color.text_color_dark : R.color.text_color_light;

        computeButton.setBackground(ContextCompat.getDrawable(this, buttonBackground));
        computeButton.setTextColor(ContextCompat.getColor(this, buttonText));

        int editTextColor = isStreetSelected ? R.color.edittext_color_street : R.color.edittext_color_uam;
        int editHintColor = isStreetSelected ? R.color.edittext_color_street : R.color.edittext_color_uam;

        weightEditText.setTextColor(ContextCompat.getColor(this, editTextColor));
        weightEditText.setHintTextColor(ContextCompat.getColor(this, editHintColor));
    }

    private void computeBestVehicles() {
        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            vehicleCalculator.setWeight(weight);
            List<Vehicle> bestVehicles = vehicleCalculator.computeBestVehicles();
            showBestVehicles(bestVehicles);
        } catch (NumberFormatException e) {
            // Handle invalid input
            Toast.makeText(MainVehicle.this, "Invalid weight input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showBestVehicles(List<Vehicle> bestVehicles) {
        StringBuilder bestVehiclesText = new StringBuilder();
        bestVehiclesText.append("Best Vehicles:\n");

        for (Vehicle vehicle : bestVehicles) {
            bestVehiclesText.append("- ").append(vehicle.getName()).append("\n");
        }

        TextView suggestedTextView = popupWindow.getContentView().findViewById(R.id.suggestedTextView);
        suggestedTextView.setText(bestVehiclesText.toString());

        // Update and show the popup window
        popupWindow.update();
        if (!popupWindow.isShowing()) {
            popupWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 0, 0);
        }
    }

}
