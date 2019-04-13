package controller;

import database.DbOperations;
import model.Location;
import utility.Serializer;

import java.util.ArrayList;

public class DbController {
    private DbOperations dbOps;

    public DbController() {
        dbOps = new DbOperations();
    }

    public void read() {
        dbOps.readFromDb();
    }

    public void write() {
        var data = (ArrayList<Location>) Serializer.readFromFile("output_dir/parsedData.ser");
        dbOps.writeToDb(data);
    }

    public void clear() {
        dbOps.cleanUpDatabase();
    }
}
