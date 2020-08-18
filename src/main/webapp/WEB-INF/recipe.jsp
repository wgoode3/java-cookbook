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
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
    <div class="container">
        <a href="/logout" class="btn btn-outline-danger">logout</a>
        <a href="/home" class="btn btn-outline-info">Home</a> 
        <div class="card">
            <div class="card-header bg-danger text-light">${recipe.name}</div>
            <div class="card-body">
                <p>Chef: ${recipe.chef.username}</p>
                <p>Servings: ${recipe.servings}</p>
                <p>Ingredients:</p>
                <ul>
                    <c:forEach items="${recipe.ingredients}" var="ing">
                        <li>${ing.name}</li>
                    </c:forEach>
                </ul>
                <p class="instructions">${recipe.instructions}</p>
                <c:if test="${user.id == recipe.chef.id}">            
                    <a class="btn btn-block btn-danger" href="/recipe/${recipe.id}">Edit Recipe</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>