<%@page import="java.util.List" %>
<%@ page import="sparkweb.tables.BncData" %>
<%@ page import="sparkweb.tables.BpcData" %>
<%@ page import="sparkweb.tables.NppcData" %>
<%@ page import="sparkweb.tables.NrpcData" %>
<%@ page import="sparkweb.tables.NrcData" %>
<%@ page import="sparkweb.tables.PncData" %>
<%@ page import="sparkweb.tables.PrcData" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<%-- Define the table selection dropdown --%>
<label for="tableSelection">选择要展示的表:</label>
<select id="tableSelection" onchange="displaySelectedTable()">
    <option value="bnclist">bnclist</option>
    <option value="bpclist">bpclist</option>
    <option value="nppclist">nppclist</option>
    <option value="nrpclist">nrpclist</option>
    <option value="nrclist">nrclist</option>
    <option value="pnclist">pnclist</option>
    <option value="prclist">prclist</option>
</select>

<%-- Define the table container --%>
<div id="tableContainer"></div>

<%-- Retrieve data from request attributes --%>
<%
    List<BncData> bnclist = (List<BncData>) request.getAttribute("bnclist");
    List<BpcData> bpclist = (List<BpcData>) request.getAttribute("bpclist");
    List<NppcData> nppclist = (List<NppcData>) request.getAttribute("nppclist");
    List<NrpcData> nrpclist = (List<NrpcData>) request.getAttribute("nrpclist");
    List<NrcData> nrclist = (List<NrcData>) request.getAttribute("nrclist");
    List<PncData> pnclist = (List<PncData>) request.getAttribute("pnclist");
    List<PrcData> prclist = (List<PrcData>) request.getAttribute("prclist");
%>

<%-- Define the JavaScript function to display the selected table --%>
<script>
    function displaySelectedTable() {
        var selectedTable = document.getElementById("tableSelection").value;
        displayTable(selectedTable);
    }

    function displayTable(tableName) {
        var tableContent;

        switch (tableName) {
            case "bnclist":
                tableContent = `
                        <table>
                            <tr>
                                <th>beds</th>
                                <th>neighbourhood</th>
                                <th>count</th>
                            </tr>
                            <c:forEach items="${bnclist}" var="bnc">
                                <tr>
                                    <td>${bnc.beds}</td>
                                    <td>${bnc.neighbourhood}</td>
                                    <td>${bnc.count}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    `;
                break;
            case "bpclist":
                tableContent = `
                        <table>
                            <tr>
                                <th>beds</th>
                                <th>property</th>
                                <th>count</th>
                            </tr>
                            <c:forEach items="${bpclist}" var="bpc">
                                <tr>
                                    <td>${bpc.beds}</td>
                                    <td>${bpc.property}</td>
                                    <td>${bpc.count}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    `;
                break;
            case "nppclist":
                tableContent = `
                        <table>
                            <tr>
                                <th>neighbourhood</th>
                                <th>property</th>
                                <th>price</th>
                                <th>count</th>
                            </tr>
                            <c:forEach items="${nppclist}" var="nppc">
                                <tr>
                                    <td>${nppc.neighbourhood}</td>
                                    <td>${nppc.property}</td>
                                    <td>${nppc.price}</td>
                                    <td>${nppc.count}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    `;
                break;
            case "nrpclist":
                tableContent = `
                        <table>
                            <tr>
                                <th>neighbourhood</th>
                                <th>room</th>
                                <th>price</th>
                                <th>count</th>
                            </tr>
                            <c:forEach items="${nrpclist}" var="nrpc">
                                <tr>
                                    <td>${nrpc.neighbourhood}</td>
                                    <td>${nrpc.room}</td>
                                    <td>${nrpc.price}</td>
                                    <td>${nrpc.count}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    `;
                break;
            case "nrclist":
                tableContent = `
                        <table>
                            <tr>
                                <th>neighbourhood</th>
                                <th>room</th>
                                <th>count</th>
                            </tr>
                            <c:forEach items="${nrclist}" var="nrc">
                                <tr>
                                    <td>${nrc.neighbourhood}</td>
                                    <td>${nrc.room}</td>
                                    <td>${nrc.count}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    `;
                break;
            case "pnclist":
                tableContent = `
                        <table>
                            <tr>
                                <th>price</th>
                                <th>neighbourhood</th>
                                <th>count</th>
                            </tr>
                            <c:forEach items="${pnclist}" var="pnc">
                                <tr>
                                    <td>${pnc.price}</td>
                                    <td>${pnc.neighbourhood}</td>
                                    <td>${pnc.count}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    `;
                break;
            case "prclist":
                tableContent = `
                        <table>
                            <tr>
                                <th>price</th>
                                <th>room</th>
                                <th>count</th>
                            </tr>
                            <c:forEach items="${prclist}" var="prc">
                                <tr>
                                    <td>${prc.price}</td>
                                    <td>${prc.room}</td>
                                    <td>${prc.count}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    `;
                break;
            default:
                tableContent = "";
                break;
        }

        document.getElementById("tableContainer").innerHTML = tableContent;
    }

    // Display the default selected table
    displayTable("bnclist");
</script>
</body>
</html>
