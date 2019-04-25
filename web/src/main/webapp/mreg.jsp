
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
            <div class="col-md-6 offset-md-3">
                <form action="mreg" method="post">
                    <div class="form-group">
                        <label>A vállalkozásának neve vagy az a megnevezés, ahogy szeretne megjelenni az oldalon</label>
                        <input name="pnametodisplay" class="form-control" value="${userinput.nameToDisplay}">
                    <label>${messages.pnametodisplay}</label>  
                </div>
                <div class="form-group">
                    <label>Tömör leírása a tevékenységének, termékeinek</label>
                    <textarea class="form-control" name="pintroduction" rows="3">${userinput.introduction}</textarea>
                    <label>${messages.pintroduction}</label> 
                </div>           
                <div class="form-group">
                    <label>Hosszabb bemutatkozó szöveg</label>
                    <textarea class="form-control" name="pdescription" rows="6">${userinput.description}</textarea>
                    <label>${messages.pdescription}</label> 
                </div> 
                <label>Városok, ahol Ön elérhető:</label>
                <div class="form-group"> 
                    <div class="row">
                        <div class="col">
                            <select class="form-control" name="town1">
                                <c:set var="van" value="${false}"></c:set>                                
                                <c:forEach items="${townList}" var="town">                                                                
                                    <c:choose>
                                    <c:when test="${town.id==userinput.townIds[0]}">
                                        <c:set var="van" value="${true}"></c:set>
                                        <option value="${town.id}" selected>${town.name}</option>                                              
                                    </c:when>
                                        <c:otherwise>
                                            <option value="${town.id}">${town.name}</option>                                              
                                        </c:otherwise>                                    
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${van}">
                                        <option disabled value> -- válasszon -- </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option disabled selected value> -- válasszon -- </option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="col">
                            <select class="form-control" name="town2">
                                <c:set var="van" value="${false}"></c:set>                                
                                <c:forEach items="${townList}" var="town">                                                                
                                    <c:choose>
                                    <c:when test="${town.id==userinput.townIds[1]}">
                                        <c:set var="van" value="${true}"></c:set>
                                        <option value="${town.id}" selected>${town.name}</option>                                              
                                    </c:when>
                                        <c:otherwise>
                                            <option value="${town.id}">${town.name}</option>                                              
                                        </c:otherwise>                                    
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${van}">
                                        <option disabled value> -- válasszon -- </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option disabled selected value> -- válasszon -- </option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <select class="form-control" name="town3">
                                <c:set var="van" value="${false}"></c:set>                                
                                <c:forEach items="${townList}" var="town">                                                                
                                    <c:choose>
                                    <c:when test="${town.id==userinput.townIds[2]}">
                                        <c:set var="van" value="${true}"></c:set>
                                        <option value="${town.id}" selected>${town.name}</option>                                              
                                    </c:when>
                                        <c:otherwise>
                                            <option value="${town.id}">${town.name}</option>                                              
                                        </c:otherwise>                                    
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${van}">
                                        <option disabled value> -- válasszon -- </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option disabled selected value> -- válasszon -- </option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="col">
                            <select class="form-control" name="town4">
                                <c:set var="van" value="${false}"></c:set>                                
                                <c:forEach items="${townList}" var="town">                                                                
                                    <c:choose>
                                    <c:when test="${town.id==userinput.townIds[3]}">
                                        <c:set var="van" value="${true}"></c:set>
                                        <option value="${town.id}" selected>${town.name}</option>                                              
                                    </c:when>
                                        <c:otherwise>
                                            <option value="${town.id}">${town.name}</option>                                              
                                        </c:otherwise>                                    
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${van}">
                                        <option disabled value> -- válasszon -- </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option disabled selected value> -- válasszon -- </option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                    <label>${messages.town}</label>
                </div>      
                <div class="form-group row"><input type="submit" value="Jóváhagyás és tovább a képek feltöltéséhez" class="btn btn-light"></div>                
            </form> 
        </div>
    </body>
</html>
