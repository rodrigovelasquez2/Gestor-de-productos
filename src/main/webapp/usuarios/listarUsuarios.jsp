<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/layout/header.jsp"/>
<h3>${title}</h3>
<c:if test="${username.present}">
    <a class="btn btn-lg  btn-outline-primary my-3" href="${pageContext.request.contextPath}/usuarios/form"><i class="fas fa-save"></i> Crear [+]</a>
    <br>
</c:if>

<table class="table table-hover table-striped table-bordered align-middle text-center">
    <thead class="table-dark">
    <tr>
        <th>Id</th>
        <th>Nombre de usuario</th>
        <th>Email</th>
        <c:if test="${username.present}">
            <th>Editar</th>
            <th>Eliminar</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usuarios}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.gmail}</td>
            <c:if test="${username.present}">
                <td>
                    <a class="btn btn-sm btn-outline-success"
                       href="${pageContext.request.contextPath}/usuarios/form?id=${u.id}">
                        <i class="fas fa-edit"></i> Editar
                    </a>
                </td>
                <td>
                    <a class="btn btn-sm btn-outline-danger"
                       onclick="return confirm('¿Está seguro que desea eliminar?');"
                       href="${pageContext.request.contextPath}/usuarios/eliminar?id=${u.id}">
                        <i class="fas fa-trash-alt"></i> Eliminar
                    </a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/layout/footer.jsp"/>
