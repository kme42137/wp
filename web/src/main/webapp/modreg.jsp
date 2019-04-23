
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
                <form action="modreg" method="post">                                            
                <div class="form-group row">
                    <label>Vezetéknév</label>
                    <input name="plastname" class="form-control" value="${userinput.lastName}">
                    <label>${messages.plastname}</label>                
                </div>
                <div class="form-group row">
                    <label>Keresztnév</label>
                    <input name="pfirstname" class="form-control" value="${userinput.firstname}">
                    <label>${messages.pfirstname}</label>    
                </div>
                <div class="form-group row">
                    <label>E-mail cím</label>
                    <input name="pemail" class="form-control" value="${userinput.eMail}">
                    <label>${messages.pemail}</label>    
                </div>
                <div class="form-group row">
                    <label>Jelszó (minimum 7 karakter)</label>
                    <input type="password" name="ppassword1" class="form-control">
                    <label>${messages.ppassword1}</label>    
                </div>
                <div class="form-group row">
                    <label>Jelszó megerősítése</label>
                    <input type="password" name="ppassword2" class="form-control">
                </div>                
                <div class="form-group row"><input type="submit" value="Jóváhagyás" class="btn btn-light"></div>            
            </form>
        </div>
    </body>
</html>
