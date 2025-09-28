<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<div class="container mt-4">
    <h2 class="mb-3">Danh sách User</h2>

    <!-- Bảng hiển thị User -->
    <table class="table table-bordered table-striped" id="userTable">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Phone</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td colspan="4" class="text-center">Đang tải dữ liệu...</td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="fragments/footer.jsp"/>

<script>
    $(function () {
        $.ajax({
            url: '/graphql',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                query: "{ users { id fullname email phone } }"
            }),
            success: function (res) {
                console.log("GraphQL response:", res);
                let rows = "";

                if (res.data && res.data.users && res.data.users.length > 0) {
                    res.data.users.forEach(u => {
                        rows += `<tr>
                                    <td>\${u.id}</td>
                                    <td>\${u.fullname || ""}</td>
                                    <td>\${u.email || ""}</td>
                                    <td>\${u.phone || ""}</td>
                                 </tr>`;
                    });
                } else {
                    rows = "<tr><td colspan='4' class='text-center'>Không có dữ liệu</td></tr>";
                }

                $("#userTable tbody").html(rows);
            },
            error: function (xhr) {
                console.error("GraphQL error:", xhr);
                $("#userTable tbody").html(
                    "<tr><td colspan='4' class='text-center text-danger'>Lỗi tải dữ liệu</td></tr>"
                );
            }
        });
    });
</script>
</body>
</html>
