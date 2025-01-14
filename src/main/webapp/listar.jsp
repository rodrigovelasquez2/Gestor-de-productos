<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Incluye la cabecera --%>
<jsp:include page="layout/header.jsp"/>
    <h3>${title}</h3>
    <c:if test="${requestScope.username.isPresent()}">
        <div class="alert alert-info">Hola <c:out value="${username.get()}"/> bienvenido!</div>
        <a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/productos/form">crear [+]</a>
    </c:if>
    <table class="table table-hover table-striped">
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

        <!--Se recorre toda la lista de los productos-->
        <!--(Producto p: productos)-->
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
                    <!-- <td><a href="=request.getContextPath()%>/carro/agregar?id=<=p.getId()>">agregar al carro</a></td> -->
                    <td><a class="btn btn-sm btn-primary"
                           href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">Agregar al carro</a></td>
                    <td><a class="btn btn-sm btn-success"
                           href="${pageContext.request.contextPath}/productos/form?id=${p.id}">editar</a></td>
                    <td><a class="btn btn-sm btn-danger" onclick="return confirm('esta seguro que desea eliminar?');"
                           href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}">eliminar</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <%--<p><%=mensajeApp%></p>--%>
    <p>${applicationScope.mensaje}</p>

    <%--<p><%=mensajeRequest%></p>--%>
    <p>${requestScope.mensaje}</p>

<jsp:include page="layout/footer.jsp"/>

