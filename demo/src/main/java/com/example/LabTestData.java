package com.example;

import java.io.FileReader;
import java.io.FileWriter;

public class LabTestData {
    public TestData[] data;

    public String readJSON(String filepath) {
        String result = "";
        try {
            FileReader file = new FileReader(filepath);
            int ch;
            while ((ch = file.read()) != -1) {
                result += (char) ch;
            }
            file.close();
        } catch (Exception e) {
            System.out.printf("ERROR: Unable to read the file %s\n", filepath);
        }
        return result;
    }

    public void writeJSON(String filepath, String json) {
        try {
            FileWriter file = new FileWriter(filepath);
            for (int i = 0; i < json.length(); i++) {
                file.write(json.charAt(i));
            }
            file.close();
        } catch (Exception e) {
            System.out.printf("ERROR: Unable to write the file %s\n", filepath);
        }
    }
}
