<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actividad 1</title>
<!-- CSS de Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/datepicker.css"
	rel="stylesheet">

<!-- librerías opcionales que activan el soporte de HTML5 para IE8 -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<c:set var="pasajero" value="${sessionScope['pasajero']}" />
<body>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a
				href="${pageContext.request.contextPath}/pasajero/gestion">Atr&aacute;s</a></li>
		</ul>
	</div>
	<h3>Introduce un Pasajero</h3>
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/pasajero/rellenado"
		method="post">
		<div class="form-group">
			<label for="nombre" class="col-sm-2 control-label">Nombre</label>
			<div class="col-sm-10">
				<input name="nombre" type="text" class="form-control" id="nombre"
					placeholder="Nombre">
			</div>
		</div>
		<div class="form-group">
			<label for="asiento" class="col-sm-2 control-label">Apellidos</label>
			<div class="col-sm-10">
				<input name="apellidos" type="text" class="form-control" id="apellidos"
					placeholder="Apellidos">
			</div>
		</div>
		<div class="form-group">
			<label for="fecha" class="col-sm-2 control-label">Fecha Nacimiento</label>
			<div class="col-sm-10">
				<input name="fecha" type="text" class="form-control" id="fecha"
					placeholder="Fecha: ej (04/09/2015)">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="hidden" name="pasajero" value="${pasajero.id}">
				<button type="submit" class="btn btn-default">Insertar
					Pasajero</button>
			</div>
		</div>
	</form>

	<!-- Librería jQuery requerida por los plugins de JavaScript -->
	<script src="http://code.jquery.com/jquery.js"></script>

	<!-- Todos los plugins JavaScript de Bootstrap (también puedes
         incluir archivos JavaScript individuales de los únicos
         plugins que utilices) -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}js/jquery-1.9.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}js/bootstrap-datepicker.js"></script>
	<script type="text/javascript">
		// When the document is ready
		$(document).ready(function() {

			$('#diahora').datepicker({
				format : "dd/mm/yyyy"
			});

		});
	</script>
</body>
</html>