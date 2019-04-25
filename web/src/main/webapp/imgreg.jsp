
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regisztráció</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="col-md-6 offset-md-3">
                <h3>Képek feltöltése</h3>
                <p>Három kép kiválasztására van lehetősége</p>
                <form action="mimgupload" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>A lap tetjén, a legnagyobb méretben megjelenő kép:</label>
                        <input type="file" name="file1" class="form-control-file" />
                        <label>${messages.file1}</label> 
                </div>      
                <c:if test="${not empty savedimages[(1).intValue()].location}">              
                    <img src="<c:out value="${savedimages[(1).intValue()].location}"/>" alt="Nagy kep" class="img-fluid">
                </c:if>  
                <div class="form-group">
                    <label>A tömörebb leíráshoz tartozó kép:</label>            
                    <input type="file" name="file2" class="form-control-file" />  
                    <label>${messages.file2}</label> 
                </div>
                <c:if test="${not empty savedimages[(2).intValue()].location}">              
                    <img src="<c:out value="${savedimages[(2).intValue()].location}"/>" alt="Nagy kep" class="img-fluid">
                </c:if> 
                <div class="form-group">
                    <label>A bemutatkozó szöveg melletti kép</label>
                    <input type="file" name="file3" class="form-control-file" />
                    <label>${messages.file3}</label> 
                </div>
                <c:if test="${not empty savedimages[(3).intValue()].location}">              
                    <img src="<c:out value="${savedimages[(3).intValue()].location}"/>" alt="Nagy kep" class="img-fluid">
                </c:if>              
                <div><input type="submit" value="Jóváhagy" class="btn btn-light"></div>                  
            </form>
            <div style="float: right;"><a href="mreg"><input type="submit" value="Vissza a szöveges bemutatkozás megadásához" class="btn btn-success"></a></div>
        </div>
    </body>
</html>
