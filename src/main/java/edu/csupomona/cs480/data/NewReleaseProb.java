package edu.csupomona.cs480.data;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The basic user object.
 */

public class NewReleaseProb {

	//private String filePath;
	private String problemId;
	private String term;
	private String fileName;
	private int week;
	private String problemDescription;
	
	@JsonCreator
	public NewReleaseProb(
			@JsonProperty("filePath") String filePath,
			@JsonProperty("problemId") String problemId,
			@JsonProperty("term") String term,
			@JsonProperty("fileName") String fileName,
			@JsonProperty("weekNo") int week,
			@JsonProperty("problemDescription") String problemDescription)
	{
		this.fileName = fileName;
		//this.filePath = filePath;
		this.problemId = problemId;
		this.term = term;
		this.week = week;
		this.problemDescription = filePath;
	}

	public NewReleaseProb(NewReleaseProblemBuilder newReleaseProbBuilder) {

		this.fileName = newReleaseProbBuilder.fileName;
		this.problemDescription = newReleaseProbBuilder.filePath;
		this.problemId = newReleaseProbBuilder.problemId;
		this.term = newReleaseProbBuilder.term;
		this.week = newReleaseProbBuilder.week;
		//this.problemDescription = newReleaseProbBuilder.problemDescription;

	}

	public String getFilePath() {
		return problemDescription;
	}

	public String getproblemId() {
		return problemId;
	}

	public String getFileName() {
		return fileName;
	}

	public int getweek() {
		return week;
	}

	public String getterm() {

		return term;
	}

	public String getproblemDescription() {

		return problemDescription;
	}
	public static class NewReleaseProblemBuilder {
		
		private String problemId;
		//private int problemNo;
		private String fileName;
		private String filePath;
		private int week;
		private String problemDescription;
		private String term;
		
		public NewReleaseProblemBuilder withproblemId(String problemId) {
			this.problemId = problemId;
			return this;
		}

		public NewReleaseProblemBuilder withfilePath(String filePath) {
			this.filePath = filePath;
			return this;
		}

		public NewReleaseProblemBuilder withfileName(String fileName) {
			this.fileName = fileName;
			return this;
	}

		public NewReleaseProblemBuilder withweekNo(int week) {
			this.week = week;
			return this;
		}
		public NewReleaseProblemBuilder withterm(String term) {
			this.term = term;
			return this;
		}

		public NewReleaseProblemBuilder withproblemDes(String problemDescription) {
			this.problemDescription = problemDescription;
			return this;
		}
//		public NewReleaseProblemBuilder withproblemNo(int problemNo) {
//			this.problemNo = problemNo;
//			return this;
//		}

		public NewReleaseProb build() {
			return new NewReleaseProb(this);
		}


	}
}

