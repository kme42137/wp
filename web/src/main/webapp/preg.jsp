
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Árucikkek rögzítése</title>
        <style>
            body { 
                background: url("images/login_img.jpg") no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>                        

            <div class="container top">
                <div class="col-md-10 offset-md-1">
                    <div class="card border-0 shadow my-5">
                        <div class="card-body bg-light p-5">   
                            <div class="row pb-4">
                            <h3>Árucikkek rögzítése</h3>                            
                            </div>
                            <form action="preg" method="post" enctype="multipart/form-data">
                                <div class="form-row">   
                                    <div class="form-group  col-md-6">
                                        <label>Az újonnan felvitt árucikk neve</label>
                                        <input name="pname" class="form-control" value="${userinput.name}">
                                    <label>${messages.pname}</label>  
                                </div>                    
                                <div class="form-group col-md-6">
                                    <label>Termékcsoport</label>                                                                                                            
                                    <select class="form-control" name="ptype">
                                            <c:set var="van" value="${false}"></c:set>                                
                                            <c:forEach items="${types}" var="t">                                                                
                                                <c:choose>
                                                    <c:when test="${t.key==userinput.type.id}">
                                                        <c:set var="van" value="${true}"></c:set>
                                                        <option value="${t.key}" selected>${t.value}</option>                                              
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${t.key}">${t.value}</option>                                             
                                                    </c:otherwise>                                    
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${!van}">
                                                <option selected value="0" > -- válasszon -- </option>
                                            </c:if>                                            
                                        </select>                                                                                                                                                
                                    <label>${messages.ptype}</label> 
                                </div>  
                            </div>
                            <div class="form-row">    
                                <div class="form-group col-md-12">
                                    <label>Termékleírás</label>
                                    <textarea class="form-control" name="pdescription" rows="4">${userinput.description}</textarea>
                                    <label>${messages.pdescription}</label> 
                                </div>       
                            </div>
                            <div class="form-group">
                                <label>Kép az árucikkhez</label>
                                <input type="file" name="img" class="form-control-file" />
                                <label>${messages.img}</label> 
                            </div>   
                            <div class="form-group row"><input type="submit" value="Jóváhagyás" class="btn btn-outline-success"></div> 
                        </form>

                        <c:if test="${not empty products}">              
                            <div class="row py-5">
                            <h3>Az eddigi termékei:</h3>                            
                            </div>
                            <c:forEach var="prod" items="${products}">
                                <div class="row">                                           
                                    <div class="col-md-7">
                                        <h4>${prod.name}</h4>
                                        <br>
                                        <c:out value="${types[prod.type.id]}"/>
                                        <br>
                                        <p>${prod.description}</p>
                                    </div>
                                    <div class="col-md-5">
                                        <img class="img-fluid" src="<c:out value="${images[prod.id]}"/>" alt="">
                                    </div>                 
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <form action="delproduct" method="post">
                                            <input type="hidden" value="${prod.id}" name="delproductid" />
                                            <input type="submit" value="Töröl" class="btn btn-outline-success">
                                        </form>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <hr>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>  
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
