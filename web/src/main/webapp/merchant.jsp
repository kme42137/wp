
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>     

            <div class="container">
                <div class="row align-items-center my-5">
                    <div class="col-md-12">
                        <img class="img-fluid rounded mb-4 mb-lg-0" src="<c:out value="${images[(1).intValue()].location}"/>" alt="">
                </div>

            </div>
            <div class="row">

                <div class="col-lg-5">
                    <div class="font-weight-light">
                        <h1>${merchant.nameToDisplay}</h1>

                    </div>
                </div>            
            </div>                       
            <!-- /.row -->
            <div class="row align-items-center my-5">
                <div class="col-lg-7">
                    <img class="img-fluid rounded mb-4 mb-lg-0" src="<c:out value="${images[(2).intValue()].location}"/>" alt="">
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-5">        
                    <div class="font-weight-light">
                        <p>${merchant.introduction}</p>
                        <p>${merchant.description}</p> 
                    </div>
                </div>
                <!-- /.col-md-4 -->
            </div>
            <div class="row align-items-center my-5">
                <div class="col-lg-7">
                    <img class="img-fluid rounded mb-4 mb-lg-0" src="<c:out value="${images[(3).intValue()].location}"/>" alt="">
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-5">        
                    <div class="font-weight-light">
                        <h4>Az alabbi telepuleseken vagyok elerheto:</h4>
                        <c:forEach var="town" items="${towns}">
                            <p>${town.name}</p>
                        </c:forEach>
                        <h4>e-mail: ${email}</h4>
                    </div>
                </div>                
            </div>
        </div>                                                                                                   
    </body>
</html>
