
<%@page import="wpdemo.visitor.dao.model.Visitor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>Web piac</title>
        <style>
            header {	
                background-color: #444;
                background: url("images/index_img2.jpg") no-repeat center top;
                padding-top: 150px;
                background-attachment: relative;
                background-position: center center;
                height: 700px;
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
            <div class="container p-5">
                <div class="row pt-5">
                    <div class="col-md-12 text-right pt-5">
                        <h1>Ha tudni szeretnéd, mit kapni a kornyéken <br>vagy eladnád a portékádat, akkor jó helyen jársz.</h1>
                        <h2 class="lead pt-5">A honlap jelenleg fejlesztés alatt áll, így csak a félkész állapot tekinthető meg.</h2>
                    </div>
                </div>
            </div>
        </header>         
    </body>
</html>
