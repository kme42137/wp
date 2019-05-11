
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Termék keresés</title>
        <link rel="stylesheet" href="css/hihihaha.css">
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>                                    
            <div class="container top">
                <form action="psearch" method="post">                                                             
                    <div class="form-group"> 
                        <div class="row">
                            <div class="col-md-3">
                                <select class="form-control" name="townid">
                                    <option value="0" selected> -- város választás -- </option>
                                <c:forEach items="${townList}" var="town">                                                                
                                    <option value="${town.id}">${town.name}</option>                                                                                 
                                </c:forEach>                                
                            </select>
                        </div>
                            <div class="col-md-3">                                                               
                                <select class="form-control" name="ptype">
                                    <option value="0" selected> -- termék típus választás -- </option>
                                <c:forEach items="${types}" var="t">                                                                
                                    <option value="${t.key}">${t.value}</option>                                                                                 
                                </c:forEach>                                
                            </select>                                                                
                        </div>
                        <div class="col-md-4">  
                            <input name="qstring" class="form-control" placeholder="Keresett szöveg...">
                        </div>
                        <div class="col-md-2"><input type="submit" value="Keresés" class="btn btn-light"></div>
                    </div>
                </div>                                   
            </form>
             <c:choose>            
                <c:when test="${not empty rslist}">
                    <h3>Találati lista:</h3>
                    <br>
                    <c:forEach items="${rslist}" var="product">
                        <div class="row">
                            <div class="col-md-7">
                                <p>${product.name}</p>    
                                <a href="<c:url value="getmerchant"><c:param name="merchantid" value="${product.merchantId}"/></c:url>"><h4>Tovabb a termelohoz</h4></a>
                                    <br>
                                    <p>${product.description}</p>    
                            </div>
                            <div class="col-md-5">
                                <img class="img-fluid" src="<c:out value="${images[product.id]}"/>" alt="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <hr>
                            </div>
                        </div>
                    </c:forEach>            
                </c:when>
                <c:otherwise>
                    <h3>${message}</h3>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
