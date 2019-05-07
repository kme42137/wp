
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
          <style>
                 @import url('https://fonts.googleapis.com/css?family=Acme|Open+Sans');
                 
                 body{
            font-family:  'Georgia', serif;
            height: 100%;
            min-height: 100%;
        }

        html { 
            background: url(images/veggieback1.jpg) no-repeat center fixed; 
            background-size: cover;
        }

        .button {
            display: inline-block;
            border-radius: 6px;
            background-color: #f4511e;
            border: none;
            color: #FFFFFF;
            text-align: center;
            font-size: 14px;
            padding: 10px;
            width: 180px;
            transition: all 0.5s;
            cursor: pointer;
            margin: 5px;
        }

        .button2 {
            display: inline-block;
            border-radius: 6px;
            background-color: #FACB01;
            border: none;
            color: #FFFFFF;
            text-align: center;
            font-size: 14px;
            padding: 10px;
            width: 180px;
            transition: all 0.5s;
            cursor: pointer;
            margin: 5px;
        }

        .button span {
            cursor: pointer;
            display: inline-block;
            position: relative;
            transition: 0.5s;
        }

        .button2 span {
            cursor: pointer;
            display: inline-block;
            position: relative;
            transition: 0.5s;
        }

        .button span:after {
            content: '\00bb';
            position: absolute;
            opacity: 0;
            top: 0;
            right: -20px;
            transition: 0.5s;
        }

        .button2 span:after {
            content: '\00bb';
            position: absolute;
            opacity: 0;
            top: 0;
            right: -20px;
            transition: 0.5s;
        }

        .button:hover span {
            padding-right: 25px;
        }

        .button2:hover span {
            padding-right: 25px;
        }

        .button:hover span:after {
            opacity: 1;
            right: 0;
        }

        .button2:hover span:after {
            opacity: 1;
            right: 0;
        }
        h1 {
            color: white;
            text-shadow: 1px 1px 2px orange, 0 0 25px yellow, 0 0 5px purple;
        }
        h12 {
            color: yellow;
            text-shadow: 1px 1px 2px orange, 0 0 25px black, 0 0 5px purple;
        }
                 
    </style>
        <title>Keresés az eladók között</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>            
            <div class="container">
                <form action="psearch" method="post">                                                             
                    <div class="form-group"> 
                        <div class="row">
                            <div class="col">                                                               
                                <select class="form-control" name="ptype">
                                    <option  disabled selected value> -- termék típus választás -- </option>
                                <c:forEach items="${types}" var="t">                                                                
                                    <option value="${t.key}">${t.value}</option>                                                                                 
                                </c:forEach>                                
                            </select>                                                                
                        </div>
                        <div class="col">  
                            <input name="qstring" class="form-control" placeholder="Keresett szöveg...">
                        </div>
                        <div class="col"><input type="submit" value="Keresés" class="btn btn-light"></div>
                    </div>
                </div>                                   
            </form>
            <c:choose>            
                <c:when test="${not empty rslist}">
                    <h12><h3>Találati lista:</h3></h12>
                    <c:forEach items="${rslist}" var="product">
                        <div class="row">
                            <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                                <a href="<c:url value="getmerchant"><c:param name="merchantid" value="${product.contactId}"/></c:url>"><h12><h4>termelo</h4></h12></a>
                                <p>${merchant.introduction}</p>    
                            </div>
                            <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
                                <img class="layout-iamge" src="<c:out value="${images[merchant.id]}"/>" alt="">
                            </div>
                        </div>
                    </c:forEach>            
                </c:when>
                <c:otherwise>
                    <h3>${message}</h3>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
