<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker</title>

    <link href="/css/index.css" rel="stylesheet"/>
    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<h1 id="title">${title}</h1>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>

<script>
    $(function () {
        $('#title').click(function () {

            $.ajax({
                url: "http://localhost:8080/api/hello",
                type: "POST",
                data: {
                    name: "ashton"
                },
                success: function (data, status) {
                    console.log(status);
                    $('P').html(data.action + " " + data.name);
                }
            });
        });
    })
</script>

</body>
</html>