<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container mt-4">
    <h2>Danh sách Category</h2>
    <table class="table table-bordered" id="categoryTable">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Images</th>
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
            url: '/graphql',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                query: "{ categories { id name images } }"
            }),
            success: function (res) {
                console.log("GraphQL response:", res); // debug ở console F12

                if (res.errors) {
                    $("#errorBox").removeClass("d-none").text(res.errors[0].message);
                    return;
                }

                if (!res.data || !res.data.categories) {
                    $("#errorBox").removeClass("d-none").text("Không có dữ liệu categories.");
                    return;
                }

                let rows = "";
                res.data.categories.forEach(function(c) {
                    rows += "<tr>" +
                        "<td>" + (c.id ?? '') + "</td>" +
                        "<td>" + (c.name ?? '') + "</td>" +
                        "<td>" + (c.images ?? '') + "</td>" +
                        "</tr>";
                });
                $("#categoryTable tbody").html(rows);
            },
            error: function (xhr) {
                $("#errorBox").removeClass("d-none").text("AJAX lỗi: " + xhr.status);
            }
        });
    });
</script>
</body>
</html>
