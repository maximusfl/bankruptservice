<!DOCTYPE html>
<html>
<head>
    <title>Bankruptcy Data</title>
    <script src="/js/script.js"></script>
</head>
<body>
    <h1>Bankruptcy Data</h1>
    <table>
        <thead>
            <tr>
                <th>GUID</th>
                <th>Type</th>
                <th>Category Code</th>
                <th>Data</th>
            </tr>
        </thead>
        <tbody>
            <#list bankruptcyList as bankruptcy>
            <tr>
                <td>${bankruptcy.guid}</td>
                <td>${bankruptcy.type}</td>
                <td>${bankruptcy.categoryCode}</td>
                <td>${bankruptcy.data?join(", ")}</td>
            </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>