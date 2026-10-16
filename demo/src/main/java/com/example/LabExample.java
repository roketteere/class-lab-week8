package com.example;

import com.google.gson.Gson;

public class LabExample {

    // STUDENTS: change this path to where socksTestData.json lives on YOUR computer
    private static String dataFilePath = "C:/Development/claude/datastructures/class-lab-week8/demo/src/main/java/com/example/socksTestData.json";

    public static Boolean processBox(String[] box) {
        // ===== STUDENT CODE GOES HERE =====
        // Stack of unmatched LEFT socks, built from a plain array.
        // Every sock is pushed/popped at most once, so run-time is O(n).
        String[] stack = new String[box.length];
        int top = 0; // number of left socks waiting for a match

        for (int i = 0; i < box.length; i++) {
            String sock = box[i];

            // reject anything that is not a valid sock code: l|r + st|po|ch
            if (sock == null || sock.length() != 3) {
                return false;
            }
            char side = sock.charAt(0);
            String pattern = sock.substring(1);
            if (!pattern.equals("st") && !pattern.equals("po") && !pattern.equals("ch")) {
                return false;
            }

            if (side == 'l') {
                stack[top++] = pattern;            // push: a left sock waits for its right
            } else if (side == 'r') {
                if (top == 0) {
                    return false;                  // right sock with no left sock before it
                }
                String leftPattern = stack[--top]; // pop the most recent unmatched left sock
                if (!leftPattern.equals(pattern)) {
                    return false;                  // left and right patterns do not match
                }
            } else {
                return false;                      // not a left or right sock
            }
        }

        // paired correctly only if no left sock is still waiting for a right sock
        Boolean result = (top == 0);
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
