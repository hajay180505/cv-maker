package cvMaker;

import cvMaker.database.Database;
import cvMaker.utils.ResumeTemplate;
import cvMaker.utils.TwoPagerOfficialResume;

import java.io.IOException;
//import schema.ResumeDetail;

public class Main {
    public final static boolean IS_DEV = true;

    public static void main(String[] args) {
//        ResumeDetail resumeDetail;

        Database db = new Database(); // default connection credentials

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