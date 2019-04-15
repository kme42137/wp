<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='/webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
    <script type="text/javascript" src="/webjars/jquery/2.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <span class="navbar-brand"><a href="index.jsp">Web Piac</a></span>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Termeloknek
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Valami tuti</a>
                            <a class="dropdown-item" href="#">Valami más</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="login">Bejelentkezés/regisztráció</a>

                        </div>
                    </li>            
                </c:when>
                <c:otherwise>            
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${sessionScope.user.nickname}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Valami tuti</a>
                            <a class="dropdown-item" href="#">Valami más</a>                     
                            <a class="dropdown-item" href="">Termelői tevékenység</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="logout">Kijelentkezés</a>
                        </div>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>