
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Keresés az eladók között</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>            
            <div class="container">
                <form action="psearch" method="post">                                                             
                    <div class="form-group"> 
                        <div class="row">
                            <div class="col">                                                               
                                <select class="form-control" name="ptype">
                                    <option  disabled selected value> -- termék típus választás -- </option>
                                <c:forEach items="${types}" var="t">                                                                
                                    <option value="${t.key}">${t.value}</option>                                                                                 
                                </c:forEach>                                
                            </select>                                                                
                        </div>
                        <div class="col">  
                            <input name="qstring" class="form-control" placeholder="Keresett szöveg...">
                        </div>
                        <div class="col"><input type="submit" value="Keresés" class="btn btn-light"></div>
                    </div>
                </div>                                   
            </form>
            <c:choose>            
                <c:when test="${not empty rslist}">
                    <h3>Találati lista:</h3>
                    <c:forEach items="${rslist}" var="product">
                        <div class="row">
                            <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                                <a href="<c:url value="getmerchant"><c:param name="merchantid" value="${product.contactId}"/></c:url>"><h4>termelo</h4></a>
                                <p>${merchant.introduction}</p>    
                            </div>
                            <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                                <img class="layout-iamge" src="<c:out value="${images[merchant.id]}"/>" alt="">
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
