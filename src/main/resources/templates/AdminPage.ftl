
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
    	<h2>CSS Administration Form</h2>  
    </div>
       <div>
   		<nav>
			<a href="/about/" target="_self">About</a> |
			<a href="/cs480/codeSubmitHome/"target= _self>Archives</a> |
			<a href="/new/" target=_self>News</a> |
			<a href="/cs480/AdminPage" target="_self">See TotalScore</a> |
			
		</nav>		   		
	</div><br>


    <div>
    	<table 	 width="100%">
		<tr>
			<th> UserID </th>
			<th> Total Score </th>
		</tr>
		<#list userscores as userscore>
			<tr>
			     <td><a href = "http://localhost:8080/cs480/AdminHome/list/${userscore.id}/listing">${userscore.id}</td>
			     <td>${userscore.totalScore}</td>
			</tr>
		</#list>
		
		</table>
    </body>

</html>