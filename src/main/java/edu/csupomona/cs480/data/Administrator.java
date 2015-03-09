/**
 * 
 */
package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.List;

import edu.csupomona.cs480.data.provider.AdminiManager;

/**
 * When an Administrator set the New Problem Set ( NewReleaseProb object ), it
 * will notify all Registered user in the database.
 *
 */
public class Administrator {
	private String adminId = "lovemonky88@gmail.com";
	private NewReleaseProb newProblem;
	private List<User> userDataBase;

	public Administrator() {
		userDataBase = new ArrayList<User>();
	}

	public List<User> getUserDataBase() {
		return userDataBase;
	}

	public void setUserDataBase(List<User> userDataBase) {
		this.userDataBase = userDataBase;
	}

	public NewReleaseProb getNewProblem() {
		return newProblem;
	}

	public void setNewProblem(NewReleaseProb newProblem) {
		this.newProblem = newProblem;

	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String toString() {

		return new StringBuffer(" Admin Id : ").append(this.adminId)

		.append(" ProblemId : ").append(this.newProblem).append(" List : ")
				.append(this.userDataBase).toString();

	}

}
