import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScan {
    public static void main(String[] args) {
        File selectedFile;

        System.out.println("Starting FileScan...");

        if (args.length > 0) {
            System.out.println("Argument received: " + args[0]);
            selectedFile = new File(args[0]);
            if (!selectedFile.exists()) {
                System.out.println("The file was not found: " + args[0]);
                return;
            }
        }
        else {
            System.out.println("No arguments received. Opening JFileChooser...");
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            JFileChooser filePicker = new JFileChooser();
            int result = filePicker.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = filePicker.getSelectedFile();
                System.out.println("File selected: " + selectedFile.getName());
            } else {
                System.out.println("No file selected. Exiting.");
                return;
            }
        }

        if (selectedFile == null || !selectedFile.exists()) {
            System.out.println("File not valid.");
            return;
        }

        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (Scanner fileScanner = new Scanner(selectedFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCount++;
                String[] words = line.trim().split("\\s+");
                wordCount += (line.trim().isEmpty()) ? 0 : words.length;
                charCount += line.length();
            }

            System.out.println("\n--- Summary Report ---");
            System.out.println("File Name: " + selectedFile.getName());
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + charCount);

        } catch (FileNotFoundException e) {
            System.out.println("The file has not been found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
        }
    }
}
