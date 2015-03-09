package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.SubmissionMap;
import edu.csupomona.cs480.util.ResourceResolver;
import edu.csupomona.cs480.data.UserScore;

/**
 * The implementation of {@link SubmissionManager} interface using file system.
 * <p>
 * This class demonstrates how you can use the file system as a database to
 * store your data.
 *
 */
public class FSSubmissionManager implements SubmissionManager {

	/**
	 * We persist all the submission related objects as JSON.
	 * <p>
	 * For more information about JSON and ObjectMapper, please see:
	 * http://www.journaldev
	 * .com/2324/jackson-json-processing-api-in-java-example-tutorial
	 *
	 * or Google tons of tutorials
	 *
	 */
	private static final ObjectMapper JSON = new ObjectMapper();

	/**
	 * Load the submission map from the local file.
	 *
	 * @return
	 */
	private SubmissionMap getSubmissionMap() {
		SubmissionMap submissionMap = null;
		File submissionFile = ResourceResolver.getSubmissionFile();
		if (submissionFile.exists()) {
			// read the file and convert the JSON content
			// to the SubmissionMap object
			try {
				submissionMap = JSON.readValue(submissionFile,
						SubmissionMap.class);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			submissionMap = new SubmissionMap();
		}
		return submissionMap;
	}

	/**
	 * Save and persist the submission map in the local file.
	 *
	 * @param submissionMap
	 */
	private void persistSubmissionMap(SubmissionMap submissionMap) {

		try {
			// convert the submission object to JSON format
			JSON.writeValue(ResourceResolver.getSubmissionFile(), submissionMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves all submissions for one particular user.
	 * 
	 * @return A list of the user's submissions
	 */
	@Override
	public ArrayList<Submission> getSubmissions(String userId) {
		SubmissionMap submissionMap = getSubmissionMap();
		return submissionMap.get(userId);
	}

	/**
	 * Update the given submission list and persist it.
	 * <p>
	 * If the submission does not exist in the list, or if the list does not
	 * exist for this user all together, the method will create a new
	 * record/list. Otherwise, it will add the new submission to the user's
	 * submission list.
	 *
	 * @param Submission
	 *            object
	 */
	@Override
	public void updateSubmissionList(Submission submission) {
		// get all submissions in the file system.
		SubmissionMap submissionMap = getSubmissionMap();

		// for the user that just made a submission, retrieve his
		// or her submission list.
		ArrayList<Submission> submissionList = submissionMap.get(submission
				.getUserId());
		// if the user has a previous record, update his/her submission list.
		//if (!chekcingResubmission(submission, submissionList)) {
			if (submissionList != null) {

				// add the submission to the list
				submissionList.add(submission);

				// put the list into the submission map
				submissionMap.put(submission.getUserId(), submissionList);
			}

			// else, create a new record.
			else {
				submissionList = new ArrayList<Submission>();
				submissionList.add(submission);
				submissionMap.put(submission.getUserId(), submissionList);
			}

			persistSubmissionMap(submissionMap);
//		}
//		else{
//			System.out.println("no resubmission");
//		}
	}

//	private boolean chekcingResubmission(Submission submission,
//			ArrayList<Submission> list) {
//		boolean isExist = false;
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).getProblemId() == submission.getProblemId()) {
//				isExist = true;
//			}
//		}
//		return isExist;
//	}

	@Override
	public void deleteSubmission(Submission submission) {
		SubmissionMap submissionMap = getSubmissionMap();
		ArrayList<Submission> submissionList = submissionMap.get(submission
				.getUserId());

		// if the user has a previous record, update his/her submission list.
		if (submissionList != null) {

			// remove the submission from the list
			submissionList.remove(submission.getWeekNo() - 1);

			// put the list into the submission map
			submissionMap.put(submission.getUserId(), submissionList);
			persistSubmissionMap(submissionMap);
		}

		// else, there is nothing to remove
		else {
			System.out.println("This user does not exist");
		}
	}

	/**
	 * List all the current submissions in the storage.
	 *
	 * @return List of Submissions
	 */
	@Override
	public ArrayList<Submission> listAllSubmissionsInStorage() {
		SubmissionMap submissionMap = getSubmissionMap();
		ArrayList<Submission> allSubmissionsInStorage = new ArrayList<Submission>();
		for (String key : submissionMap.keySet()) {
			allSubmissionsInStorage.addAll(submissionMap.get(key));
		}
		return allSubmissionsInStorage;
	}

	public ArrayList<UserScore> listUser() {
		SubmissionMap sbMap = getSubmissionMap();
		ArrayList<UserScore> userList = new ArrayList<UserScore>();
		ArrayList<Submission> sb = new ArrayList<Submission>();

		int totalScore = 0;
		UserScore us = new UserScore();
		for (String key : sbMap.keySet()) {
			sb = getSubmissions(key);
			for (int i = 0; i < sb.size(); i++) {
				totalScore += sb.get(i).getScore();
			}
			us.setTotalScore(totalScore);
			us.setId(key);
			userList.add(us);
			totalScore = 0;
			us = new UserScore();
		}
		return userList;

	}

	public void setScore(String id, String problemid, int score) {

		SubmissionMap submissionMap = getSubmissionMap();
		ArrayList<Submission> list = submissionMap.get(id);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProblemId().equals(problemid)) {
				list.get(i).setScore(score);
				System.out.println(list.get(i).getScore());

				submissionMap.put(id, list);

				persistSubmissionMap(submissionMap);

			}
		}
	}
	public Submission getSubmission(ArrayList<Submission> list, String problemId){
		Submission submission = new Submission();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getProblemId().equals(problemId)){
				submission = list.get(i);
				return submission;
			}
		}
		return submission;
	}

}
