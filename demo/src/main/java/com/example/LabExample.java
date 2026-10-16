package com.example;

import com.google.gson.Gson;

public class LabExample {

    // STUDENTS: change this path to where socksTestData.json lives on YOUR computer
    private static String dataFilePath = "C:/Development/claude/datastructures/class-lab-week8/demo/src/main/java/com/example/socksTestData.json";

    public static Boolean processBox(String[] box) {
        // ===== STUDENT CODE GOES HERE =====
        Boolean result = true;
        // ===== END STUDENT CODE =====
        return result;
    }

    public static void main(String[] args) {
        LabTestData td = new LabTestData();
        String jsonStr = td.readJSON(dataFilePath);
        Gson gson = new Gson();
        LabTestData testData = gson.fromJson(jsonStr, LabTestData.class);

        for (int i = 0; i < testData.data.length; i++) {
            String[] box = testData.data[i].input;
            Boolean answer = processBox(box);
            Boolean correctAnswer = testData.data[i].output;

            if (answer == correctAnswer) {
                System.out.printf("pass test %d : ", i);
                if (answer) {
                    System.out.printf("box %d has socks that pair correctly.\n", i);
                } else {
                    System.out.printf("box %d has socks that do not pair correctly.\n", i);
                }
            } else {
                System.out.printf("fail test %d : ", i);
                System.out.printf("box %d answered as %s. But, the correct answer is %s\n", i, answer, correctAnswer);
            }
        }
    }
}
