package tn.esprit.pidev.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/recommend")
public class RecommendationController {

    private Map<String, Object> bestCourseData;

    @PostMapping("/load")
    public ResponseEntity<String> loadPickle(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded file to a temporary location
            File tempFile = File.createTempFile("temp", ".pkl");
            FileOutputStream fileOut = new FileOutputStream(tempFile);
            fileOut.write(file.getBytes());
            fileOut.close();

            // Load the best course data from the uploaded file
            FileInputStream fileIn = new FileInputStream(tempFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            bestCourseData = (Map<String, Object>) objectIn.readObject();
            objectIn.close();
            fileIn.close();

            // Delete the temporary file
            tempFile.delete();

            return new ResponseEntity<>("Pickle file loaded successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while loading the pickle file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recommend")
    public ResponseEntity<Map<String, Object>> recommend() {
        try {
            // Check if the pickle file is loaded
            if (bestCourseData == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Return the information of the best recommended course
            return new ResponseEntity<>(bestCourseData, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
