package me.sebastianrevel.instagram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

import me.sebastianrevel.instagram.models.Post;

public class CreateFragment extends Fragment {
    private static final String imagePath = "/storage/emulated/0/DCIM/Camera/IMG_20180712_141743.jpg";

    private Button createButton;
    private Button refreshButton;
    private EditText descriptionInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.activity_home, parent, false);

        refreshButton = view.findViewById(R.id.refresh_btn);
        createButton = view.findViewById(R.id.create_btn);
        descriptionInput = view.findViewById(R.id.description_et);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();

                final File file = new File(imagePath);
                final ParseFile parseFile = new ParseFile(file);

                parseFile.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("CreateFragment", "Successfully uploaded image" );
                            createPost(description, parseFile, user);
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        return view;
    }

    private void createPost(String description, ParseFile imageFile, ParseUser user){
        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("CreateFragment", "Create post success!");
                } else {
                    e.printStackTrace();
                }
            }
        });
    }
}

