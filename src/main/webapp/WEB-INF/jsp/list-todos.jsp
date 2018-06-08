<%@include file="common/header.jsp" %>
<%@include file="common/navigation.jsp" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Your todos are</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}"/> </td>
						<td>${todo.done}</td>
						<td><a type="button" href="/update-todo?id=${todo.id}" class="btn btn-success">Update</a></td>
						<td><a type="button" href="/delete-todo?id=${todo.id}" class="btn btn-warning">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
		<br>
		<a href="/add-todo" class="button">Add a todo</a>
	</div>
<%@include file="common/footer.jsp" %>