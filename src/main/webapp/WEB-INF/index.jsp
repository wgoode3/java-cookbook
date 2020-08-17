<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CookBook</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	    <h1>Cookbook</h1>
	    
	    <div class="row">
	       <div class="col">
	           <form:form action="/register" method="post" modelAttribute="user">
	               <div class="form-group">
	                   <label>Username:</label>
	                   <form:input path="username" class="form-control" />
	                   <form:errors path="username" class="text-danger" />
	               </div>
	               <div class="form-group">
                       <label>Email:</label>
                       <form:input path="email" class="form-control" />
                       <form:errors path="email" class="text-danger" />
                   </div>
                   <div class="form-group">
                       <label>Password:</label>
                       <form:password path="password" class="form-control" />
                       <form:errors path="password" class="text-danger" />
                   </div>
                   <div class="form-group">
                       <label>Confirm Password:</label>
                       <form:password path="confirm" class="form-control" />
                       <form:errors path="confirm" class="text-danger" />
                   </div>
	               <input type="submit" value="Sign Up" class="btn btn-primary" />
	           </form:form>
	       </div>
	       <div class="col">
	           <form action="/login" method="post">
	               <div class="form-group">
                       <label>Email:</label>
                       <input type="text" name="email" class="form-control" />
                   </div>
	               <div class="form-group">
                       <label>Password:</label>
                       <input type="password" name="password" class="form-control" />
                   </div>
                   <input type="submit" value="Sign In" class="btn btn-primary" />
	           </form>
	       </div>
	    </div>
	    
	</div>
</body>
</html>