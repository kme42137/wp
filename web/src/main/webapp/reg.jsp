
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
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
                <div class="col-md-8 offset-md-2">
                    <div class="card border-0 shadow my-5">
                        <div class="card-body bg-light p-5">                       

                            <form action="reg" method="post">                        
                                <div class="form-group row">
                                    <label>Felhasználónév</label>
                                    <input name="pnickname" class="form-control" value="${userinput.nickname}">
                                <label>${messages.pnickname}</label>     
                            </div>
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
                            <input type="hidden" value="${ismerchant}" name="ismerchant" />
                            <div class="form-group row"><input type="submit" value="Regisztráció" class="btn btn-outline-success"></div>            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
