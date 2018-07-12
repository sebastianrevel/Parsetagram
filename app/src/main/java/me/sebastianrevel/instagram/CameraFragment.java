package me.sebastianrevel.instagram;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

import me.sebastianrevel.instagram.models.Post;

public class CameraFragment extends Fragment {
    private static final String imagePath = "/storage/emulated/0/DCIM/Camera/IMG_20180712_141743.jpg";


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    interface CallBack {
        void onPostUploaded();
    }

    private CallBack callback;

    public ImageView ivPhoto;
    private Button createButton;
    private EditText descriptionInput;
    public String photoFileName = "photo.jpg";
    public final String APP_TAG = "MyCustomApp";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof CallBack) {
            callback = (CallBack) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_camera, parent, false);
        ivPhoto = view.findViewById(R.id.ivPhoto);
        createButton = view.findViewById(R.id.create_btn);
        descriptionInput = view.findViewById(R.id.description_et);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();

                final File file = getPhotoFileUri(photoFileName);
                final ParseFile parseFile = new ParseFile(file);

                createPost(description, parseFile, user);

//                parseFile.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            Log.d("CreateFragment", "Successfully uploaded image" );
//                            createPost(description, parseFile, user);
//                        } else {
//                            e.printStackTrace();
//                        }
//                    }
//                });
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
                    callback.onPostUploaded();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(APP_TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        return file;
    }
}