<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actividad 1</title>
<!-- CSS de Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/datepicker.css" rel="stylesheet">

<!-- librerías opcionales que activan el soporte de HTML5 para IE8 -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/avion/insercion_mapear" method="post">
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
					id="hora_salida" placeholder="Hora de Salida">
			</div>
		</div>
		<div class="form-group">
			<label for="hora_llegada" class="col-sm-2 control-label">Hora
				Llegada</label>
			<div class="col-sm-10">
				<input name="hora_llegada" type="text" class="form-control"
					id="hora_llegada" placeholder="Hora de Llegada">
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
				<input name="estado_licencia" type="text" class="form-control"
					id="estado_licencia" placeholder="Estado licencia">
			</div>
		</div>
		<div class="form-group">
			<label for="estado_licencia" class="col-sm-2 control-label">Caducidad
				Licencia</label>
			<div class="row">
				<div class='col-sm-6'>
					<input name="caducidad_licencia" type='text' class="form-control"
						id='diahora' />
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Insertar
					Avi&oacute;n</button>
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
        <script src="${pageContext.request.contextPath}js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
            // When the document is ready
            $(document).ready(function () {
                
                $('#diahora').datepicker({
                    format: "dd/mm/yyyy"
                });  
            
            });
        </script>
</body>
</html>