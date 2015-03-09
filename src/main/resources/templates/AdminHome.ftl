
<html>
<head>

<style>
	#header
	{
		background-color:black;
		color:red;
		text-align:center;
		padding:5px;
	}
	#footer
	{
		background-color:black;
		color:red;
		text-align:center;
		padding:5px;
	}
	
	table
	{		 
		 align="center";
		 border: 1px solid black; 
	}	
	th
	{
		padding: 5px;
    	text-align: left;
    	color: white;
       	border: 1px solid black;
       	background-color: DimGray;       				 
	}
	td
	{
		padding: 5px;
    	text-align: left;
       	border: 1px solid black;	
       	color: DimGray;	
    }
   nav
    {
      border:1px solid red;
      border-width:1px 0;
      list-style:none;
      margin:0;
      padding:0;
      text-align:right;
    
    }
    <title> <h2><> Administrator </title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
    <div id="header">
    	<h2>codeSubmit Administration</h2>  
    </div> <br>
    <div>
   		<nav>
			<a href="/about/" target="_self">About</a> |
			<a href="/cs480/codeSubmitHome/"target= _self>Archives</a> |
			<a href="/new/" target=_self>News</a> |
			<a href="/cs480/AdminPage" target="_self">See TotalScore</a> |
			
		</nav>		   		
	</div><br>
    
    
    
    <br><br><br><br><br>
    <h4 style="color:red">Release new problem!</p> </h4>
	<form method="POST" enctype = "multipart/form-data" action="/cs480/AdminHome">  	
    	<table width="20">
    
			<tr>	
    		<input type="file" id="file" name="ProblemDescription" size="40" required">    	
    	
        	<input type="submit" value="Upload">
		</tr>

    		<tr>
				<td>ProblemID: </td>
				<td><input type="text" id="probId" name="ProblemID" required pattern="[0-9]{1,}" required> </td>
			</tr>
			   
			    <td>Week: </td>
			    <td><select  id="mySelect" name="Weeks" onchange="selectWeek()" required>
			    
				    <option value="0">      </option>
				    <option value="1">Week 1</option>
				    <option value="2">Week 2</option>
				    <option value="3">Week 3</option>
				    <option value="4">Week 4</option>
				    <option value="5">Week 5</option>
			    </select></td>
			</tr>
			
			</tr>
			   
			    <td>Term: </td>
			    <td><select  id="myterm" name="Term" onchange="selectTerm()" required>
			    
				    <option value="0">      </option>
				    <option value="Fall">Fall</option>
				    <option value="Winter">Winter</option>
				    <option value="Spring">Spring</option>
				    
			    </select></td>
			</tr>
        </table>
    </form>
		   	
    <#if submissions?has_content>
    <#else>
        <p>No result found.</p>
    </#if>
    
    <div>
        <h4 style="color:red">Search User:</p>     
       <table border = "1">
          <tr> 
             <td>User Name: </td>
             <td>
			        <form method="POST" enctype="multipart/form-data" action = "/cs480/AdminHome/list/user">
		     		<input type="text" name="userId" required>
		     		<input type = "submit" value="Search" >
		     		</form>
		     </td>
       </table>
    </div>
    
	  <br><br><br>
	<table 	 width="100%">
		<tr>
			<th> Week </th>
			<th> UserID </th>
			<th> ProbemID </th>
			<th> File </th>
			<th> term </th>
			<th> Date </th>
			<th> Score </th>

		</tr>
		<#list submissions as submission>
			<tr>
			     <td>${submission.weekNo}</td>
			     <td>${submission.userId}</td>
                 <td>${submission.problemId}</td>
                 <td><a href = "http://localhost:8080/user/${submission.userId}/${submission.problemId}/download ">${submission.fileName}</td>
                 <td>${submission.term}</td>
                 <td>${submission.creationTime}</td>
                 <td>${submission.score}</td>

			</tr>
		</#list>
		
		</table>

	</div>
    
 
<br><br><br><br><br><br><br><br><br>
   <div id="footer">
    	Copypright © PolyPower
    	<a href="http://codesubmit.com" target="_self"></a> 
    </div>	   
       
</body>
<script>
	function newProblemReleased()
	{
		alert("New Probem has been released!");
	}
	var count=0;
	
 	function countProblemNo()
 	{
 		count++;
 		document.getElementById("showCount").value = count;
 		 	
 	}
 	Release.onclick = countProblemNo;
 	
	function releasePro()
	{
		document.getElementById("submit").disabled = true;
    }
</script>
</html>