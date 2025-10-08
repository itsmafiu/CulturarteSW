<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Perfil de ${usuario.nickname}</h2>
<img src="SvMostrarFoto?path=${usuario.imagen}" width="150" class="rounded-circle mb-3">
<p><strong>Nombre:</strong> ${usuario.nombre} ${usuario.apellido}</p>
<p><strong>Tipo:</strong> ${usuario.tipo}</p>
<c:if test="${usuario.tipo eq 'Proponente'}">
  <p><strong>Biografía:</strong> ${usuario.biografia}</p>
</c:if>
