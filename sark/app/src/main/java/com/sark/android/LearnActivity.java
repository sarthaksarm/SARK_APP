package com.sark.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sark.android.Play.HomeScreen;
import com.sark.android.TeamDevAchi.Blog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sark.android.TeamDevAchi.TeamActivity;
import com.squareup.picasso.Picasso;

public class LearnActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    KenBurnsView kenBurnsView;
    private Boolean moving=true;
    String name1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent=new Intent(LearnActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_dashboard:
                    Intent intent2=new Intent(LearnActivity.this,HomeScreen.class);
                    startActivity(intent2);
                    break;
                case R.id.navigation_notifications:
                    Intent intent3=new Intent(LearnActivity.this,Notification.class);
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        kenBurnsView=(KenBurnsView)findViewById(R.id.image);
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(10000, ACCELERATE_DECELERATE);
        kenBurnsView.setTransitionGenerator(generator);

        kenBurnsView.setTransitionListener(onTransittionListener());

        kenBurnsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moving){
                    kenBurnsView.pause();
                    moving=false;
                }
                else{
                    kenBurnsView.resume();;
                    moving=true;
                }
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Learn");
        mDatabase.keepSynced(true);

        recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);





    }
    private KenBurnsView.TransitionListener onTransittionListener() {
        return new KenBurnsView.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {

                // Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                // Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, LearnActivity.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, LearnActivity.BlogViewHolder>
                (Blog.class, R.layout.cardview, LearnActivity.BlogViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(LearnActivity.BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }
        };
        firebaseRecyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mview;

        public BlogViewHolder(final View itemView) {
            super(itemView);
            mview = itemView;
//
//            DatabaseReference reference= FirebaseDatabase.getInstance().getReference(getAdapterPosition()+"");
//            Snackbar snackbar = Snackbar.make(itemView,"Opening Link ...",Snackbar.LENGTH_LONG);
//            snackbar.show();
//
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for(DataSnapshot x:dataSnapshot.getChildren()) {
//                        name1 = dataSnapshot.child("link").getValue().toString();
//                        //Toast.makeText(Main3Activity.this, ""+name1, Toast.LENGTH_SHORT).show();
//
//                    }
//                    Intent i=new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(name1));
//                    startActivity(i);
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(), TeamActivity.class));
//                }
//            });

        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mview.findViewById(R.id.item_title);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mview.findViewById(R.id.item_desc);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_Image = (ImageView)mview.findViewById(R.id.item_image);
            Picasso.get().load(image).placeholder(R.drawable.back).into(post_Image);
        }
    }
}
