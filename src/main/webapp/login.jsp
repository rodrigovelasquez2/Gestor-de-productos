<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="layout/header.jsp"/>
<h3 class="text-center">${title}</h3>
<form action="${pageContext.request.contextPath}/login" method="post" style="max-width: 400px; margin: auto;">
    <div class="my-2 text-center">
        <label class="form-label" for="username">Username</label>
        <input class="form-control" type="text" name="username" id="username">
    </div>
    <div class="my-2 text-center">
        <label class="form-label" for="password">Password</label>
        <input class="form-control" type="password" name="password" id="password">
    </div>
    <div class="my-2 text-center">
        <input class="btn btn-primary" type="submit" value="Login">
    </div>
</form>

<jsp:include page="layout/footer.jsp"/>
