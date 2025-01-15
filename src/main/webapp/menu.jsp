<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp"/>

<div class="container mt-4">
    <!-- Encabezado -->
    <div class="text-center mb-4">
        <h3 class="fw-bold text-primary">${title}</h3>
        <p class="text-muted">Selecciona una opción del menú para continuar</p>
    </div>

    <!-- Lista de opciones -->
    <ul class="list-group shadow-sm">
        <li class="list-group-item list-group-item-primary fw-bold text-center">
            <i class="fas fa-bars me-2"></i> Menú de Opciones
        </li>
        <li class="list-group-item d-flex align-items-center">
            <i class="fas fa-users text-primary me-2"></i>
            <a href="${pageContext.request.contextPath}/usuarios" class="text-decoration-none text-dark">Usuarios Registrados</a>
        </li>
        <li class="list-group-item d-flex align-items-center">
            <i class="fas fa-box text-success me-2"></i>
            <a href="${pageContext.request.contextPath}/productos" class="text-decoration-none text-dark">Mostrar Productos HTML</a>
        </li>
        <li class="list-group-item d-flex align-items-center">
            <i class="fas fa-sign-in-alt text-info me-2"></i>
            <a href="${pageContext.request.contextPath}/login.html" class="text-decoration-none text-dark">Login</a>
        </li>
        <li class="list-group-item d-flex align-items-center">
            <i class="fas fa-sign-out-alt text-danger me-2"></i>
            <a href="${pageContext.request.contextPath}/logout" class="text-decoration-none text-dark">Logout</a>
        </li>
        <li class="list-group-item d-flex align-items-center">
            <i class="fas fa-shopping-cart text-warning me-2"></i>
            <a href="${pageContext.request.contextPath}/carro/ver" class="text-decoration-none text-dark">Ver Carro</a>
        </li>
    </ul>
</div>

<jsp:include page="layout/footer.jsp"/>
