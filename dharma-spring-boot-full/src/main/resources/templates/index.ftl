<!DOCTYPE html>
<html>
    <head>
        <title>FreeMarker</title>

        <link href="/css/index.css" rel="stylesheet"/>
        <script type="text/javascript" src="/webjars/jquery/3.3.0/jquery.min.js"></script>

        <script>
            $(function() {
                $(title).click(function() {
                    $.ajax({
                        url: "http://127.0.0.1:8080/index",
                        success: function(data, status, xhr) {
                            console.log(status);
                            $("P").html(data)
                        }
                    })

                });
            })
        </script>
    </head>
    <body>
        <h1 id="title">${title}</h1>
        <p></p>
        <p></p>
        <p></p>
        <p></p>
        <p></p>

    </body>
</html>
