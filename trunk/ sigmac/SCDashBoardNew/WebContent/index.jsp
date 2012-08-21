<%@ page import="smc.RelatedConcept"%>
<%@ page import="smc.Concept"%>
<%@ page import="smc.Document"%>
<%@ page import="smc.JSCreator"%>
<%@ page import="smc.DocObjectCreator"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.ObjectInputStream"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%

DocObjectCreator dococ= new DocObjectCreator();
JSCreator jsc = new JSCreator();


%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ForceDirected - Graph Operations</title>

<!-- CSS Files -->
<link type="text/css" href="base.css" rel="stylesheet" />
<link type="text/css" href="ForceDirected.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="jit.js"></script>

<!-- Example File -->
<!--<script language="javascript" type="text/javascript" src="example2.js"></script>  -->
<script type="text/javascript">
<%
out.println(jsc.CreateJS(dococ.getDoc("InterXML//1344510832982.xml")));
%>
</script>
</head>

<body onload="init();">
<div id="container">



<div id="center-container">
    <div id="infovis"></div>    
</div>

<div id="right-container">

<div id="MSearchPanel" class="MSearchPanelInactive"><input type="text" id="MSearchField" value="Search" onfocus="searchPanel.OnSearchFieldFocus(true)" onblur="searchPanel.OnSearchFieldFocus(false)" onkeyup="searchPanel.OnSearchFieldChange()"><select id="MSearchType" onfocus="searchPanel.OnSearchTypeFocus(true)" onblur="searchPanel.OnSearchTypeFocus(false)" onchange="searchPanel.OnSearchTypeChange()"><option id="MSearchEverything" selected="" value="General">Everything</option><option value="Classes">Classes</option><option value="Files">Files</option><option value="Functions">Functions</option></select></div>
<div id="inner-details"></div>
<div id="inner-details2"></div>
</div>


</div>

<div id="log"></div>
</div>
</body>
</html>