<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"/>

<div class="container mt-5">
    <div class="card">
        <div class="card-header">
            <h3>${title}</h3>
        </div>
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/productos/form" method="post">
                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="nombre">Nombre</label>
                    <div class="col-md-6">
                        <input type="text" name="nombre" id="nombre" value="${producto.nombre}" class="form-control">
                        <c:if test="${errores != null && not empty errores.nombre}">
                            <small class="text-danger">${errores.nombre}</small>
                        </c:if>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="precio">Precio</label>
                    <div class="col-md-6">
                        <input type="number" name="precio" id="precio" value="${producto.precio > 0 ? producto.precio : ''}" class="form-control">
                        <c:if test="${errores != null && not empty errores.precio}">
                            <small class="text-danger">${errores.precio}</small>
                        </c:if>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="sku">Sku</label>
                    <div class="col-md-6">
                        <input type="text" name="sku" id="sku" value="${producto.sku}" class="form-control">
                        <c:if test="${errores != null && not empty errores.sku}">
                            <small class="text-danger">${errores.sku}</small>
                        </c:if>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="fecha_registro">Fecha Registro</label>
                    <div class="col-md-6">
                        <input type="date" name="fecha_registro" id="fecha_registro"
                               value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern('yyyy-MM-dd')) : ''}"
                               class="form-control">
                        <c:if test="${errores != null && not empty errores.fecha_registro}">
                            <small class="text-danger">${errores.fecha_registro}</small>
                        </c:if>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-form-label col-md-3" for="categoria">Categoria</label>
                    <div class="col-md-6">
                        <select class="form-select" name="categoria" id="categoria">
                            <option value="">--- seleccionar ---</option>
                            <c:forEach items="${categorias}" var="c">
                                <option value="${c.id}" ${c.id == producto.categoria.id ? 'selected' : ''}>${c.nombre}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${errores != null && not empty errores.categoria}">
                            <small class="text-danger">${errores.categoria}</small>
                        </c:if>
                    </div>
                </div>

                <div class="text-center">
                    <input type="submit" class="btn btn-primary"
                           value="${producto.id != null && producto.id > 0 ? 'Editar' : 'Crear'}">
                </div>

                <input type="hidden" name="id" value="${producto.id}">
            </form>
        </div>
    </div>
</div>

<jsp:include page="layout/footer.jsp"/>
