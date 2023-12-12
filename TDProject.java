package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TDProject {
    private SortedArrayDictionary <BusinessName, String> directory;
    private SortedArrayDictionary <BusinessName, String> reverseDirectory;

    public TDProject() {
        this.directory = new SortedArrayDictionary<>();
        
    }

    public boolean readFile(String fileName){
        File datafile = new File (fileName);
        String line;
        BusinessName name; 
        String phoneNumber;
        try {
        	Scanner read = new Scanner (datafile);
        	while (read.hasNext()) {
        		line = read.nextLine().trim();
        		int lastBlank = line.lastIndexOf(' ');
        		name = new BusinessName(line.substring(0, lastBlank).trim());
        		phoneNumber = line.substring(lastBlank+1);
        		directory.add(name, phoneNumber);
        		if (line.isEmpty())
        			continue;   	
        	}
        }
        catch (FileNotFoundException ex) {
        	System.out.println ("The file " + fileName + " is not found");
        	return false;
        }
        return true;
        
	}

    public void add(String name, String phoneNumber) {
        directory.add(new BusinessName(name), phoneNumber);
    }

    public String getPhoneNumber(String name) {
        return directory.getValue(new BusinessName(name));
    }

    public void remove(String name) {
        directory.remove(new BusinessName(name));
    }

    public String getFullNameAndPhone(String s) {
        return directory.getValue(new BusinessName(s));
    }
}
