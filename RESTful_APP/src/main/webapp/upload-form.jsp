<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<script type="text/javascript">
    var counter = 1;
    function addInput(divName){
        var newdiv = document.createElement('div');
        newdiv.innerHTML = "<p>File: " + (counter + 1) + " <input type='file' name='files' size='60' /></p>";
        document.getElementById(divName).appendChild(newdiv);
        counter++;
    }
</script>
<form method="post" action="count" enctype="multipart/form-data" >
    <div id="dynamicInput">
        <p>File 1: <input type="file" name="files" size="60" /></p>
    </div>
    <p><input type="button" value="Add another input" onClick="addInput('dynamicInput');"></p>
    <p><input type="submit" value="Upload" /></p>
</form>
</body>
