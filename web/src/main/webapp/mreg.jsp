
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
                <form action="mreg" method="post">
                    <div class="form-group">
                        <label>A vállalkozásának neve vagy az a megnevezés, ahogy szeretne megjelenni az oldalon</label>
                        <input name="pnametodisplay" class="form-control" value="${userinput.nameToDisplay}">
                    <label>${messages.pnametodisplay}</label>  
                </div>
                <div class="form-group">
                    <label>Tömör leírása a tevékenységének, termékeinek</label>
                    <textarea class="form-control" name="pintroduction" rows="3">${userinput.introduction}</textarea>
                    <label>${messages.pintroduction}</label> 
                </div>           
                <div class="form-group">
                    <label>Hosszabb bemutatkozó szöveg</label>
                    <textarea class="form-control" name="pdescription" rows="6">${userinput.description}</textarea>
                    <label>${messages.pdescription}</label> 
                </div> 
                <label>Városok, ahol Ön elérhető:</label>
                <div class="form-group"> 
                    <div class="row">
                        <div class="col">
                            <select class="form-control" name="town1">
                                <option disabled selected value> -- válasszon -- </option>
                                <c:forEach items="${townList}" var="town">                                                                
                                    <option value="${town.id}">${town.name}</option>                                              
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <select class="form-control" name="town2">
                                <option disabled selected value> -- válasszon -- </option>
                                <c:forEach items="${townList}" var="town">                                                                
                                    <option value="${town.id}">${town.name}</option>                                              
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <select class="form-control" name="town3">
                                <option disabled selected value> -- válasszon -- </option>
                                <c:forEach items="${townList}" var="town">                                                                
                                    <option value="${town.id}">${town.name}</option>                                              
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <select class="form-control" name="town4">
                                <option disabled selected value> -- válasszon -- </option>
                                <c:forEach items="${townList}" var="town">                                                                
                                    <option value="${town.id}">${town.name}</option>                                              
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <label>${messages.town}</label>
                </div>      
                <div class="form-group row"><input type="submit" value="Jóváhagyás" class="btn btn-light"></div>
            </form> 
                <h3>Képek feltöltése</h3>
                <p>Három kép kiválasztására van lehetősége</p>
            <div class="form-group">
                <label>A lap tetjén, a legnagyobb méretben megjelenő kép</label>
                <form action="imgupload" method="post" enctype="multipart/form-data">
                    Válassza ki a feltölteni kívánt képet: <input type="file" name="file" size="60" /><br />
                    <input type="hidden" value="1" name="type" />
                    <br /> <input type="submit" value="Feltölt" />
                </form>
            </div>
                <div class="form-group">
                <label>A tömörebb leíráshoz tartozó kép</label>
                <form action="imgupload" method="post" enctype="multipart/form-data">
                    Válassza ki a feltölteni kívánt képet: <input type="file" name="file" size="60" /><br />
                    <input type="hidden" value="2" name="type" />
                    <br /> <input type="submit" value="Feltölt" />
                </form>
            </div>
                <div class="form-group">
                <label>A bemutatkozó szöveg melletti kép</label>
                <form action="imgupload" method="post" enctype="multipart/form-data">
                    Válassza ki a feltölteni kívánt képet: <input type="file" name="file" size="60" /><br />
                    <input type="hidden" value="3" name="type" />
                    <br /> <input type="submit" value="Feltölt" />
                </form>
            </div>
        </div>
    </body>
</html>
