<%@ page import="smc.RelatedConcept"%>
<%@ page import="smc.Concept"%>
<%@ page import="smc.Document"%>
<%@ page import="smc.JSCreator"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.ObjectInputStream"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
RelatedConcept rec1 = new RelatedConcept("is a ", "Java", false, 4);
RelatedConcept rec2 = new RelatedConcept("is a ", "Programmaing Language", true, 4);
RelatedConcept rec3 = new RelatedConcept("is a ", "Php", false, 4);
RelatedConcept rec4 = new RelatedConcept("is a ", "Web", true, 4);

Concept cn1 = new Concept("Java");
Concept cn2 = new Concept("Programmaing Language");
Concept cn3 = new Concept("Php");
Concept cn4 = new Concept("Web");

cn1.addRelatedConcept(rec2);
cn2.addRelatedConcept(rec1);
cn3.addRelatedConcept(rec4);
cn4.addRelatedConcept(rec3);

ArrayList<Concept> con = new ArrayList<Concept>();
con.add(cn1);
con.add(cn2);
con.add(cn3);
con.add(cn4);

//Document doc = new Document();
//doc.addConcepts(con);
Document doc = null;
		FileInputStream fileIn =
            new FileInputStream("C:\\Users\\Eranda\\Downloads\\doccc.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			doc = (Document) in.readObject();
			in.close();
			fileIn.close();

JSCreator jsc = new JSCreator();



%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ForceDirected - Graph Operations</title>

<!-- CSS Files -->
<link type="text/css" href="base.css" rel="stylesheet" />
<link type="text/css" href="ForceDirected.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="jit.js"></script>

<!-- Example File -->
<!--<script language="javascript" type="text/javascript" src="example2.js"></script>  -->
<script type="text/javascript">
<%
out.println(jsc.CreateJS(doc));
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

</div>

<div id="log"></div>
</div>
</body>
</html>