
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
        <style>
            header {	
                background-color: #444;
                background: url("<c:out value="${images[(1).intValue()].location}"/>") no-repeat center top;
                padding-top: 150px;
                background-attachment: relative;
                background-position: center center;
                height: 400px;
                width: 100%;

                -webkit-background-size: 100%;
                -moz-background-size: 100%;
                -o-background-size: 100%;
                background-size: 100%;

                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }



            header,h1 {
                margin-top: 50px;
                margin-bottom: 15px;
                color: #fff;                
            }
        </style>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>     
            <header>
            </header>
            <main>
                <div class="container">
                    <div class="row align-items-center my-5">
                        <div class="font-weight-light">
                            <h1>${merchant.nameToDisplay}</h1>
                    </div>
                </div>

                <div class="row py-5">
                    <div class="col-md-6">
                        <img class="img-fluid rounded" src="<c:out value="${images[(2).intValue()].location}"/>" alt="">
                    </div>
                    <div class="col-md-5 offset-md-1">        
                        <div class="font-weight-light">                            
                            <p class="text-justify">${merchant.introduction}</p>

                            <h5>Az alábbi településeken vagyok elérhető:</h5>
                            <c:forEach var="town" items="${towns}">
                                <p>${town.name}</p>
                            </c:forEach>
                            <h5>e-mail: ${email}</h5>

                        </div>
                    </div>
                </div>
                <div class="row py-5">                    
                    <div class="col-md-5 offset-md-1">        
                        <p class="text-justify">${merchant.description}</p> 
                    </div>    
                    <div class="col-md-6">
                        <img class="img-fluid rounded" src="<c:out value="${images[(3).intValue()].location}"/>" alt="">
                    </div>
                </div>
                <div class="row py-5">
                </div>                                          
            </div>

        </main>
    </body>
</html>
