package tn.esprit.pidev;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class PidevApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PidevApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       // generateCSV();
       //executePythonScripting();
    }

    public void executePythonScripting() {
        try {
            String pythonScriptPath = "C:\\Users\\ADMIN\\OneDrive - ESPRIT\\Bureau\\quiz.ipynb";
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python script executed successfully");
            } else {
                System.out.println("Error executing Python script, exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void executePythonScript() {
        try {
            String pythonScriptPath = "C:\\Users\\Aziz\\Desktop\\scriiiiiiiiiiiiiipt.py";
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Python script executed successfully");
            } else {
                System.out.println("Error executing Python script, exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void generateCSV() {
        String csvFile = "cours_data.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("nomCours,nomProfesseur,dateInscription,descriptionCours,prix,photo,ressourceList,likes,dislikes,likedBy,dislikedBy\n");

            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                String nomCours = "Course " + (i + 1);
                String nomProfesseur = "Professor " + (i + 1);
                String dateInscription = "2024-05-01"; // Assuming all dates are the same for simplicity
                String descriptionCours = "Brief description of course " + (i + 1);
                double prix = 10 + (90 * random.nextDouble());
                String photo = "https://example.com/photo" + (i + 1) + ".jpg";

                List<String> ressourceList = new ArrayList<>();
                for (int j = 0; j < random.nextInt(5) + 1; j++) {
                    ressourceList.add(UUID.randomUUID().toString());
                }

                int likes = random.nextInt(101);
                int dislikes = random.nextInt(51);

                List<String> likedBy = new ArrayList<>();
                for (int j = 0; j < random.nextInt(21); j++) {
                    likedBy.add(UUID.randomUUID().toString());
                }

                List<String> dislikedBy = new ArrayList<>();
                for (int j = 0; j < random.nextInt(11); j++) {
                    dislikedBy.add(UUID.randomUUID().toString());
                }

                writer.append(String.join(",", nomCours, nomProfesseur, dateInscription, descriptionCours,
                        String.valueOf(prix), photo, String.join(";", ressourceList),
                        String.valueOf(likes), String.valueOf(dislikes),
                        String.join(";", likedBy), String.join(";", dislikedBy)));
                writer.append("\n");
            }

            System.out.println("CSV file generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
