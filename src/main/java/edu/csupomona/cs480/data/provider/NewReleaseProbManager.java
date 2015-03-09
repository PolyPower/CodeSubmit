package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.NewReleaseProb;

public interface NewReleaseProbManager {

	public void updateNewProblem(NewReleaseProb newReleaseProb);
	/**
	 * List all the problems in the storage.
	 *
	 * @return
	 */
	public List<NewReleaseProb> listAllProblems();
	//public List<User> listFiles();
	//public List<User> listScores();

	public NewReleaseProb getProbId(String probId);

}
