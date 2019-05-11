
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arucikkek rogzitese</title>
         <style>
            .top{
                margin-top: 150px;                
            }
        </style>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>                        

            <div class="container top">
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
                            <option  disabled selected value> -- válasszon -- </option>
                            <c:forEach items="${types}" var="t">                                                                
                                <option value="${t.key}">${t.value}</option>                                                                                 
                            </c:forEach>                                
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
                <input type="hidden" value="${productid}" name="productid" />
                <div class="form-group row"><input type="submit" value="Jóváhagyás" class="btn btn-light"></div> 
            </form>
        </div>
                <c:if test="${not empty products}">              
        <div class="container">
            <h3>Az eddigi termekei:</h3>
            <br>
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
                            <input type="submit" value="Töröl" class="btn btn-primary">
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <hr>
                    </div>
                </div>
            </c:forEach>

        </div>
        </c:if>  
    </body>
</html>
