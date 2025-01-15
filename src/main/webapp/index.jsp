<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">

</head>

<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
            <div class="mb-5">
                <h1 class="text-center mb-4">Inicia sesión</h1>
            </div>
            <div class="card border border-light-subtle rounded-4">
                <div class="card-body p-3 p-md-4 p-xl-5">
                    <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="row gy-3 overflow-hidden">
                            <div class="col-12">
                                <div class="form-floating mb-3">
                                    <input type="username" class="form-control" name="username" id="username" placeholder="username" required>
                                    <label for="Username" class="form-label">Username</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-floating mb-3">
                                    <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" required>
                                    <label for="password" class="form-label">Password</label>
                                </div>

                            </div>

                            <div class="col-12">
                                <div class="d-grid">
                                    <button class="btn btn-primary btn-lg" type="submit">Log in</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>

