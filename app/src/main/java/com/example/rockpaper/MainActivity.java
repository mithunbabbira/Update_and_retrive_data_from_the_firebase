package com.example.rockpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //CONNECTING WITH FIREBASE
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootRef =db.getReference();
    DatabaseReference gameRef = rootRef.child("game");

    //UI elements
    TextView textView;
    Button rock,paper ,scissor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rootRef.child("Users").child("01").child("Email").setValue("babbira.com");
        //rootRef.child("Users").child("01").child("phone").setValue("894634486");


        textView =(TextView)findViewById(R.id.textview);
        rock=(Button)findViewById(R.id.rock);
        paper=(Button)findViewById(R.id.papper);
        scissor=(Button)findViewById(R.id.scissor);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("Rock");
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("paper");
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameRef.setValue("scissor");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String text = dataSnapshot.getValue().toString();
                textView.setText(text);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.i("TAG","Something is wrong");


            }
        });
    }
}
