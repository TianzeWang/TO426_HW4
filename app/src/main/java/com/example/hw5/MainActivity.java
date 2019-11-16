package com.example.hw5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonReport, buttonGoToSearch;
    EditText editTextBirdName, editTextZipCode, editTextPersonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonReport = findViewById(R.id.button_Submit);
        buttonGoToSearch = findViewById(R.id.button_goToSearch);
        editTextBirdName = findViewById(R.id.editText_BirdName);
        editTextZipCode = findViewById(R.id.editText_Zip);
        editTextPersonName = findViewById(R.id.editText_Person);

        buttonReport.setOnClickListener(this);
        buttonGoToSearch.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("bird");

        Intent searchIntent = new Intent(this, SearchActivity.class);
        if (v == buttonReport) {
            String createBirdName = editTextBirdName.getText().toString();
            int createZipCode = Integer.parseInt(editTextZipCode.getText().toString());
            String createPersonName = editTextPersonName.getText().toString();
            Bird temp_bird = new Bird(createBirdName, createZipCode, createPersonName);
            myRef.push().setValue(temp_bird);
        } else if (v == buttonGoToSearch) {
            startActivity(searchIntent);
        }
    }
}
