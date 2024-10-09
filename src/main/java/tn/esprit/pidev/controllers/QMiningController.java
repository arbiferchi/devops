package tn.esprit.pidev.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/quizdm")
public class QMiningController {

    @GetMapping("/dm")
    public String retrieveAccount() {
        return executePythonScript();
    }

    public String executePythonScript() {
        StringBuilder output = new StringBuilder();

        try {
            String pythonScriptPath = "C:\\Users\\ADMIN\\OneDrive - ESPRIT\\Bureau\\quiz.py";
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
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

        // Create a JSON object
        ObjectMapper mapper = new ObjectMapper();
        String jsonOutput = "";
        try {
            jsonOutput = mapper.writeValueAsString(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonOutput;
    }
}
