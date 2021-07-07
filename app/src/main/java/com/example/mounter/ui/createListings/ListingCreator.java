package com.example.mounter.ui.createListings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import com.example.mounter.data.model.RidePostingModel;

import com.example.mounter.R;
import com.example.mounter.ridesearch.RideSearchActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class ListingCreator extends AppCompatActivity {

    private Intent intent;
    private RidePostingModel ridePostingModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_creator);

        TextInputEditText fillTo = findViewById(R.id.fillTo);
        TextInputEditText fillFrom = findViewById(R.id.fillFrom);
        TextInputEditText fillHourOfDeparture = findViewById(R.id.fillHourOfDeparture);
        TextInputEditText fillEstimatedPrice = findViewById(R.id.fillEstimatedPrice);
        TextInputEditText fillDescription = findViewById(R.id.fillDescription);
        ImageButton submit = findViewById(R.id.submit);
        ImageButton back = findViewById(R.id.back);

        submit.setOnClickListener(view -> {
            Log.i("MyApp", "Clicked on SUBMIT");
            CharSequence to = fillTo.getText();
            CharSequence from = fillFrom.getText();
            CharSequence hourOfDeparture = fillHourOfDeparture.getText();
            CharSequence estimatedPrice = fillEstimatedPrice.getText();
            CharSequence description = fillDescription.getText();
            if(to.length() == 0 || from.length() == 0 || fillHourOfDeparture.length() == 0){
                hideKeyboard(this);
                Log.i("MyApp", "Key details are not filled in!");
                fillTo.setText("");
                fillFrom.setText("");
                fillHourOfDeparture.setText("");
                fillEstimatedPrice.setText("");
                fillDescription.setText("");
                Snackbar.make(view, "Please make sure to fill in all key components.",
                        Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                return;
            }
            //Setting all the data into the ridePosting model
            ridePostingModel.setDestinationAddress(to.toString());
            ridePostingModel.setOriginAddress(from.toString());
            ridePostingModel.setDepartureTime(hourOfDeparture.toString());
            ridePostingModel.setEstimatedPrice(estimatedPrice.toString());



            intent = new Intent(getApplicationContext(), RideSearchActivity.class);
            startActivity(intent);
            finish();
        });

        back.setOnClickListener(view -> {
            Log.i("MyApp", "Clicked on BACK");
            intent = new Intent(getApplicationContext(), ChooseListing.class);
            startActivity(intent);
            finish();
        });
    }

    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}