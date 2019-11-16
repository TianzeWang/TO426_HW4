package com.example.hw5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonGoToReport, buttonSearch;
    EditText EditText_ZipcodeSearch;
    TextView Searched_bird_name, Searched_personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        buttonGoToReport = findViewById(R.id.button_goToReport);
        buttonSearch = findViewById(R.id.button_search);
        EditText_ZipcodeSearch = findViewById(R.id.editText_zipsearch);
        Searched_bird_name = findViewById(R.id.textView_Search_birdname);
        Searched_personName = findViewById(R.id.textView_serach_personName);

        buttonGoToReport.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("bird");
        if (v == buttonGoToReport) {
            startActivity(mainIntent);
        }
        else if (v == buttonSearch) {
            int findZipcode = Integer.parseInt(EditText_ZipcodeSearch.getText().toString());
            myRef.orderByChild("ZipCode").equalTo(findZipcode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    if (foundBird == null) {
                        Toast.makeText(SearchActivity.this, "No birds found for given zip!", Toast.LENGTH_SHORT).show();
                    }
                    String foundPersonName = foundBird.personName;
                    String foundBirdName = foundBird.birdName;
                    Searched_bird_name.setText(foundBirdName);
                    Searched_personName.setText(foundPersonName);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
