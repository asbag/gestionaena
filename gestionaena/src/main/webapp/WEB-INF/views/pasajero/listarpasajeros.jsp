<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*,java.util.*,java.util.List,java.sql.*,com.innova4b.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS de Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">

<!-- librerías opcionales que activan el soporte de HTML5 para IE8 -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<script type="text/javascript">
		function borrar(param){
			document.getElementById('seleccionado').value = param;
		}
		function asignar(param){
			document.getElementById('seleccionado').value = param;
		}
	</script>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}">Atr&aacute;s</a></li>
		</ul>
	</div>

	<c:set var="pasajeros" value="${sessionScope['listPasajeros']}" />
	<table border="1">
		<thead>
			<tr>
				<th>Nombre:</th>
			</tr>
		</thead>
		<tbody>
			<form class="form-horizontal" role="form" name="pasajero_form"
				action="${pageContext.request.contextPath}/pasajero/accion"
				method="post">
				<c:forEach var="e" items="${pasajeros}">
					<tr>
						<td>
							<div class="form-group">
								<label for="id_pasajero" class="col-sm-2 control-label">${e.nombre}
									${e.apellidos}</label>
								<div class="col-sm-10">
									<input name="id_pasajero" value="${e.id}" type="hidden"
										class="form-control" id="id_pasajero">
								</div>
						</td>
						<td>
							<div class="col-sm-10">
								<button type="submit" name="act" value="borrar"
									class="btn btn-primary" onclick="borrar(${e.id})">Borrar</button>
							</div>
						</td>
						<td>
							<div class="col-sm-10">
								<input name="seleccionado" value="" type="hidden"
									class="form-control" id="seleccionado">
								<button type="submit" name="act" value="asignar"
									class="btn btn-primary" onclick="asignar(${e.id})">Asignar
									Billete</button>
							</div>
						</td>
						</div>
				</c:forEach>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" name="act" value="insertar"
									class="btn btn-success">Insertar Pasajero</button>
							</div>
						</div>
					</td>
				</tr>
			</form>
		</tbody>
	</table>
	<!-- Librería jQuery requerida por los plugins de JavaScript -->
	<script src="http://code.jquery.com/jquery.js"></script>

	<!-- Todos los plugins JavaScript de Bootstrap (también puedes
         incluir archivos JavaScript individuales de los únicos
         plugins que utilices) -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.js"></script>
</body>
</html>