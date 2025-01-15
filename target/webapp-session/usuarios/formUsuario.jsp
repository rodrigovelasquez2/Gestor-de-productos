<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.time.format.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/layout/header.jsp"/>

<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>${title}</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/usuarios/form" method="post">
                <!-- Username -->
                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="username">Username</label>
                    <div class="col-md-6">
                        <input type="text" name="username" id="username" value="${usuario.username}" class="form-control">
                        <c:if test="${errores != null && errores.containsKey('username')}">
                            <small class="text-danger">${errores.username}</small>
                        </c:if>
                    </div>
                </div>

                <!-- Password -->
                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="password">Password</label>
                    <div class="col-md-6">
                        <input type="password" name="password" id="password" class="form-control">
                        <c:if test="${errores != null && not empty errores.password}">
                            <small class="text-danger">${errores.password}</small>
                        </c:if>
                    </div>
                </div>

                <!-- Gmail -->
                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="gmail">Gmail</label>
                    <div class="col-md-6">
                        <input type="text" name="gmail" id="gmail" value="${usuario.gmail}" class="form-control">
                        <c:if test="${errores != null && not empty errores.gmail}">
                            <small class="text-danger">${errores.gmail}</small>
                        </c:if>
                    </div>
                </div>

                <!-- Submit Button -->
                <div class="text-center">
                    <input class="btn btn-primary" type="submit"
                           value="${usuario.id != null && usuario.id > 0 ? 'Editar' : 'Crear'}">
                </div>

                <!-- Hidden Field -->
                <input type="hidden" name="id" value="${usuario.id != null && usuario.id > 0 ? usuario.id : 0}">
            </form>
        </div>
    </div>
</div>

<jsp:include page="/layout/footer.jsp"/>
