<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
Map<String,String> errores = (Map<String, String>)request.getAttribute("errores");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de Productos</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h3>Formalario de Productos</h3>

<%
if(errores != null && errores.size() > 0) {
 %>
 <ul class="alert alert-danger mx-5 px-5">
 <% for(String error: errores.values()){%>
 <li><%=error%></li>
 <%}%>
 </ul>
<%}%>
<div class="px-5">
<form action="/form-product/crear" method="post">

    <div class="row mb-3">
        <label for="nombre" class="col-form-label col-sm-2">Producto</label>
        <div class="col-sm-4">
            <input type="text" name="nombre" id="nombre" class="form-control" value="${param.nombre}">
        </div>
    </div>

    <div class="row mb-3">
        <label for="precio" class="col-form-label col-sm-2">Pecio</label>
        <div class="col-sm-4">
            <input type="number" name="precio" id="precio" class="form-control">
        </div>
    </div>

    <div class="row mb-3">
        <label for="fabricante" class="col-form-label col-sm-2">Fabricante</label>
        <div class="col-sm-4">
            <input type="text" name="fabricante" id="fabricante" class="form-control" value="${param.fabricante}">
        </div>
    </div>

       <div class="row mb-3">
          <label for="categoria" class="col-form-label col-sm-2">Categoria</label>
         <div class="col-sm-4">
            <select name="categoria" id="categoria">
              <option value="">-- seleccionar --</option>
              <option value="1">Deporte</option>
              <option value="2">Computacion</option>
              <option value="3">electrodomesticos</option>
              <option value="4">Linea Blanca</option>
              <option value="5">Ropa</option>
              <option value="6">Tecnologia</option>
             </select>
         </div>
           <%
             if(errores != null && errores.containsKey("categoria")){
               out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>"+ errores.get("categoria") + "</small>");
             }
           %>
       </div>
        <div>
            <div>
                <input type="submit" value="Enviar">
            </div>
        </div>
    </div>
</form>
</div>
</body>
</html>