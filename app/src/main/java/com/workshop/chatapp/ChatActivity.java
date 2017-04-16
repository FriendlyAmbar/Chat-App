package com.workshop.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    Button btnChat;
    EditText msg;
    TextView tv;
    String user, message;

    private FirebaseDatabase database;
    String display = "Messages: \n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference().child("chat");
        btnChat = (Button)findViewById(R.id.buttonStart);
        msg = (EditText)findViewById(R.id.Message);
        tv = (TextView)findViewById(R.id.Result);

        user = getIntent().getExtras().getString("email");
        tv.setText(user);

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = msg.getText().toString();
                ChatMessages chat = new ChatMessages(user,message);
                ref.push().setValue(chat);
            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessages chat = dataSnapshot.getValue(ChatMessages.class);
                display = display+chat.getName()+ " : " + chat.getMessage()+"\n";
                tv.setText(display);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
