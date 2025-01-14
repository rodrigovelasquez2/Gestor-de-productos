<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 8/08/2024
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.time.format.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/layout/header.jsp"/>
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/usuarios/form" method="post">
    <div class="row mb-2">

        <label class="col-form-label col-sm-2" for="username">Username</label>
        <div class="col-sm-4">
            <input type="text" name="username" id="username" value="${usuario.username}" class="form-control">
        </div>

        <%--        Encontrar errores --%>
        <c:if test="${errores != null && errores.containsKey('username')}">
<%--            Muestro el error --%>
            <div style="color:red;">${errores.username}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="password">Password</label>
        <div class="col-sm-4">
            <input type="password" name="password" id="password" class="form-control">
        </div>

        <%--        Encontrar errores --%>
        <c:if test="${errores != null && not empty errores.password}">
            <div style="color:red;">${errores.password}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="gmail">gmail</label>
        <div class="col-sm-4">
            <input type="text" name="gmail" id="gmail" value="${usuario.gmail}" class="form-control">
        </div>

        <%--        Encontrar errores --%>
        <c:if test="${errores != null && not empty errores.gmail}">
            <div style="color:red;">${errores.gmail}</div>
        </c:if>
    </div>

    <div class="row mb-2">
<%--        Si el usuario existe, mostrara editar, si el usaurio es nuevo, mostrara crear--%>
        <input class="btn btn-primary" type="submit" value="${usuario.id != null && usuario.id > 0 ? 'Editar' : 'Crear'}">
    </div>
    <input type="hidden" name="id" value="${usuario.id!=null && usuario.id>0? usuario.id: 0}">
</form>
<jsp:include page="/layout/footer.jsp"/>

