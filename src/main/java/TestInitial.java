import database.Database;
//import schema.ResumeDetail;

public class TestInitial {

    public static void main(String[] args) {
//        ResumeDetail resumeDetail;

        Database db = new Database(
                CONNECTION_CREDENTIALS.URI,
                CONNECTION_CREDENTIALS.DATABASE_NAME,
                CONNECTION_CREDENTIALS.COLLECTION_NAME
                );

        if (db.ping()){
            System.out.println("PONG");
        }
        else {
            System.out.println("ERROR");
        }
    }
}