package compiler.classes;

import java.io.*;

public class Writer {

    private static String filePath;

    public Writer() {

    }

    public static void writeToFileAppend(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeHeader(String headerPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(headerPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    writer.write(line + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFooter(String footerPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(footerPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    writer.write(line + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFilePath(String path) {
        filePath = path;
    }
}