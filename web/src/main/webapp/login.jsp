
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>Bejelentkezés</title>
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
                <div class="row col-md-6 offset-md-3">
                <div class="card border-0 shadow my-5">
                    <div class="card-body bg-light p-5">                        
                            <form action="login" method="post">                        
                                <div class="form-group row">
                                    <label>Felhasználónév vagy e-mail cím</label>
                                    <input name="username" class="form-control">
                                </div>
                                <div class="form-group row">
                                    <label>Jelszó</label>
                                    <input type="password" name="password" class="form-control">
                                </div>
                                <label>${error}</label>   
                            <div class="form-group row"><input type="submit" value="Bejelentkezés" class="btn btn-outline-success"></div>            
                        </form>
                        <div style="float: right;">
                            <a href="<c:url value="reg"><c:param name="ismerchant" value="${ismerchant}"/></c:url>"><input type="submit" value="Regisztráció" class="btn btn-success"></a>               
                        </div>
                    </div>
                </div>
            </div>        
                        </div>
    </body>
</html>
