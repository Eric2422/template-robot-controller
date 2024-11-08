
package org.firstinspires.ftc.teamcode;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import android.os.Environment;

public class FileManager {
    /*
     * The directory that saves the current season's storage files.
     * The current String is a placeholder,
     * and is intended to be replaced with the name of the current season's repository"
     */
    private static final Path seasonDirectory = Paths.get(
            Environment.getExternalStorageDirectory().getPath() + "2024-2025IntoTheDeep");

    public static final Path getSeasonDirectory() {
        return seasonDirectory;
    }

    /**
     * Writes a String to a text file inside this season's directory.
     * Creates the file if it does not already exist
     *
     * @param fileName    The name of the file being written to.
     * @param inputString The String that is written to the file
     * @return Whether the String was successfully written to the file.
     * @throws FileAlreadyExistsException Failed to create the output file due to it
     *                                    already existing.
     * @throws IOException                Failed to create or write to the output
     *                                    file.
     */
    public static boolean writeToFile(Path fileName, String inputString)
            throws FileAlreadyExistsException, IOException {
        // If this season's directory does not exist, create it.
        if (!Files.exists(seasonDirectory)) {
            Files.createDirectory(seasonDirectory);
        }

        Path writeFile = seasonDirectory.resolve(fileName);
        try {
            // Create the file if it does not exist
            Files.createFile(writeFile);

        } catch (FileAlreadyExistsException err) {
            // The file already existing is not a problem.

        } catch (IOException err) {
            return false;
        }

        ArrayList<String> inputLines = new ArrayList<>();
        inputLines.add(inputString);
        Files.write(writeFile, inputLines);

        return true;
    }

    /**
     * Read a text file inside this season's directory.
     *
     * @param filePath The file path of the text file to read.
     * @return The String contained within the text file.
     * If an error occurs, null is returned.
     * @throws IOException Failed to read the input file
     */
    public static String readFile(Path filePath) throws IOException {
        Path readFile = seasonDirectory.resolve(filePath);

        // Read
        if (Files.exists(readFile)) {
            return String.join("\n", Files.readAllLines(readFile));
        }

        return null;
    }
}