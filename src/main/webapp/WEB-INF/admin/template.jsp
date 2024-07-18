<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>Home</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/dashboard/">

    <link href="../../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .b-example-divider {
            height: 3rem;
            background-color: rgba(0, 0, 0, .1);
            border: solid rgba(0, 0, 0, .15);
            border-width: 1px 0;
            box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
        }

        .b-example-vr {
            flex-shrink: 0;
            width: 1.5rem;
            height: 100vh;
        }

        .bi {
            vertical-align: -.125em;
            fill: currentColor;
        }

        .nav-scroller {
            position: relative;
            z-index: 2;
            height: 2.75rem;
            overflow-y: hidden;
        }

        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="<c:url value="../../assets/css/dashboard.css"/>" rel="stylesheet">
</head>
<body class="fs-6">
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="#">VNU - HCMUS</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
<%--    <input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" placeholder="Search" aria-label="Search">--%>
    <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="/logout">Đăng xuất</a>
        </div>
    </div>
</header>
<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="position-sticky pt-3 sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link ${contentPage.equals("index.jsp") ? "active" : ""}" aria-current="page" href="home">
                            <span data-feather="home" class="align-text-bottom"></span>
                            Trang chủ
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${contentPage.equals("account.jsp") ? "active" : ""}" href="/admin/account">
                            <span data-feather="align-justify" class="align-text-bottom"></span>
                            Quản lý tài khoản
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${contentPage.equals("subject.jsp") ? "active" : ""}" href="/admin/subject">
                            <span data-feather="book-open" class="align-text-bottom"></span>
                            Môn học
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${contentPage.equals("semester.jsp") ? "active" : ""}" href="/admin/semester">
                            <span data-feather="archive" class="align-text-bottom"></span>
                            Học kỳ
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${contentPage.equals("course.jsp") ? "active" : ""}" href="/admin/course">
                            <span data-feather="book" class="align-text-bottom"></span>
                            Học phần
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${contentPage.equals("class.jsp") || contentPage.equals("class-detail.jsp") ? "active" : ""}" href="/admin/class">
                            <span data-feather="package" class="align-text-bottom"></span>
                            Lớp học
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link ${contentPage.equals("me.jsp") ? "active" : ""}" href="/admin/me">
                            <span data-feather="user" class="align-text-bottom"></span>
                            Thông tin cá nhân
                        </a>
                    </li>
                </ul>

            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-${sessionScope.messageType}">${sessionScope.message}</div>
                <c:remove var="message" scope="session"/>
                <c:remove var="messageType" scope="session"/>
            </c:if>
            <jsp:include page="${contentPage}"></jsp:include>
        </main>
    </div>
</div>


<script src="../../assets/dist/js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script><script src="<c:url value="../../assets/js/dashboard.js"/>"></script>
</body>
</html>