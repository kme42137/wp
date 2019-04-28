
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Arucikkek rogzitese</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="col-md-6 offset-md-3">
                <form action="preg" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Az újonnan felvitt árucikk neve</label>
                        <input name="pname" class="form-control" value="${userinput.name}">
                    <label>${messages.pname}</label>  
                </div>                        
                <div class="form-group">
                    <label>Termékleírás</label>
                    <textarea class="form-control" name="pdescription" rows="4">${userinput.description}</textarea>
                    <label>${messages.pdescription}</label> 
                </div>       
                <div class="form-group">
                    <label>Termékcsoport</label>   
                    <select class="form-control" name="ptype">
                        <option  disabled selected value> -- válasszon -- </option>
                        <c:forEach items="${types}" var="t">                                                                
                            <option value="${t.key}">${t.value}</option>                                                                                 
                        </c:forEach>                                
                    </select>
                    <label>${messages.ptype}</label> 
                </div>                                   
                <div class="form-group">
                    <label>Kép az árucikkhez</label>
                    <input type="file" name="img" class="form-control-file" />
                    <label>${messages.img}</label> 
                </div>   
                <div class="form-group row"><input type="submit" value="Jóváhagyás" class="btn btn-light"></div> 
            </form>
        </div>  
    </body>
</html>
