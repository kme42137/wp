
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>Web piac</title>
        <style>
            header {	
                background-color: #444;
                background: url("images/buzamezo.jpg") no-repeat center top;
                padding-top: 150px;
                background-attachment: relative;
                background-position: center center;
                min-height: 650px;
                width: 100%;

                -webkit-background-size: 100%;
                -moz-background-size: 100%;
                -o-background-size: 100%;
                background-size: 100%;

                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }



            header,h1 {
                margin-top: 50px;
                margin-bottom: 15px;
                color: #fff;                
            }
        </style>
    </head>
    <body>

        <jsp:include page="menu.jsp"></jsp:include>

        <header>
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <h1 class="font-weight-light">Web Piac</h1>
                        <h2 class="lead">Ha tudni szeretned, mit kapni a kornyeken</h2>
                    </div>
                </div>
            </div>
        </header>

    </body>
</html>
