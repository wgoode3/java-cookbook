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
        <a href="/new_recipe" class="btn btn-outline-info">New Recipe</a>
        <div class="alert alert-success">
            Welcome back ${user.username}!
        </div>
        
        <h1>Your Favorites</h1>
        
        <div class="row">
        <c:forEach items="${user.favorites}" var="recipe">
            <div class="col-sm-4 mb-3">
                <div class="card">
                    <div class="card-header bg-danger text-light">
                        ${recipe.name}
                    </div>
                    <div class="card-body">
                        <p>Servings: ${recipe.servings}</p>
                        <p>Ingredients:</p>
                        <ul>
                            <c:forEach items="${recipe.getFirstIngredients(3)}" var="ing">
                                <li>${ing.name}</li>
                            </c:forEach>
                            <li>...</li>
                        </ul>
                        <p class="snippet">${recipe.getShortInstructions(75)}</p>
                        <a class="btn btn-block btn-danger" href="/recipe/${recipe.id}">View Recipe</a>
                    </div>
                </div>
            </div>
        </c:forEach>        
        </div>
        
        <h1>Other Recipes</h1>
        
        <div class="row">
        <c:forEach items="${allRecipes}" var="recipe">
            <div class="col-sm-4 mb-3">
                <div class="card">
                    <div class="card-header bg-danger text-light">
                        ${recipe.name}
                        <c:if test="${!recipe.checkIfAlreadyLiked(user.id)}">                    
                            <a href="/fav/${recipe.id}" class="close text-light">${heart}</a>
                        </c:if>
                        <c:if test="${recipe.checkIfAlreadyLiked(user.id)}">                    
                            <a href="/unfav/${recipe.id}" class="close text-light">${hollow}</a>
                        </c:if>
                    </div>
                    <div class="card-body">
                        <p>Servings: ${recipe.servings}</p>
                        <p>Ingredients:</p>
                        <ul>
                            <c:forEach items="${recipe.getFirstIngredients(3)}" var="ing">
                                <li>${ing.name}</li>
                            </c:forEach>
                            <li>...</li>
                        </ul>
                        <p class="snippet">${recipe.getShortInstrunctions(75)}</p>
                        <a class="btn btn-block btn-danger" href="/recipe/${recipe.id}">View Recipe</a>
                    </div>
                </div>
            </div>
        </c:forEach>        
        </div>   
        
    </div>
</body>
</html>