
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
        <style>
            body { 
                background: url("images/product_img.jpg") no-repeat center center fixed; 
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
                <div class="col-md-10 offset-md-1">
                <div class="card border-0 shadow my-5">
                    <div class="card-body bg-light p-5">  
                        <h3>Képek feltöltése</h3>
                        <p>Három kép kiválasztására van lehetősége</p>
                        <form action="mimgupload" method="post" enctype="multipart/form-data">
                            <div class="form-row">
                                <div class="form-group col-md-7">
                                    <label>A lap tetején, a legnagyobb méretben megjelenő kép:</label>
                                    <input type="file" name="file1" class="form-control-file" />
                                    <label>${messages.file1}</label> 
                            </div> 

                            <div class="form-group col-md-4 offset-md-1">
                                <c:if test="${not empty savedimages[(1).intValue()].location}">              
                                    <img src="<c:out value="${savedimages[(1).intValue()].location}"/>" alt="Nagy kep" class="img-fluid">
                                </c:if>  
                            </div>
                        </div>      
                        <div class="form-row">
                            <div class="col-md-12"><hr></div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-7">
                                <label>A tömörebb leíráshoz tartozó kép:</label>            
                                <input type="file" name="file2" class="form-control-file" />  
                                <label>${messages.file2}</label> 
                            </div>
                            <div class="form-group col-md-4 offset-md-1">
                                <c:if test="${not empty savedimages[(2).intValue()].location}">              
                                    <img src="<c:out value="${savedimages[(2).intValue()].location}"/>" alt="Nagy kep" class="img-fluid">
                                </c:if> 
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12"><hr></div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-7">
                                <label>A bemutatkozó szöveg melletti kép</label>
                                <input type="file" name="file3" class="form-control-file" />
                                <label>${messages.file3}</label> 
                            </div>
                            <div class="form-group col-md-4 offset-md-1">
                                <c:if test="${not empty savedimages[(3).intValue()].location}">              
                                    <img src="<c:out value="${savedimages[(3).intValue()].location}"/>" alt="Nagy kep" class="img-fluid">
                                </c:if>  
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12"><hr></div>
                        </div>
                        <div class="form-row">    
                            <div><input type="submit" value="Jóváhagy" class="btn btn-outline-success"></div>                  
                        </div>
                    </form>
                    <div style="float: right;"><a href="mreg"><input type="submit" value="Vissza a szöveges bemutatkozás megadásához" class="btn btn-success"></a></div>
                </div>
            </div>
        </div>
                            </div>
    </body>
</html>
