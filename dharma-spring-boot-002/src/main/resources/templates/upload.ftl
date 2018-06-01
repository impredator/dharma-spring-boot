<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker</title>

    <link href="/css/index.css" rel="stylesheet"/>
    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>

    <h1>FILE UPLOAD</h1>
    <form method="POST" enctype="multipart/form-data" action="/file/upload">
        文件：<input type="file" name="dharmaFile" />
        <input type="submit" value="上传" />
    </form>

    <#if file??>
        <img src="${file}" />
    </#if>


</body>
</html>