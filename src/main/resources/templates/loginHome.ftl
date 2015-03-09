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

	form
	{
	    width: 300px;
	    margin: 0 auto;
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
    
    <title> <h2> Login </h2> </title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
	
</style>
</head>

<body>    

  
      
    <div id="header">
    	<h2> if you want to get news about codeSubmit, Please submit you email </h2>              
    </div>
    <br><br>
	
	       <div>
   		<nav>
   			
			<a href="/about" target="_self">About</a> |
			<a href="/cs480/codeSubmitHome" target="_self">Archives</a> |
			<a href="/new/"target="_self">News</a> |
			<a href="/cs480/codeSubmit" target="_self">Code Submit</a> |
			<a href="/cs480/contactUs" target = "_self"> ContactUs</a> |
		</nav>	
  	   		
	</div><br><br>
    <div>
       <table border = "1" align = "center" >
          <tr> 
             <td>New User : </td>
             <td>
			        <form method = "POST" enctype = "multipart/form-data" action = "/cs480/codeSubmit/signUp">
		     		<input type ="text" id = "userId" name = "userId" required>
		     		<input type = "submit" value = "Submit" >
		     		</form>
		     </td>
       </table>
    </div>
    <script language="javascript">
    	function check(form)
    	{
    		
    		if(form.userId.value == "hs")
    		{
    			window.open('codeSubmit.ftl')
    		}
    		else{
    		alert("Invalid userid")
    		}
    
    </script>
      
</body>

</html>