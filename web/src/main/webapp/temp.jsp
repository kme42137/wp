<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bejelentkezés</title>
         <link rel="stylesheet" href="css/hihihaha.css">
    </head>
    <body class="body-bg">
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="container">
  <div class="card border-0 shadow my-5">
    <div class="card-body bg-light p-5">
      <div class="col-md-6 offset-md-3">
                <form action="login" method="post">                        
                    <div class="form-group row">
                        <label>Felhasználónév vagy e-mail cím</label>
                        <input name="username" class="form-control">
                    </div>
                    <div class="form-group row">
                        <label>Jelszó</label>
                        <input type="password" name="password" class="form-control"  placeholder="Password">
                    </div>
                    <label>${error}</label>   
                <div class="form-group row"><input type="submit" value="Bejelentkezés" class="btn btn-light"></div>            
            </form>
            <div style="float: right;">
                <a href="<c:url value="reg"><c:param name="ismerchant" value="${ismerchant}"/></c:url>"><input type="submit" value="Regisztracio" class="btn btn-success"></a>               
            </div>
        </div>
    </div>
  </div>
</div>
    </body>
</html>
