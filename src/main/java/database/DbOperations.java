package database;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import model.Dates;
import model.Location;
import model.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class DbOperations {

    private FirebaseDatabase dbInstance;
    private Firestore firestore;
    private final String collection = "showkokhon";
    private final String cineplex = "starcineplex";

    private final String bcity = "Bashundhara Shopping Mall, Panthapath";
    private final String shimanto = "Shimanto Shambhar, Dhanmondi 2";

    public DbOperations() {
        dbInstance = new Databaseinitializer().initDBConnection();
        firestore = FirestoreClient.getFirestore();
    }

    private void readDocs(CollectionReference ref, String location) {
        try {
            System.out.println("Data for :: " + location);
            var future = ref.get();
            var docList = future.get().getDocuments();

            docList.forEach(doc -> {
                System.out.println(doc.getId());
                System.out.println(doc.getData());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromDb() {
        try {
            var refBCity = firestore.collection(collection)
                    .document(cineplex)
                    .collection(bcity);

            var refShimanto = firestore.collection(collection)
                    .document(cineplex)
                    .collection(shimanto);


            readDocs(refBCity, bcity);
            readDocs(refShimanto, shimanto);

        } catch (Exception e) {
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

    private void deleteDocs(CollectionReference ref) {
        try {
            var future = ref.get();
            var docs = future.get().getDocuments();

            docs.forEach(document -> {
                document.getReference().delete();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanUpDatabase() {
        try {
            var refBCity = firestore.collection(collection)
                    .document(cineplex)
                    .collection(bcity);

            var refShimanto = firestore.collection(collection)
                    .document(cineplex)
                    .collection(shimanto);

            // delete
            deleteDocs(refBCity);
            deleteDocs(refShimanto);

            System.out.println("Delete::Update time => " + new Date().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
