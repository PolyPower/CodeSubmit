package edu.csupomona.cs480.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.*;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.NewReleaseProb;
import edu.csupomona.cs480.data.Submission;
import edu.csupomona.cs480.data.SubmissionMap;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.UserScore;
import edu.csupomona.cs480.data.provider.AdminiManager;
import edu.csupomona.cs480.data.provider.NewReleaseProbManager;
import edu.csupomona.cs480.data.provider.SubmissionManager;
import edu.csupomona.cs480.util.SendMail;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map each HTTP API Path to the
 * correspondent method.
 * 
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with {@link Autowired}, it will be
	 * looking for the actual instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in the {@link App} class.
	 * 
	 * 1. newReleaseProbManager : Administrator could update new problems, and
	 * new problem object is stored in database, newRelease-Map.json.
	 * 
	 * 2. submissionManager : User can submit a file ( source code file ) into
	 * web service, and user's submission is stored in database,
	 * submission-Map.json.
	 * 
	 * 3. adminiManager : This manager object can be used to send an email to
	 * users in Database. Users in Database.json get an email when the
	 * administrator release new problem.
	 * 
	 */
	@Autowired
	private NewReleaseProbManager newReleaseProbManager;

	@Autowired
	private SubmissionManager submissionManager;

	@Autowired
	private AdminiManager adminiManager;

	/**
	 * This API lists all the submissions by users.
	 * 
	 * @return list of all submissions by users.
	 */
	@RequestMapping(value = "/list/allusers", method = RequestMethod.GET)
	ArrayList<Submission> listSubmissionForAll() {
		return submissionManager.listAllSubmissionsInStorage();
	}

	/**
	 * This API lists the submissions by the user (userId).
	 * 
	 * @return list of all submissions by the user.
	 */
	@RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
	ArrayList<Submission> listSubmissionsForUser(
			@PathVariable("userId") String userId) {
		return submissionManager.getSubmissions(userId);
	}

	/**
	 * This API lists the total scores for each users and users.
	 * 
	 * @return list of all users who submit problem solutions and users' id.
	 */
	@RequestMapping(value = "/list/totalScores/users", method = RequestMethod.GET)
	ArrayList<UserScore> listUser() {
		return submissionManager.listUser();
	}

	/**
	 * This API lists the all the User in the DataBase.json. Even an user has
	 * not submitted before, the user exists in Database.json Once user sign up
	 * our web site, they would get a notification email when administrator
	 * release a problem.
	 * 
	 * @return list of all user in the Database.
	 */
	@RequestMapping(value = "/list/userDatabase", method = RequestMethod.GET)
	public List<User> getUserId() {
		return adminiManager.getList();
	}

	/**
	 * This API get the user from DataBase.json
	 * 
	 * @param userId
	 * @return User object from DataBase.json.
	 */
	@RequestMapping(value = "/cs480/list/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = adminiManager.getUser(userId);
		return user;
	}

	/**
	 * This API lists the all problem sets
	 * 
	 * @return list of all problem sets
	 */
	@RequestMapping(value = "/list/problem", method = RequestMethod.GET)
	List<NewReleaseProb> listAllProblems() {
		return newReleaseProbManager.listAllProblems();
	}

	/********** Web UI for the set submission, and score *********/

	/**
	 * Web UI for codeSubmit.com / URL : cs480/codeSubmitHome The web UI
	 * represents all problem sets.
	 * 
	 * @return Web UI, template "codeSubmitHome.
	 */
	@RequestMapping(value = "/cs480/codeSubmitHome", method = RequestMethod.GET)
	ModelAndView getcodeSubmitHome() {
		ModelAndView modelAndView = new ModelAndView("codeSubmitHome");
		List<NewReleaseProb> list = listAllProblems();
		if (list == null) {
			modelAndView.addObject("problems", new ArrayList<NewReleaseProb>());
			return modelAndView;
		}
		modelAndView.addObject("problems", listAllProblems());
		return modelAndView;
	}

	/**
	 * Web UI display user's submissions and problem for Individual users.
	 * 
	 * @return Web UI
	 */
	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.GET)
	ModelAndView getUsercodeSubmit() {
		ModelAndView modelAndView = new ModelAndView("codeSubmitForAll");

		modelAndView.addObject("problems", listAllProblems());

		return modelAndView;
	}

	/**
	 * Web UI where user login to submit their solutions
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cs480/codeSubmit/login", method = RequestMethod.GET)
	ModelAndView getlogin() {
		ModelAndView modelAndView = new ModelAndView("loginHome");
		return modelAndView;
	}

	/**
	 * If an user does not exist in the DataBase.json, it will throw the error
	 * message, and the Web UI explicitly moves to the SignUp page. Otherwise,
	 * web UI explicitly moves to the codeSubmit.com
	 * 
	 * @param userId
	 * @return modelView, if user exist, modelView = codeSubmit. Otherwise,
	 *         modelView = SignUp
	 */
	@RequestMapping(value = "/cs480/codeSubmit/login", method = RequestMethod.POST)
	public ModelAndView logininByUser(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("codeSubmit");

		// User user = adminiManager.getUser(userId);

		List<Submission> submission = listSubmissionsForUser(userId);
		if (submission != null) {
			modelAndView.addObject("submissions", submission);
			return modelAndView;
		}
		modelAndView.addObject("submissions", new ArrayList<Submission>());
		return modelAndView;
	}

	/**
	 * This dispaly the ContactUs page.
	 * 
	 * @return modelAndVeiw
	 */
	@RequestMapping(value = "/cs480/contactUs", method = RequestMethod.GET)
	ModelAndView getContactUs() {
		ModelAndView model = new ModelAndView("ContactUs");
		return model;

	}

	/**
	 * This method is retrieved datas from ContactUs UI. and Send a emial to
	 * administrator.
	 */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	ModelAndView getaboutUs() {
		ModelAndView modelAndView = new ModelAndView("About");
		return modelAndView;
	}

	@RequestMapping(value ="/new", method = RequestMethod.GET)
	ModelAndView getNews(){
		ModelAndView model = new ModelAndView("news");
		return model;
	}

	@RequestMapping(value = "/cs480/contactUs", method = RequestMethod.POST)
	ModelAndView getFeedBack(@RequestParam("userAddress") String address,
			@RequestParam("title") String titlefromUser,
			@RequestParam("message") String feedback) {
		ModelAndView model = new ModelAndView("ContactUs");

		String message = "User's address : " + address + "\n" + feedback;
		String title = "CodeSubmit FeedBack : " + titlefromUser;
		Resource r = new ClassPathResource("applicationContext.xml");
		BeanFactory b = new XmlBeanFactory(r);
		SendMail m = (SendMail) b.getBean("mailMail");
		String sender = adminiManager.getAdminId();// write here sender gmail id

		m.sendMail(sender, sender, title, message);
		return model;
	}

	/**
	 * WebUI display all users' submissions.
	 * 
	 * @return modelAndView
	 */
	@RequestMapping(value = "/cs480/AdminHome", method = RequestMethod.GET)
	ModelAndView getAdmin() {
		ModelAndView modelAndView = new ModelAndView("AdminHome");
		modelAndView.addObject("submissions", listSubmissionForAll());
		return modelAndView;
	}

	/**
	 * This function is for an administrator to search an individual user.
	 * 
	 * @param userId
	 * @return modelAndView with a list of the user's submissions
	 */
	@RequestMapping(value = "/cs480/AdminHome/list/user", method = RequestMethod.POST)
	ModelAndView getUsersInAdmin(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("/AdminSecond");
		ArrayList<Submission> list = submissionManager.getSubmissions(userId);
		if (list != null) {
			modelAndView.addObject("submissions",
					listSubmissionsForUser(userId));
			return modelAndView;
		}
		System.out.println("user is not exist");
		modelAndView = new ModelAndView("/AdminHome");
		modelAndView.addObject("submissions", new ArrayList<Submission>());
		return modelAndView;
	}

	/**
	 * This function is for an administrator to search an individual user by
	 * clicking the userId.
	 * 
	 * @param userId
	 * @return modelAndView with a list of the user's submissions
	 */
	@RequestMapping(value = "/cs480/AdminHome/list/{user}/listing", method = RequestMethod.GET)
	ModelAndView getUsersbyClick(@PathVariable("user") String userId) {
		ModelAndView modelAndView = new ModelAndView("AdminSecond");
		modelAndView.addObject("submissions", listSubmissionsForUser(userId));
		return modelAndView;
	}

	/**
	 * This function allows an user to be a member of codeSubmit.
	 * 
	 * @param userId
	 * @return signup Page
	 */
	@RequestMapping(value = "/cs480/codeSubmit/signUp", method = RequestMethod.POST)
	public ModelAndView signUpByUser(@RequestParam("userId") String userId) {
		ModelAndView modelAndView = new ModelAndView("loginHome");
		User user = new User();
		user.setUserId(userId);
		adminiManager.updateUser(user);
		modelAndView = new ModelAndView("codeSubmitHome");
		modelAndView.addObject("problems", listAllProblems());
		return modelAndView;
	}

	@RequestMapping(value = "/cs480/AdminPage", method = RequestMethod.GET)
	public ModelAndView getAdminPage() {
		ModelAndView model = new ModelAndView("AdminPage");
		model.addObject("userscores", listUser());
		return model;
	}

	/**
	 * This function is for user to submit their submission.
	 * 
	 * @param userId
	 * @param weekNo
	 * @param problemID
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/cs480/codeSubmit", method = RequestMethod.POST)
	ModelAndView updateSubmission(@RequestParam("userId") String userId,
			@RequestParam("problemId") String probID,
			@RequestParam("file") MultipartFile file) {

		ModelAndView modelAndView = new ModelAndView("codeSubmit");
		String name = null;
		name = file.getOriginalFilename();
		String dir = System.getProperty("user.home") + "\\cs480\\";
		System.out.println(dir);
		if (!file.isEmpty()) {
			try {
				int week = newReleaseProbManager.getProbId(probID).getweek();
				String term = newReleaseProbManager.getProbId(probID).getterm();
				System.out.println(week);

				Submission submission = new Submission();

				submission.setUserId(userId);
				submission.setWeekNo(week);
				submission.setProblemId(probID);
				submission.setFileName(name);
				submission.setTerm(term);
				submission.setFilePath(dir + name);
				submission.setStatus(false); // hard-coded value
				submission.setScore(0); // hard-coded value
				submissionManager.updateSubmissionList(submission);

				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(dir + name)));
				stream.write(bytes);
				stream.close();

				modelAndView.addObject("submissions",
						listSubmissionsForUser(userId));
				return modelAndView;

			} catch (Exception e) {
				System.out.println("Error message : uploading File");
				return modelAndView;
			}

		} else {
			System.out.println("File doesnot exist");
			return modelAndView;
		}

	}

	/**
	 * Download file, done
	 * 
	 * file should be in the repository folder
	 * 
	 * 
	 * @param fileName
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/{user}/{problemId}/download", method = RequestMethod.GET)
	public void getFile(@PathVariable("user") String userId,
			@PathVariable("problemId") String problemId,
			HttpServletResponse response) throws IOException {

		ArrayList<Submission> list = submissionManager.getSubmissions(userId);
		Submission user = submissionManager.getSubmission(list, problemId);
		String path = user.getFilePath();
		System.out.println(path);
		File f = new File(path);
		System.out.println(f.getAbsolutePath() + " " + f.exists());
		InputStream file = new FileInputStream(f);

		// 2. Return the photo file as an output stream using the code below
		IOUtils.copy(file, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename= \""
				+ f.getName() + "\"");

		response.flushBuffer();

	}

	@RequestMapping(value = "/problem/{problem}/download", method = RequestMethod.GET)
	public void getProblemFile(@PathVariable("problem") String probId,
			HttpServletResponse response) throws IOException {

		NewReleaseProb problem = newReleaseProbManager.getProbId(probId);
		String path = problem.getFilePath();
		System.out.println(path);
		File f = new File(path);
		System.out.println(f.getAbsolutePath() + " " + f.exists());
		InputStream file = new FileInputStream(f);

		IOUtils.copy(file, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename= \""
				+ f.getName() + "\"");

		response.flushBuffer();
	}

	/**
	 * This function release a new problem .
	 * 
	 * @param problemId
	 * @param weekNo
	 * @param term
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/cs480/AdminHome", method = RequestMethod.POST)
	public @ResponseBody ModelAndView releaseFileUpload(
			@RequestParam("ProblemID") String problemId,
			@RequestParam("Weeks") int weekNo,
			@RequestParam("Term") String term,
			@RequestParam("ProblemDescription") MultipartFile file) {
		String name = null;
		String dir = System.getProperty("user.home") + "\\cs480\\";
		name = file.getOriginalFilename();
		if (!file.isEmpty()) {
			try {

				System.out.println("Release Problem .....");

				NewReleaseProb newProb = new NewReleaseProb.NewReleaseProblemBuilder()
						.withfileName(name).withfilePath(dir + name)
						.withproblemId(problemId).withweekNo(weekNo)
						.withterm(term).build();

				// observer
				adminiManager.updateObserver();
				adminiManager.displayObserver();
				// add
				adminiManager.changed(newProb);
				adminiManager.deleteObservers();
				adminiManager.displayObserver();

				newReleaseProbManager.updateNewProblem(newProb);
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(dir + name)));
				stream.write(bytes);
				stream.close();

				ModelAndView modelAndView = new ModelAndView("/AdminHome");
				// modelAndView.addObject("problems", listAllProblems());
				modelAndView.addObject("submissions", listSubmissionForAll());
				modelAndView.addObject("scores", listUser());

				return modelAndView;
			} catch (Exception e) {
				ModelAndView modelAndView = new ModelAndView("/AdminHome");
				modelAndView.addObject("submissions", listSubmissionForAll());
				modelAndView.addObject("scores", listUser());
				return modelAndView;
			}
		} else {
			System.out.println("file is empty");
			ModelAndView modelAndView = new ModelAndView("/AdminHome");
			return modelAndView;
		}
	}

	/**
	 * This function is for setting a score for the user.
	 * 
	 * @param id
	 * @param week
	 * @param score
	 * @return
	 */
	@RequestMapping(value = "/cs480/AdminHome/{userId}/{problemid}/setScore", method = RequestMethod.POST)
	public @ResponseBody ModelAndView setScore(
			@PathVariable("userId") String id,
			@PathVariable("problemid") String problemid,
			@RequestParam(value = "score") int score) {

		submissionManager.setScore(id, problemid, score);
		ModelAndView modelAndView = new ModelAndView("/AdminSecond");
		modelAndView.addObject("submissions", listSubmissionsForUser(id));
		return modelAndView;
	}

}