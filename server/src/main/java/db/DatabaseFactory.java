package db;

/**
 * Created by Juriy on 3/21/2015.
 */
public class DatabaseFactory {

    private static Database database = new Database();

    public static Database getDatabase(){
        return database;
    }
}
