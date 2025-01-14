<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>401 Unauthorized</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
            color: #343a40;
            text-align: center;
        }
        .error-container {
            max-width: 400px;
        }
        h1 {
            font-size: 100px;
            margin-bottom: 20px;
            color: #dc3545;
        }
        p {
            margin-bottom: 30px;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>401</h1>
    <p>¡No autorizado! Lo sentimos, no tienes permisos para acceder a esta página.</p>
    <a href="${pageContext.request.contextPath}/login">Ir al Login</a>
</div>
</body>
</html>
