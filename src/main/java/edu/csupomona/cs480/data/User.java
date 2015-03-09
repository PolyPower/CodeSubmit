package edu.csupomona.cs480.data;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.csupomona.cs480.data.provider.AdminiManager;
import edu.csupomona.cs480.util.SendMail;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * All the User in the database (In CPP).
 */
public class User implements Observer {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		AdminiManager admin = new AdminiManager();
		
		String receiver = userId;
		System.out.println(receiver);
		String title = getTitle((NewReleaseProb) arg1);
		String message = getMessage((NewReleaseProb) arg1);
		String filePath = getFilePath((NewReleaseProb) arg1);
		String fileName = getFileName((NewReleaseProb) arg1);
		
		Resource r = new ClassPathResource("applicationContext.xml");
		BeanFactory b = new XmlBeanFactory(r);
		SendMail m = (SendMail) b.getBean("mailMail");
		String sender = admin.getAdminId();// write here sender gmail id
       System.out.println(sender);
		m.sendMail(sender, receiver, title, message, filePath,fileName);
		System.out.println(userId +  ": Message is Sending to USERS");
		System.out.println(receiver);

	}
	
	private String getFileName(NewReleaseProb newProblem) {
		String fileName;
		fileName = newProblem.getFileName();
		return fileName;
	}

	private String getMessage(NewReleaseProb newProblem){
		String message; 
		message = "Hi, Students. The new Problem is posted on the CodeSubmit.com \n" +
				 "Week : " + newProblem.getweek() + "\n" +
				 "Problem Id : " + newProblem.getproblemId() + "\n" 
				 +
				 "Good Luck!\nAdministrator.";
		return message;
	}
	
	private String getTitle(NewReleaseProb newProblem){
		String title;
		title = "CodeSubmit Notify : New Problem, " + newProblem.getproblemId() + 
				" Released NOW";
		return title;
	}

	private String getFilePath(NewReleaseProb newProblem){
		String filePath;
		filePath = newProblem.getFilePath();
		return filePath;
	}
	
	@JsonIgnore
	public String[] getAllUsersAddress() {
		Administrator admin = new Administrator();
		List<User> listId = admin.getUserDataBase();
		final int size = admin.getUserDataBase().size();
		String[] usersAddress = new String[size];

		for (int i =0; i < size; i++) {
			usersAddress[i] = listId.get(i).getUserId();
		}


		return usersAddress;
	}
	

}
