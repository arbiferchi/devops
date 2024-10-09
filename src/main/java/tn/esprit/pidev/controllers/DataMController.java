package tn.esprit.pidev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dm")
public class DataMController {

    @GetMapping("/dm")
    public String retrieveAccount() {
        return executePythonScript();
    }

    public String executePythonScript() {
        StringBuilder output = new StringBuilder();

        try {
            String pythonScriptPath = "C:\\Users\\Aziz\\Desktop\\scriiiiiiiiiiiiiipt.py";
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

        return "{\"output\": \"" + output.toString().replace("\n", "\\n") + "\"}";
    }
}
