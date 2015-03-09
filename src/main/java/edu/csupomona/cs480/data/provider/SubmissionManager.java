package edu.csupomona.cs480.data.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.UserScore;

public interface SubmissionManager {

	/**
	 * Get the submission information object based on
	 * the given userId.
	 * <p>
	 * If the user does not exist, simply create
	 * one with a new Submission record.
	 *
	 * @param userId
	 * @return List<Submission> 
	 */
	public ArrayList<Submission> getSubmissions(String userId);

	/**
	 * Update the given submission list and persist it.
	 * <p>
	 * If the submission does not exist in the list, or
	 * if the list does not exist for this user all together, 
	 * the method will create a new record/list. Otherwise, 
	 * it will add the new submission to the user's submission 
	 * list.
	 *
	 * @param Submission object
	 */
	public void updateSubmissionList(Submission submission);

	/**
	 * Delete the given submission from the storage.
	 *
	 * @param submissionId
	 */
	public void deleteSubmission(Submission submission);

	/**
	 * List all the current submissions in the storage.
	 *
	 * @return List of Submissions
	 */
	public ArrayList<Submission> listAllSubmissionsInStorage();
	
	public ArrayList<UserScore> listUser();

	public void setScore(String id, String problemid, int score);
	public Submission getSubmission(ArrayList<Submission> list, String problemId);



	

}
