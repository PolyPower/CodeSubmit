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

    <title><h2> CodeSubmit </h2></title>    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
	
</style>
</head>


<body background= url("background.png")>

    <div id="header">
    	<h2><h2>CodeSubmit<span style="color:while;font-weight:bold"><sub>.com</sub></span></h2>              
    </div><br>
    <div>
   		<nav>
   			
			<a href="/about" target="_self">About</a> |
			<a href="/cs480/codeSubmitHome" target="_self">Archives</a> |
			<a href="/new/"target="_self">News</a> |
			<a href="/cs480/AdminHome"  target="_self">AdminHome</a> |
			<a href="/cs480/codeSubmit/login" target="_self">Register</a> |
			<a href="/cs480/codeSubmit" target="_self">Code Submit</a> |
			<a href="/cs480/contactUs" target = "_self"> ContactUs</a> |
		</nav>	
  	   		
	</div><br><br>
	
	<div>
	   <table>
	     <form method="POST" enctype = "multipart/form-data" action="/cs480/contactUs"> 
	        <tr>
				<td>Your Email Address : </td>
				<td><input type="text" id="userId" name="userAddress" required> </td>	
	       </tr>
	       <tr> Your FeedBack </tr>
	       <tr>
				<td>Title : </td>
				<td><input type="text" id="userId" name="title" required> </td>	
	       </tr>
	        <tr>
				<td>FeedBack : </td>
				<td><textarea rows="20" cols="70" name= "message"></textarea>
	       </tr>	       
	        <input type = "submit" value = "Submit">
	     </form>
	   </table>
	</div>
</body>
</html>