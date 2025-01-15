<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>
<h3>${title}</h3>
<c:choose>
    <c:when test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}">
        <div class="alert alert-warning">Lo sentimos no hay productos en el carro de compras!</div>
    </c:when>
    <c:otherwise>
        <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
            <table class="table table-hover table-striped table-bordered align-middle text-center">
                <thead class="table-dark">

                <tr>
                    <th>Id</th>
                    <th>Nombre del producto</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Total</th>
                    <th>Borrar</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${sessionScope.carro.items}" var="item">
                    <tr>

                        <td>${item.producto.id}</td>
                        <td>${item.producto.nombre}</td>
                        <td>${item.producto.precio}</td>

                        <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}"/>
                        </td>
                        <td>${item.importe}</td>
                        <td><input class="form-check-input" type="checkbox" value="${item.producto.id}" name="deleteProductos"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5" style="text-align: right">Total:</td>
                    <td>${sessionScope.carro.total}</td>
                </tr>
                </tbody>
            </table>
            <a class="btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a>
        </form>
    </c:otherwise>
</c:choose>

<div class="my-2">
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/menu.jsp">volver</a>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/productos">seguir comprando</a>
</div>
<jsp:include page="layout/footer.jsp"/>

