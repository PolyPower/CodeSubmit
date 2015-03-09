package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.Administrator;
import edu.csupomona.cs480.data.NewReleaseProb;
import edu.csupomona.cs480.data.NewReleaseProbMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link UserManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 *
 */
public class FSreleaseProbManager implements NewReleaseProbManager {

	/**
	 * We persist all the user related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();

	/**
	 * Load the user map from the local file.
	 *
	 * @return
	 */
	private NewReleaseProbMap getNewReleaseProbMap() {
		NewReleaseProbMap newprobMap = null;
		File userFile = ResourceResolver.getNewRelease();
        if (userFile.exists()) {
        	// read the file and convert the JSON content
        	// to the NewReleaseProbMap object
            try {
				newprobMap = JSON.readValue(userFile, NewReleaseProbMap.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
        	newprobMap = new NewReleaseProbMap();
        }
        return newprobMap;
	}

	/**
	 * Save and persist the user map in the local file.
	 *
	 * @param userMap
	 */
	private void persistNewReleaseProbMap(NewReleaseProbMap userMap) {
		try {
			// convert the user object to JSON format
            JSON.writeValue(ResourceResolver.getNewRelease(), userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void updateNewProblem(NewReleaseProb newReleaseProb) {
		NewReleaseProbMap newReleaseProbMap = getNewReleaseProbMap();
		newReleaseProbMap.put(newReleaseProb.getproblemId(), newReleaseProb);
		persistNewReleaseProbMap(newReleaseProbMap);
		
	}
	
	@Override
	public NewReleaseProb getProbId(String probId) {
		NewReleaseProbMap newprobMap = getNewReleaseProbMap();
        return  newprobMap.get(probId);
	}
	


	@Override
	public List<NewReleaseProb> listAllProblems() {
		NewReleaseProbMap newprobMap = getNewReleaseProbMap();
		return new ArrayList<NewReleaseProb>(newprobMap.values());
	}
	
	
}
