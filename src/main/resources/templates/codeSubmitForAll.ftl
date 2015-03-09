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

    <title><h2> CSS CodeSubmit </h2></title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    
    <div id="header">
    	<h2><h2>CodeSubmit<span style="color:while;font-weight:bold"><sub>.com</sub></span></h2>              
    </div><br>
    <div>
   		<nav>
			<a href="/about/">About</a> |
			<a href="/cs480/codeSubmitHome">Archives</a> |
			<a href="/new/">News</a> |
		</nav>		   		
	</div><br>
     
   
     	<br><br>
		
	<form method="POST" enctype = "multipart/form-data" action="/cs480/codeSubmit">  
    	<table width="120">
    		<tr>
				<td>UserID: </td>
				<td><input type="text" id="userId" name="userId" required> </td>	
			   
			</tr>
			<tr>
				<td>ProblemID: </td>	
				<td><textarea rows="" cols="7" id="prob" name="problemId"> </textarea></td>
			</tr> 
			<tr>	
    		<input type="file" id="file" name="file" size="40" required">
    	
    		</tr>
        	<input type="submit" value="Upload">
    </form>
	
	</table>
	</div>
    
      
<script>

	function displayMsg(){
		alert("You successfully uploaded!");
	}
	
</script>

   
<br><br><br><br><br><br><br><br><br>
   <div id="footer">
    	<a href="/cs480/contactUs" target="_self">Contact us</a> 
    	<br>
    	Copypright © PolyPower
    	<a href="http://codesubmit.com" target="_self"></a> 
    </div>	   
          
</body>

</html>