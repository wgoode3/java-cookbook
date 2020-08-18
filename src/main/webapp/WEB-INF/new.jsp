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
        <a href="/logout" class="btn btn-outline-danger">logout</a>
        <a href="/home" class="btn btn-outline-info">Home</a>

        <form:form action="/recipe" method="post" modelAttribute="newRecipe">
            
            <input type="hidden" name="chef" value="${user.id}" />
        
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Recipe Name:</label>
                        <form:input path="name" class="form-control" />
                        <form:errors path="name" class="text-danger" />
                    </div>                
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Number of Servings:</label>
                        <form:input type="number" path="servings" class="form-control" />
                        <form:errors path="servings" class="text-danger" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label>Ingredients:</label>
                <form:textarea path="tempIngredients" class="form-control" />
                <form:errors path="tempIngredients" class="text-danger" />
                <small class="form-text text-muted">Separate multiple ingredients with commas ",".</small>
            </div>
            
            <div class="form-group">
                <label>Instructions:</label>
                <form:textarea path="instructions" class="form-control" />
                <form:errors path="instructions" class="text-danger" />
            </div>
        
            <input type="submit" value="Add Recipe" class="btn btn-primary btn-block" />
        
        </form:form>        
        
    </div>
</body>
</html>