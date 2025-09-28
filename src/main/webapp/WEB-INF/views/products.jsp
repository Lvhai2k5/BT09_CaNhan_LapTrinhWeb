<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container mt-4">
    <h2>Danh sách sản phẩm</h2>
    <table class="table table-bordered" id="productTable">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Số lượng</th>
            <th>Mô tả</th>
            <th>Giá</th>
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
                query: "{ products { id title quantity description price } }"
            }),
            success: function (res) {
                console.log("GraphQL response:", res); // Debug console F12

                if (res.errors) {
                    $("#errorBox").removeClass("d-none").text(res.errors[0].message);
                    return;
                }

                if (!res.data || !res.data.products) {
                    $("#errorBox").removeClass("d-none").text("Không có dữ liệu sản phẩm.");
                    return;
                }

                let rows = "";
                res.data.products.forEach(function(p) {
                    rows += "<tr>" +
                        "<td>" + (p.id ?? '') + "</td>" +
                        "<td>" + (p.title ?? '') + "</td>" +
                        "<td>" + (p.quantity ?? '') + "</td>" +
                        "<td>" + (p.description ?? '') + "</td>" +
                        "<td>" + (p.price ?? '') + "</td>" +
                        "</tr>";
                });
                $("#productTable tbody").html(rows);
            },
            error: function (xhr) {
                $("#errorBox").removeClass("d-none").text("AJAX lỗi: " + xhr.status);
            }
        });
    });
</script>
</body>
</html>
