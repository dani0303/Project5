package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class TDProject {
    private Map<BusinessName, String> directory;
    private Map<String, BusinessName> reverseDirectory;

    public TDProject() {
        this.directory = new TreeMap<>();
        this.reverseDirectory = new TreeMap<>();
    }

    public void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    BusinessName businessName = new BusinessName(parts[0].trim(), parts[1].trim());
                    directory.put(businessName, parts[2].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
