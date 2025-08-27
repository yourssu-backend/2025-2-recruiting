package com.yourssu.application.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TestIOUtils {
    public static String captureOutputWithInput(String input, Runnable action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;
        
        try {
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            action.run();
            return outputStream.toString().trim();
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }

    public static String captureOutputWithMultipleInputs(String[] inputs, Runnable action) {
        String combinedInput = String.join("\n", inputs);
        return captureOutputWithInput(combinedInput, action);
    }

    public static String captureOutputWithInputLines(Runnable action, String... lines) {
        String combinedInput = String.join("\n", lines);
        return captureOutputWithInput(combinedInput, action);
    }

    public static TestIOResult captureIOInteraction(String input, Runnable action) {
        String output = captureOutputWithInput(input, action);
        return new TestIOResult(input, output);
    }
    
    public static class TestIOResult {
        public final String input;
        public final String output;
        public final String[] inputLines;
        public final String[] outputLines;
        
        public TestIOResult(String input, String output) {
            this.input = input;
            this.output = output;
            this.inputLines = input.split("\n");
            this.outputLines = output.split("\n");
        }
        
        public boolean outputContains(String text) {
            return output.contains(text);
        }
        
        public boolean outputLineEquals(int index, String expected) {
            return index < outputLines.length && outputLines[index].equals(expected);
        }
    }
}
