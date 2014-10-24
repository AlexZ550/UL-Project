<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> 
				<div class="nav-collapse collapse">
					<ul class="nav">
                                            <li class="active" id="home-link"><a href="<c:url value='/' />">Home</a></li>
                                            <% if (request.getUserPrincipal() != null) { %>
                                                <li data-access-level="admin"><a href="<c:url value='/#/userlog' />">Log</a></li>
                                            <% } %>
                                            <li><a href="<c:url value='/about.jsp' />">About</a></li>
                                            <li><a href="mailto:13199358@studentmail.ul.ie">Contact</a></li>
                                            <% if (request.getUserPrincipal() != null) { %>
                                                <li><a href="<c:url value='/#/cart' />"><%= request.getUserPrincipal().getName() %></a></li>
                                                <li><a href="<c:url value='LogOff' />">Log Off</a></li>
                                            <% } %>
                                        </ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
                                
