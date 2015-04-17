<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.io.*,java.util.*,java.util.List,java.sql.*,com.innova4b.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actividad 1</title>
<!-- Bootstrap core JS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath}">Atr&aacute;s</a></li>
		</ul>
	</div>
	<form class="form-horizontal" role="form"
		action="${pageContext.request.contextPath}/avion/insercion_mapear"
		method="post">
		<div class="form-group">
			<label for="modelo" class="col-sm-2 control-label">Modelo</label>
			<div class="col-sm-10">
				<input name="modelo" type="text" class="form-control" id="modelo"
					placeholder="Modelo">
			</div>
		</div>
		<div class="form-group">
			<label for="max_pasajeros" class="col-sm-2 control-label">M&aacute;ximo
				Pasajeros</label>
			<div class="col-sm-10">
				<input name="max_pasajeros" type="text" class="form-control"
					id="max_pasajeros" placeholder="Máximo número pasajeros">
			</div>
		</div>
		<div class="form-group">
			<label for="hora_salida" class="col-sm-2 control-label">Hora
				Salida</label>
			<div class="col-sm-10">
				<input name="hora_salida" type="text" class="form-control"
					id="hora_salida" placeholder="Hora de Salida: ej(02/03/2015 08:45)">
			</div>
		</div>
		<div class="form-group">
			<label for="hora_llegada" class="col-sm-2 control-label">Hora
				Llegada</label>
			<div class="col-sm-10">
				<input name="hora_llegada" type="text" class="form-control"
					id="hora_llegada"
					placeholder="Hora de Llegada: ej(02/03/2015 08:45)">
			</div>
		</div>
		<div class="form-group">
			<label for="codgo_licencia" class="col-sm-2 control-label">C&oacute;digo
				Licencia</label>
			<div class="col-sm-10">
				<input name="codgo_licencia" type="text" class="form-control"
					id="codgo_licencia" placeholder="Código licencia">
			</div>
		</div>
		<div class="form-group">
			<label for="estado_licencia" class="col-sm-2 control-label">Estado
				Licencia</label>
			<div class="col-sm-10">
				<select id="estado_licencia" name="estado_licencia"
					class="form-control">
						<option value="1">Activo</option>
						<option value="0">Caducado</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="compania" class="col-sm-2 control-label">Compania</label>
			<div class="col-sm-10">
				<c:set var="companias" value="${sessionScope['listaCompanias']}" />
				<select id="listado_de_companias" name="compania"
					class="form-control">
					<c:forEach var="comp" items="${companias}">
						<option value="${comp}">${comp}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="estado_licencia" class="col-sm-2 control-label">Caducidad
				Licencia</label>
			<div class="col-sm-10">
				<input name="caducidad_licencia" type="text" class="form-control"
					id="caducidad_licencia"
					placeholder="Caducidad Licencia: ej(02/03/2015 08:45)">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="hidden" name="pasajero" value="${pasajero.id}">
				<button type="submit" class="btn btn-default">Insertar
					Avión</button>
			</div>
		</div>
	</form>

	<!-- Librería jQuery requerida por los plugins de JavaScript -->
	<script src="http://code.jquery.com/jquery.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/locales/bootstrap-datetimepicker.fr.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			//language:  'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		});
		$('.form_date').datetimepicker({
			language : 'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});
		$('.form_time').datetimepicker({
			language : 'fr',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0
		});
	</script>
</body>
</html>