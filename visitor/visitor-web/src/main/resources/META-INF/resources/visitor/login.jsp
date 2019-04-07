<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bejelentkezés</title>
    </head>
    <body>
        <jsp:include page="/support/menu.jsp"></jsp:include>
            <div class="col-md-6 offset-md-3">
                <form action="/login" method="post">                        
                    <div class="form-group row">
                        <label>Felhasználónév vagy e-mail cím</label>
                        <input name="username" class="form-control">
                    </div>
                    <div class="form-group row">
                        <label>Jelszó</label>
                        <input name="password" class="form-control">
                    </div>
                    <label>${error}</label>   
                <div class="form-group row"><input type="submit" value="Bejelentkezés" class="btn btn-light"></div>                
            </form>
            
        </div>
                <div class="col-md-6 offset-md-3">
                <a href="reg"><input type="submit" value="Regisztracio" class="btn btn-primary"></a>
            </div> 
    </body>
</html>
