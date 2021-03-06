package com.vidyalankar.yogaspace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class YogaActivity extends AppCompatActivity {

    TextView yoga_name, instruction1, instruction2, instruction3, instruction4, instruction5, instruction6;
    ImageView yogaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);
        yoga_name = findViewById(R.id.yoga_name);
        instruction1 = findViewById(R.id.instruction1);
        instruction2 = findViewById(R.id.instruction2);
        instruction3 = findViewById(R.id.instruction3);
        instruction4 = findViewById(R.id.instruction4);
        instruction5 = findViewById(R.id.instruction5);
        instruction6 = findViewById(R.id.instruction6);
        yogaImage = findViewById(R.id.yoga_img);

        Intent intent= getIntent();
        String y= intent.getStringExtra("yoga");
        yoga_name.setText(y);

        FirebaseDatabase.getInstance().getReference()
                .child("Yoga Space")
                .child("Yoga")
                .child(y)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        YogaInstruction yogaInstruction= snapshot.getValue(YogaInstruction.class);
                        String yogaName= yogaInstruction.getName();
                        String instruct1= yogaInstruction.getInstruction1();
                        String instruct2= yogaInstruction.getInstruction2();
                        String instruct3= yogaInstruction.getInstruction3();
                        String instruct4= yogaInstruction.getInstruction4();
                        String instruct5= yogaInstruction.getInstruction5();
                        String instruct6= yogaInstruction.getInstruction6();
                        String yogaImg= yogaInstruction.getImage();

                        yoga_name.setText(yogaName);
                        instruction1.setText(instruct1);
                        instruction2.setText(instruct2);
                        instruction3.setText(instruct3);
                        instruction4.setText(instruct4);
                        instruction5.setText(instruct5);
                        instruction6.setText(instruct6);

                        Glide.with(YogaActivity.this)
                                .load(yogaImg)
                                .placeholder(R.drawable.loading_img)
                                .centerCrop()
                                .into(yogaImage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}