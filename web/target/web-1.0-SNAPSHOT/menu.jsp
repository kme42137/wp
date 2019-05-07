<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='/webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
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
        h11 {
            color: white;
            text-shadow: 1px 1px 2px orange, 0 0 25px yellow, 0 0 5px purple;
        }
        
        h12 {
            color: yellow;
            text-shadow: 1px 1px 2px orange, 0 0 25px black, 0 0 5px purple;
        }
    </style>

</head>

<body>
    

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">


            <h11><span class="navbar-brand"><a href="index.jsp">Web Piac</a></span></h11>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent" class="d-flex flex-column">
                <section class="d-flex f-grow-0 justify-content-between">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
                                <button class="button" style="vertical-align:middle"><span>
                                        Keresés
                                    </span></button>
                            </a>

                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="#"><button class="button2" style="vertical-align:middle"><span>Termékek</span></button></a>
                                <a class="dropdown-item" href="msearch"><button class="button2" style="vertical-align:middle"><span>Eladók</span></button></a>

                            </div>
                        </li>  
                        <c:choose>
                            <c:when test="${sessionScope.user == null}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <button class="button" style="vertical-align:middle"><span> Látogatóknak</span></button>
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                        <h11><a class="dropdown-item" href="#">Fejlesztés alatt lévő funkciók</a></h11>                           
                                        <div class="dropdown-divider"></div>
                                        <a href="<c:url value="login"><c:param name="ismerchant" value="${false}"/></c:url>"><button class="button2" style="vertical-align:middle"><span>Bejelentkezés/regisztráció</span></button></a>                            
                                        </div>
                                    </li>     
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <button class="button" style="vertical-align:middle"><span>Termelőknek</span></button>
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">                                
                                            <h11><a class="dropdown-item" href="#">Fejlesztés alatt lévő funkciók</a></h11>

                                            <div class="dropdown-divider"></div>
                                            <a href="<c:url value="login"><c:param name="ismerchant" value="${true}"/></c:url>"><button class="button2" style="vertical-align:middle"><span>Bejelentkezés/regisztráció</span></button></a>                            
                                        </div>
                                    </li>
                            </c:when>

                            <c:otherwise>    
                                <c:choose>
                                    <c:when test="${sessionScope.user.isMerchant}">
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                ${sessionScope.user.nickname}
                                            </a>
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">                               
                                                <a class="dropdown-item" href="preg"><button class="button2" style="vertical-align:middle"><span>Árucikk rögzítése</span></button></a>
                                                <a class="dropdown-item" href="mreg"><button class="button2" style="vertical-align:middle"><span>Tremelői tevékenység leírása</span></button></a>                     
                                                <a class="dropdown-item" href="modreg"><button class="button2" style="vertical-align:middle"><span>Személyes adatok módosítása</span></button></a>
                                                <div class="dropdown-divider"></div>
                                                <a class="dropdown-item" href="logout"><button class="button2" style="vertical-align:middle"><span>Kijelentkezés</span></button></a>
                                            </div>
                                        </li>
                                    </c:when>
                                    <c:otherwise> 
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                ${sessionScope.user.nickname}
                                            </a>
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">                                  
                                                <a class="dropdown-item" href="modreg"><button class="button2" style="vertical-align:middle"><span>Személyes adatok módosítása</span></button></a>
                                                <div class="dropdown-divider"></div>
                                                <a class="dropdown-item" href="logout"><button class="button2" style="vertical-align:middle"><span>Kijelentkezés</span></button></a>
                                            </div>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </section>
            </div>

        </nav>

    
</body>
<script type="text/javascript" src="/webjars/jquery/2.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>