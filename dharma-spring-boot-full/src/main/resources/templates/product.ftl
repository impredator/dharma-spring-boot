<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker</title>

    <link href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/css/index.css" rel="stylesheet"/>
    <script type="text/javascript" src="/webjars/jquery/3.3.0/jquery.min.js"></script>
</head>
<body>
    <h1>PRODUCT LIST</h1>
    <#list products>
        <table class="table table-hover table-dark">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">NAME</th>
                    <th scope="col">PRICE</th>
                </tr>
            </thead>
            <tbody>
                <#items as product>
                    <tr>
                        <td scope="col">${product.id}</td>
                        <td scope="col">${product.name}</td>
                        <td scope="col">${product.price}</td>
                    </tr>
                </#items>
            </tbody>
        </table>
    <#else>
        Table is Empty
    </#list>
</body>
</html>
