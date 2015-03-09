package edu.csupomona.cs480.util;

import java.io.File;

/**
 * This is an utility class to help file locating.
 */
public class ResourceResolver {
	
	/** The base folder to store all the data used by this project. */
	// "user.home" means computer user's physical home directory
    private static final String BASE_DIR = System.getProperty("user.home") + "/cs480"; 
    
    /**
     * Get the file used to store the submission object JSON
     *
     * @param submissionId
     * @return
     */
    public static File getSubmissionFile() {
        File file = new File(BASE_DIR + "/" + "submission-map.json");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }
	
  
    /**
     * Get the file used to store the user object JSON
     *
     * @param userId
     * @return
     */
    public static File getDatabaseFile() {
        File file = new File(BASE_DIR + "/" + "DataBase.json");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }
    
    public static File getNewRelease() {
        File file = new File(BASE_DIR + "/" + "NewRelease-map.json");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }
    
}
