package utils;

import com.itextpdf.kernel.colors.DeviceRgb;
import schema.ResumeDetail;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ResumeTemplate {
    String BASE_FILE_PATH = "src/main/resources/";
    String getTemplate() throws IOException;
    String generateResume(ResumeDetail resumeDetail, DeviceRgb accentColor, String username, String filename) throws IOException;
}
