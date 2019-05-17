
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Termék keresés</title>
        <style>
            body { 
                background: url("images/search_img.jpg") no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>                                    
            <div class="container top font-weight-light">
                <div class="card border-0 shadow my-5">
                    <div class="card-body bg-light pt-3 pl-5 pr-5 pb-4">
                        <div class="row pb-2 pl-4">
                            <h5>Termék keresés</h5>
                        </div>
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
                                <div class="col-md-5">  
                                    <input name="qstring" class="form-control" placeholder="Keresett szöveg...">
                                </div>
                                <div class="col-md-1"><input type="submit" value="Keresés" class="btn btn-outline-success"></div>
                            </div>
                        </div>                                   
                    </form>
                    <c:choose>            
                        <c:when test="${not empty rslist}">
                            <div class="row p-4">
                                <h5>Találati lista:</h5>
                            </div>
                            <c:forEach items="${rslist}" var="product">
                                <div class="row">
                                    <div class="col-md-7 p-5">
                                        <h5>${product.name}</h5>                                                                                        
                                        <p class="text-justify py-5">${product.description}</p>    
                                        <a href="<c:url value="getmerchant"><c:param name="merchantid" value="${product.merchantId}"/></c:url>"><p>Tovább a termelőhöz</p></a>
                                        <c:if test="${sessionScope.user!=null}">
                                            <form action="cart" method="get">
                                                <input type="hidden" value="${product.name}" name="cartname" />
                                                <input type="hidden" value="${product.merchantId}" name="cartvalue" />
                                                <input type="submit" value="Listára vele" class="btn btn-outline-success">
                                            </form>
                                        </c:if>

                                    </div>
                                    <div class="col-md-5">
                                        <img class="img-fluid rounded" src="<c:out value="${images[product.id]}"/>" alt="">
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
            </div>
        </div>
    </body>
</html>
