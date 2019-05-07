
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
        <h12><title>Arucikkek rogzitese</title></h12>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="col-md-6 offset-md-3">
                <form action="preg" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <h12><label>Az újonnan felvitt árucikk neve</label></h12>
                        <input name="pname" class="form-control" value="${userinput.name}">
                    <label>${messages.pname}</label>  
                </div>                        
                <div class="form-group">
                    <h12><label>Termékleírás</label></h12>
                    <textarea class="form-control" name="pdescription" rows="4">${userinput.description}</textarea>
                    <label>${messages.pdescription}</label> 
                </div>       
                <div class="form-group">
                    <h12><label>Termékcsoport</label></h12>   
                    <select class="form-control" name="ptype">
                        <option  disabled selected value> -- válasszon -- </option>
                        <c:forEach items="${types}" var="t">                                                                
                            <option value="${t.key}">${t.value}</option>                                                                                 
                        </c:forEach>                                
                    </select>
                    <label>${messages.ptype}</label> 
                </div>                                   
                <div class="form-group">
                    <h12><label>Kép az árucikkhez</label></h12>
                    <input type="file" name="img" class="form-control-file" />
                    <label>${messages.img}</label> 
                </div>   
                <div class="form-group row"><input type="submit" value="Jóváhagyás" class="btn btn-light"></div> 
            </form>
        </div>  
    </body>
</html>
