<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container mt-4">
    <h2>Danh sách User</h2>
    <table class="table table-bordered" id="userTable">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <div id="errorBox" class="alert alert-danger d-none"></div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<script>
    $(function () {
        $.ajax({
            url: '<c:url value="/graphql"/>',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                query: "{ users { id fullname email phone } }"
            }),
            success: function (res) {
                if (res.errors) {
                    $("#errorBox").removeClass("d-none").text(res.errors[0].message);
                    return;
                }
                let rows = "";
                res.data.users.forEach(u => {
                    rows += `<tr>
                        <td>${u.id || ''}</td>
                        <td>${u.fullname || ''}</td>
                        <td>${u.email || ''}</td>
                        <td>${u.phone || ''}</td>
                    </tr>`;
                });
                $("#userTable tbody").html(rows);
            },
            error: function (xhr) {
                $("#errorBox").removeClass("d-none").text("AJAX lỗi: " + xhr.status);
            }
        });
    });
</script>
</body>
</html>
