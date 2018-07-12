package me.sebastianrevel.instagram;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

import me.sebastianrevel.instagram.models.Post;

public class HomeActivity extends AppCompatActivity {
    private static final String imagePath = "/sdcard/DCIM/Camera/IMG_20180710_135954.jpg";
    private Button createButton;
    private Button refreshButton;
    private EditText descriptionInput;
    private SwipeRefreshLayout swipeContainer;


    private ArrayList<Post> posts = new ArrayList<>();
    // recycler view
    private RecyclerView rvPosts;
    // movie adapter
    private InstaAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.

                adapter.clear();
                loadTopPosts();
                swipeContainer.setRefreshing(false);
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.nav_logo_whiteout);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 255, 255)));

        adapter = new InstaAdapter(posts);
        rvPosts = (RecyclerView) findViewById(R.id.rvPost);
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false));
        loadTopPosts();

//        descriptionInput = findViewById(R.id.description_et);
//        refreshButton = findViewById(R.id.refresh_btn);
//        createButton = findViewById(R.id.create_btn);
//
//        createButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String description = descriptionInput.getText().toString();
//                final ParseUser user = ParseUser.getCurrentUser();
//
//                final File file = new File(imagePath);
//                final ParseFile parseFile = new ParseFile(file);
//
//                parseFile.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            Log.d("HomeActivity", "Successfully uploaded image" );
//                            createPost(description, parseFile, user);
//                        } else {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        });
//        refreshButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadTopPosts();
//            }
//        });


    }


//    private void createPost(String description, ParseFile imageFile, ParseUser user){
//        final Post newPost = new Post();
//        newPost.setDescription(description);
//        newPost.setImage(imageFile);
//        newPost.setUser(user);
//
//        newPost.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.d("HomeActivity", "Create post success!");
//                } else {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }


    private void loadTopPosts() {
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();
        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    if (objects == null) {
                        Log.d("HomeActivity", "Objects is null!");
                    } else {
                        Log.d("HomeActivity", "Adding posts: " + objects.size());
                    }

                    posts.addAll(objects);
                    adapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

}

