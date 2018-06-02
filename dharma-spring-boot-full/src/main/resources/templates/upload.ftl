<!DOCTYPE html>
<html>
    <head>
        <title>FreeMarker</title>
        <link href="/css/index.css" rel="stylesheet"/>
        <script type="text/javascript" src="/webjars/jquery/3.3.0/jquery.min.js"></script
    </head>
    <body>
        <h1>FILE UPLOAD</h1>
        <form method="post" enctype="multipart/form-data"
              action="/file/upload">
            File: <input type="file" name="dharmaFile" />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
