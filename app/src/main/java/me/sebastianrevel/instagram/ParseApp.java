package me.sebastianrevel.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.sebastianrevel.instagram.models.Post;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("sebrev")
                .clientKey("instaparse2")
                .server("http://sebastianrevel-fbu-instagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
