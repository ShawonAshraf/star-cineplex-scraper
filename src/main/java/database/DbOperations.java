package database;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import model.Dates;
import model.Location;
import model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class DbOperations {

    private FirebaseDatabase dbInstance;
    private Firestore firestore;
    private final String collection = "showkokhon";
    private final String cineplex = "starcineplex";

    public DbOperations() {
        dbInstance = new Databaseinitializer().initDBConnection();
        firestore = FirestoreClient.getFirestore();
    }

    public void readFromDb() {
        try {
            var snapshot = firestore.collection(collection).document(cineplex).get();
            var doc = snapshot.get();

            if (doc.exists()) {
                System.out.println(doc.getData());
            } else {
                System.out.println("No data in the database!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void writeToDb(ArrayList<Location> data) {
        // clear database
        cleanUpDatabase();

        // iterate and write to db
        data.forEach(location -> {
            var locationName = location.getLocationName();
            var dates = location.getDates();

            dates.forEach(date -> {
                var dateString = date.getDateString();
                var movies = date.getMoviesOnThisDate();

                // movie , showTimes pair
                var movieMap = new HashMap<String, ArrayList<String>>();

                movies.forEach(movie -> {
                    var name = movie.getName();
                    var showTimes = movie.getShowTimes();

                    movieMap.put(name, showTimes);
                });

                try {
                    // location -> date -> movies -> times
                    var ref = firestore.collection(collection)
                            .document(cineplex)
                            .collection(locationName)
                            .document(dateString).set(movieMap);

                    System.out.println("WriteDate:: Update Time => " + ref.get().getUpdateTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        });

    }

    public void cleanUpDatabase() {
        try {
            var future = firestore.collection(collection).document(cineplex)
                    .delete();

            System.out.println("Delete::Update time => " + future.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
