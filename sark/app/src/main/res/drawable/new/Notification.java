package com.example.sarthakmishra.sark;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Notification extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        listView=findViewById(R.id.listView);

       Query query=firebaseDatabase.getInstance().getReference().child("User").orderByChild("key");

        FirebaseListOptions<User> options=new FirebaseListOptions.Builder<User>()
                .setLayout(R.layout.user)
                .setQuery(query,User.class)
                .build();

        adapter=new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                // String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                TextView title=v.findViewById(R.id.titletxt);
                TextView desc=v.findViewById(R.id.descriptiontxt);
                TextView time=v.findViewById(R.id.eventtime);
                 TextView datenotif=v.findViewById(R.id.dateofnotifictxt);

                User std=(User)model;
                //if(std.getDate().compareTo(date1) > 0){
                //  pos[j++]=position;
                title.setText(""+std.getTitle().toString());
                desc.setText(""+std.getDescrip().toString());
                time.setText("Event time: "+std.getEventtime().toString());
                datenotif.setText(""+std.getDateofnotific().toString());
            }

        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User s=(User)parent.getItemAtPosition(position);
//                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(s.getRegislink()));
//                startActivity(browserIntent);
            }
        });
    }

    private int getItemCount() {
        return listView.getCount();
    }

    @Override
    public void onBackPressed() {
      Intent i=new Intent(Notification.this,MainActivity.class);
      startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}


