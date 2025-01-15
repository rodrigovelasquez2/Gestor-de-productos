<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Incluye la cabecera --%>
<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:if test="${requestScope.username.isPresent()}">
    <div class="alert alert-info">Hola <c:out value="${username.get()}"/> bienvenido!</div>
    <a class="btn btn-lg  btn-outline-primary my-3" href="${pageContext.request.contextPath}/productos/form"><i
            class="fas fa-save"></i> Crear [+]</a>

</c:if>
<table class="table table-hover table-striped table-bordered align-middle text-center">
    <thead class="table-dark">
    <tr>
        <th>id</th>
        <th>nombre</th>
        <th>tipo</th>
        <c:if test="${username.present}">
            <th>precio</th>
            <th>agregar</th>
            <th>editar</th>
            <th>eliminar</th>
        </c:if>
    </tr>
    </thead>


    <!--Se recorre toda la lista de los productos-->
    <!--(Producto p: productos)-->
    <tbody>
    <c:forEach items="${productos}" var="p">
        <tr>
            <!--p.getId()--->
            <td>${p.id}</td>
            <!--p.getNombre()--->
            <td>${p.nombre}</td>
            <!--p.getCategoria().getNombre()--->
            <td>${p.categoria.nombre}</td>
            <!--if(username.isPresent()--->
            <c:if test="${username.isPresent()}">
                <td>${p.precio}</td>
                <td>
                    <a class="btn btn-sm btn-outline-warning"
                       href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}"><i class="fas fa-edit"></i> Agregar al carro</a>
                </td>
                <td>
                    <a class="btn btn-sm btn-outline-success"
                       href="${pageContext.request.contextPath}/productos/form?id=${p.id}"><i class="fas fa-edit"></i>Editar</a>
                </td>
                <td>
                    <a class="btn btn-sm btn-outline-danger"
                       onclick="return confirm('esta seguro que desea eliminar?');"
                       href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}"><i class="fas fa-trash-alt"></i>Eliminar</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>

</table>

<jsp:include page="layout/footer.jsp"/>

