package edu.csupomona.cs480;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.provider.FSSubmissionManager;
import edu.csupomona.cs480.data.provider.SubmissionManager;


/**
 * Assignment 6 - JUnit test
 * @author Khamille
 *
 */
public class TestWeeklySubmission {
	
	SubmissionManager submissionManager;
	
	@Before
	public void setUp() {
		submissionManager = new FSSubmissionManager();
	}
	
	@Test
	public void Test() {
		int counter = 1;
		Submission submission;
		while(counter <= 5) {
			submission = new Submission();
			submission.setUserId("testID");
			submission.setWeekNo(counter);
		//	submission.setProbID("10765");
			submission.setFilePath("MyCode.java");
			submission.setStatus(false); // hard-coded value
			submission.setScore(0); // hard-coded value
			submissionManager.updateSubmissionList(submission);
			counter++;
		}
		
		ArrayList<Submission> mylist = submissionManager.getSubmissions("testID");
		boolean completed;
		if(mylist.size() == 5) {
			completed = true;
		} else {
			completed = false;
		}
		
		assertTrue(completed);
		
	}
	
	
	
}