package database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;

public class Databaseinitializer {
    // init db connection and then return the init db instance
    public FirebaseDatabase initDBConnection() {
        try {
            InputStream configFile = this.getClass()
                    .getResourceAsStream("../db_secret/db.json");

            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(configFile))
                    .setDatabaseUrl("https://showkokhon.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);

            return FirebaseDatabase.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
