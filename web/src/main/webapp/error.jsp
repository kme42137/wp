<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hiba</title>
    </head>
     <body>
         <jsp:include page="menu.jsp"></jsp:include> 
         <br>
        <div class="alert alert-danger top" role="alert">
            <h1 class="text-center">${message}</h1>
        </div>        
    </body>
</html>
