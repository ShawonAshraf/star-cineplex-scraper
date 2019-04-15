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

    public void write(ArrayList<Location> data) {
        dbOps.writeToDb(data);
    }

    public void clear() {
        dbOps.cleanUpDatabase();
    }
}
