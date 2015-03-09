package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.Administrator;
import edu.csupomona.cs480.data.NewReleaseProb;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.util.ResourceResolver;

public class AdminiManager extends Observable {

	private static final ObjectMapper JSON = new ObjectMapper();

	private Administrator getAdmin() {
		Administrator admin = null;
		File userFile = ResourceResolver.getDatabaseFile();

		if (userFile.exists()) {
			try {
				admin = JSON.readValue(userFile, Administrator.class);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			admin = new Administrator();
		}
		return admin;
	}

	private void persistAdministrator(Administrator administrator) {
		try {
			JSON.writeValue(ResourceResolver.getDatabaseFile(), administrator);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		Administrator admin = getAdmin();
		List<User> listId = admin.getUserDataBase();
		listId.add(user);		
		admin.setUserDataBase(listId);
		persistAdministrator(admin);
		addObserver(user);
	}

	public void deleteUserFromDatabase(String userId) {
		Administrator admin = getAdmin();
		User user = getUser(userId);
		if (user != null) {
			admin.getUserDataBase().remove(user);
			deleteObserver(user);
			persistAdministrator(admin);
		}
	}

	public String getAdminId(){
		Administrator administrator = getAdmin();
		String admin = administrator.getAdminId();
		return admin;
	}
	public User getUser(String userId) {
		Administrator admin = getAdmin();
		List<User> listId = admin.getUserDataBase();
		User user = null;
		String userFromList = "";
		for (int i = 0; i < listId.size(); i++) {
			userFromList =listId.get(i).getUserId();
			System.out.println("Original " + userId + "\tFrom LIst: " +userFromList);
			if (userId.equals(userFromList)) {
				user = listId.get(i);
				//user.setUserId(userId);
				System.out.println(user.getUserId());
				break;
			}
		}
		return user;
	}

	public void updateObserver() {
		Administrator admin = getAdmin();
		List<User> listId = admin.getUserDataBase();
		int size = listId.size();
		for (int i = 0; i < size; i++) {
			addObserver(listId.get(i));
		}
	}
	
	public List<User> getList(){
		Administrator admin = getAdmin();
		return admin.getUserDataBase();
	}
	
	public void changed(NewReleaseProb newProb){
		setChanged();
		notifyObservers(newProb);
	}
	
	public void displayObserver(){
		Administrator admin = getAdmin();
		System.out.println("The Numbser of  User in the Database is " + admin.getUserDataBase().size());
		System.out.println("This is All list of Observer" + countObservers());
		
	}
}
