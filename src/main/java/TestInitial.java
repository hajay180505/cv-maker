import database.Database;
import config.CONNECTION_CREDENTIALS;
import utils.ResumeTemplate;
import utils.TwoPagerOfficialResume;

import java.io.IOException;
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

        ResumeTemplate rt = new TwoPagerOfficialResume();
        try {
            rt.getTemplate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* TODO: create frontend GUI */
        /*
            Login user using mongodb work  {backend also yet to be done}
            List all available templates {file paths will be provided}
            Possibly give a colorpicker {cosmetic work}
            Click generate -> Generate pdf -> View it {generation to be done}

         */
    }
}