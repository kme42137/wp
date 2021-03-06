<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='/webjars/bootstrap/4.1.3/css/bootstrap.min.css'>  
    <link rel="stylesheet" href="css/hihihaha.css">
</head>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top mb-8 shadow fixed-top">
    <div class="container">
        <span class="navbar-brand"><a href="index.jsp">Web Piac</a></span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">        
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Keresés
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="psearch">Termék</a>
                        <a class="dropdown-item" href="msearch">Eldó</a>                                                      
                    </div>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Látogatóknak
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="#">Fejlesztés alatt lévő funkciók</a>                           
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="<c:url value="login"><c:param name="ismerchant" value="${false}"/></c:url>">Bejelentkezés/regisztráció</a>                            
                                </div>
                            </li>     
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Termelőknek
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">                                
                                    <a class="dropdown-item" href="#">Fejlesztés alatt lévő funkciók</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="<c:url value="login"><c:param name="ismerchant" value="${true}"/></c:url>">Bejelentkezés/regisztráció</a>                            
                                </div>
                            </li>            
                    </c:when>
                    <c:otherwise>    
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Bevásárlólista
                            </a>
                            <c:if test="${sessionScope.cart!=null}">
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">     
                                    <c:forEach items="${sessionScope.cart}" var="pr">                                            
                                        <a class="dropdown-item" href="<c:url value="getmerchant"><c:param name="merchantid" value="${pr.value}"/></c:url>">${pr.key}</a>
                                    </c:forEach> 

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="cartdel">Lista törlése</a>

                                </div>
                            </c:if>
                        </li>
                        <c:choose>
                            <c:when test="${sessionScope.user.isMerchant}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${sessionScope.user.nickname}
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">                               
                                        <a class="dropdown-item" href="preg">Árucikk rögzítése</a>
                                        <a class="dropdown-item" href="mreg">Tremelői tevékenység leírása</a>                     
                                        <a class="dropdown-item" href="modreg">Személyes adatok módosítása</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="logout">Kijelentkezés</a>
                                    </div>
                                </li>
                            </c:when>
                            <c:otherwise> 
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${sessionScope.user.nickname}
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">                                  
                                        <a class="dropdown-item" href="modreg">Személyes adatok módosítása</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="logout">Kijelentkezés</a>
                                    </div>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>                                
            </ul>
        </div>
    </div>
</nav>

<script type="text/javascript" src="/webjars/jquery/2.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>