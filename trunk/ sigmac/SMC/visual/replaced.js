var labelType, useGradients, nativeTextSupport, animate;

(function() {
  var ua = navigator.userAgent,
      iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
      typeOfCanvas = typeof HTMLCanvasElement,
      nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
      textSupport = nativeCanvasSupport 
        && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
  labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
  nativeTextSupport = labelType == 'Native';
  useGradients = nativeCanvasSupport;
  animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
  elem: false,
  write: function(text){
    if (!this.elem) 
      this.elem = document.getElementById('log');
    this.elem.innerHTML = text;
    this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
  }
};
function WriteToFile(path,message,actualpath)
{

var fso = new ActiveXObject("Scripting.FileSystemObject");
alert(actualpath);
 var txtFile = fso.OpenTextFile(path, 8, true, 0);  
 
    txtFile.WriteLine(message);  
    txtFile.Close();  

}

function init(){
  // init data
var json = [
	{
		"id" : "online resources",
		"name" : "online resources",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "process",
		"nodeFrom" : "online resources",
		"data" : {}
		},
		]
	},
	{
		"id" : "map feature",
		"name" : "map feature",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "map feature",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "map feature",
		"data" : {}
		},
		{
		"nodeTo" : "confidentiality",
		"nodeFrom" : "map feature",
		"data" : {}
		},
		{
		"nodeTo" : "user editable concept",
		"nodeFrom" : "map feature",
		"data" : {}
		},
		{
		"nodeTo" : "different access capability levels",
		"nodeFrom" : "map feature",
		"data" : {}
		},
		{
		"nodeTo" : "individual users",
		"nodeFrom" : "map feature",
		"data" : {}
		},
		]
	},
	{
		"id" : "client",
		"name" : "client",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "server architecture",
		"nodeFrom" : "client",
		"data" : {}
		},
		{
		"nodeTo" : "web",
		"nodeFrom" : "client",
		"data" : {}
		},
		{
		"nodeTo" : "architecture",
		"nodeFrom" : "client",
		"data" : {}
		},
		{
		"nodeTo" : "viable way",
		"nodeFrom" : "client",
		"data" : {}
		},
		{
		"nodeTo" : "client server",
		"nodeFrom" : "client",
		"data" : {}
		},
		{
		"nodeTo" : "client interface",
		"nodeFrom" : "client",
		"data" : {}
		},
		]
	},
	{
		"id" : "pdf",
		"name" : "pdf",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "pdf",
		"data" : {}
		},
		]
	},
	{
		"id" : "edits",
		"name" : "edits",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "other edges and nodes",
		"nodeFrom" : "edits",
		"data" : {}
		},
		]
	},
	{
		"id" : "use advance",
		"name" : "use advance",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "result",
		"nodeFrom" : "use advance",
		"data" : {}
		},
		{
		"nodeTo" : "syntactic and document level analysis",
		"nodeFrom" : "use advance",
		"data" : {}
		},
		{
		"nodeTo" : "concept extraction mechanism",
		"nodeFrom" : "use advance",
		"data" : {}
		},
		]
	},
	{
		"id" : "all the concepts and relationships",
		"name" : "all the concepts and relationships",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "all the concepts and relationships",
		"data" : {}
		},
		]
	},
	{
		"id" : "architecture",
		"name" : "architecture",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "client",
		"nodeFrom" : "architecture",
		"data" : {}
		},
		{
		"nodeTo" : "server architecture",
		"nodeFrom" : "architecture",
		"data" : {}
		},
		{
		"nodeTo" : "web",
		"nodeFrom" : "architecture",
		"data" : {}
		},
		{
		"nodeTo" : "viable way",
		"nodeFrom" : "architecture",
		"data" : {}
		},
		{
		"nodeTo" : "client server",
		"nodeFrom" : "architecture",
		"data" : {}
		},
		{
		"nodeTo" : "client interface",
		"nodeFrom" : "architecture",
		"data" : {}
		},
		]
	},
	{
		"id" : "search box",
		"name" : "search box",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "search box",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "search box",
		"data" : {}
		},
		{
		"nodeTo" : "google search",
		"nodeFrom" : "search box",
		"data" : {}
		},
		{
		"nodeTo" : "auto",
		"nodeFrom" : "search box",
		"data" : {}
		},
		]
	},
	{
		"id" : "pptx",
		"name" : "pptx",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "pptx",
		"data" : {}
		},
		]
	},
	{
		"id" : "concept map visualization",
		"name" : "concept map visualization",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "browsing experience",
		"nodeFrom" : "concept map visualization",
		"data" : {}
		},
		{
		"nodeTo" : "system",
		"nodeFrom" : "concept map visualization",
		"data" : {}
		},
		]
	},
	{
		"id" : "prioritizing",
		"name" : "prioritizing",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "document",
		"nodeFrom" : "prioritizing",
		"data" : {}
		},
		{
		"nodeTo" : "document hierarchy",
		"nodeFrom" : "prioritizing",
		"data" : {}
		},
		{
		"nodeTo" : "modified dates",
		"nodeFrom" : "prioritizing",
		"data" : {}
		},
		]
	},
	{
		"id" : "best way",
		"name" : "best way",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "such a scenario",
		"nodeFrom" : "best way",
		"data" : {}
		},
		{
		"nodeTo" : "concept map",
		"nodeFrom" : "best way",
		"data" : {}
		},
		]
	},
	{
		"id" : "knowledge base",
		"name" : "knowledge base",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "knowledge base",
		"data" : {}
		},
		]
	},
	{
		"id" : "database management system",
		"name" : "database management system",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "term",
		"nodeFrom" : "database management system",
		"data" : {}
		},
		{
		"nodeTo" : "word",
		"nodeFrom" : "database management system",
		"data" : {}
		},
		{
		"nodeTo" : "example",
		"nodeFrom" : "database management system",
		"data" : {}
		},
		]
	},
	{
		"id" : "client server",
		"name" : "client server",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "client",
		"nodeFrom" : "client server",
		"data" : {}
		},
		{
		"nodeTo" : "server architecture",
		"nodeFrom" : "client server",
		"data" : {}
		},
		{
		"nodeTo" : "web",
		"nodeFrom" : "client server",
		"data" : {}
		},
		{
		"nodeTo" : "architecture",
		"nodeFrom" : "client server",
		"data" : {}
		},
		{
		"nodeTo" : "viable way",
		"nodeFrom" : "client server",
		"data" : {}
		},
		{
		"nodeTo" : "client interface",
		"nodeFrom" : "client server",
		"data" : {}
		},
		]
	},
	{
		"id" : "existing relationships",
		"name" : "existing relationships",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "add new relationships",
		"nodeFrom" : "existing relationships",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "existing relationships",
		"data" : {}
		},
		{
		"nodeTo" : "map",
		"nodeFrom" : "existing relationships",
		"data" : {}
		},
		{
		"nodeTo" : "new concepts",
		"nodeFrom" : "existing relationships",
		"data" : {}
		},
		]
	},
	{
		"id" : "client interface",
		"name" : "client interface",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "server architecture",
		"nodeFrom" : "client interface",
		"data" : {}
		},
		{
		"nodeTo" : "web",
		"nodeFrom" : "client interface",
		"data" : {}
		},
		{
		"nodeTo" : "viable way",
		"nodeFrom" : "client interface",
		"data" : {}
		},
		]
	},
	{
		"id" : "individual users",
		"name" : "individual users",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "individual users",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "individual users",
		"data" : {}
		},
		{
		"nodeTo" : "confidentiality",
		"nodeFrom" : "individual users",
		"data" : {}
		},
		{
		"nodeTo" : "user editable concept",
		"nodeFrom" : "individual users",
		"data" : {}
		},
		{
		"nodeTo" : "different access capability levels",
		"nodeFrom" : "individual users",
		"data" : {}
		},
		]
	},
	{
		"id" : "add new relationships",
		"name" : "add new relationships",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "concept",
		"nodeFrom" : "add new relationships",
		"data" : {}
		},
		{
		"nodeTo" : "map",
		"nodeFrom" : "add new relationships",
		"data" : {}
		},
		{
		"nodeTo" : "new concepts",
		"nodeFrom" : "add new relationships",
		"data" : {}
		},
		{
		"nodeTo" : "existing relationships",
		"nodeFrom" : "add new relationships",
		"data" : {}
		},
		]
	},
	{
		"id" : "several document types",
		"name" : "several document types",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "enterprise knowledge base",
		"nodeFrom" : "several document types",
		"data" : {}
		},
		{
		"nodeTo" : "there",
		"nodeFrom" : "several document types",
		"data" : {}
		},
		]
	},
	{
		"id" : "search documents",
		"name" : "search documents",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "user",
		"nodeFrom" : "search documents",
		"data" : {}
		},
		]
	},
	{
		"id" : "engine",
		"name" : "engine",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "process",
		"nodeFrom" : "engine",
		"data" : {}
		},
		{
		"nodeTo" : "rule",
		"nodeFrom" : "engine",
		"data" : {}
		},
		]
	},
	{
		"id" : "several hosts",
		"name" : "several hosts",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "several hosts",
		"data" : {}
		},
		]
	},
	{
		"id" : "editing",
		"name" : "editing",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "editing",
		"data" : {}
		},
		{
		"nodeTo" : "map",
		"nodeFrom" : "editing",
		"data" : {}
		},
		]
	},
	{
		"id" : "existing concepts",
		"name" : "existing concepts",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "document",
		"nodeFrom" : "existing concepts",
		"data" : {}
		},
		{
		"nodeTo" : "user",
		"nodeFrom" : "existing concepts",
		"data" : {}
		},
		]
	},
	{
		"id" : "concepts and relationships",
		"name" : "concepts and relationships",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "rule",
		"nodeFrom" : "concepts and relationships",
		"data" : {}
		},
		]
	},
	{
		"id" : "enterprise",
		"name" : "enterprise",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "enterprise",
		"data" : {}
		},
		]
	},
	{
		"id" : "common document types",
		"name" : "common document types",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "common document types",
		"data" : {}
		},
		]
	},
	{
		"id" : "viable way",
		"name" : "viable way",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "client",
		"nodeFrom" : "viable way",
		"data" : {}
		},
		{
		"nodeTo" : "server architecture",
		"nodeFrom" : "viable way",
		"data" : {}
		},
		{
		"nodeTo" : "web",
		"nodeFrom" : "viable way",
		"data" : {}
		},
		{
		"nodeTo" : "architecture",
		"nodeFrom" : "viable way",
		"data" : {}
		},
		{
		"nodeTo" : "client server",
		"nodeFrom" : "viable way",
		"data" : {}
		},
		]
	},
	{
		"id" : "ppt",
		"name" : "ppt",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "ppt",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "ppt",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "ppt",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "ppt",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "ppt",
		"data" : {}
		},
		]
	},
	{
		"id" : "project",
		"name" : "project",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "project",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "project",
		"data" : {}
		},
		]
	},
	{
		"id" : "node",
		"name" : "node",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relationship",
		"nodeFrom" : "node",
		"data" : {}
		},
		{
		"nodeTo" : "concepts and edges",
		"nodeFrom" : "node",
		"data" : {}
		},
		]
	},
	{
		"id" : "system",
		"name" : "system",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "map feature",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "plugins",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "editing",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "search box",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "different access capability levels",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "browsing experience",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "english language",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "confidentiality",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "google search",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "map",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "user editable concept",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "auto",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "new document types",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise corpuses",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "concept map",
		"nodeFrom" : "system",
		"data" : {}
		},
		{
		"nodeTo" : "individual users",
		"nodeFrom" : "system",
		"data" : {}
		},
		]
	},
	{
		"id" : "window",
		"name" : "window",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "document",
		"nodeFrom" : "window",
		"data" : {}
		},
		{
		"nodeTo" : "user",
		"nodeFrom" : "window",
		"data" : {}
		},
		]
	},
	{
		"id" : "confidentiality",
		"name" : "confidentiality",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "confidentiality",
		"data" : {}
		},
		{
		"nodeTo" : "map feature",
		"nodeFrom" : "confidentiality",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "confidentiality",
		"data" : {}
		},
		{
		"nodeTo" : "user editable concept",
		"nodeFrom" : "confidentiality",
		"data" : {}
		},
		{
		"nodeTo" : "different access capability levels",
		"nodeFrom" : "confidentiality",
		"data" : {}
		},
		{
		"nodeTo" : "individual users",
		"nodeFrom" : "confidentiality",
		"data" : {}
		},
		]
	},
	{
		"id" : "document hierarchy",
		"name" : "document hierarchy",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "prioritizing",
		"nodeFrom" : "document hierarchy",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "document hierarchy",
		"data" : {}
		},
		{
		"nodeTo" : "modified dates",
		"nodeFrom" : "document hierarchy",
		"data" : {}
		},
		]
	},
	{
		"id" : "user editable concept",
		"name" : "user editable concept",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "user editable concept",
		"data" : {}
		},
		{
		"nodeTo" : "map feature",
		"nodeFrom" : "user editable concept",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "user editable concept",
		"data" : {}
		},
		{
		"nodeTo" : "confidentiality",
		"nodeFrom" : "user editable concept",
		"data" : {}
		},
		{
		"nodeTo" : "different access capability levels",
		"nodeFrom" : "user editable concept",
		"data" : {}
		},
		{
		"nodeTo" : "individual users",
		"nodeFrom" : "user editable concept",
		"data" : {}
		},
		]
	},
	{
		"id" : "auto",
		"name" : "auto",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "auto",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "auto",
		"data" : {}
		},
		{
		"nodeTo" : "google search",
		"nodeFrom" : "auto",
		"data" : {}
		},
		]
	},
	{
		"id" : "word",
		"name" : "word",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "term",
		"nodeFrom" : "word",
		"data" : {}
		},
		{
		"nodeTo" : "database management system",
		"nodeFrom" : "word",
		"data" : {}
		},
		{
		"nodeTo" : "example",
		"nodeFrom" : "word",
		"data" : {}
		},
		]
	},
	{
		"id" : "modified dates",
		"name" : "modified dates",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "document",
		"nodeFrom" : "modified dates",
		"data" : {}
		},
		]
	},
	{
		"id" : "example",
		"name" : "example",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "term",
		"nodeFrom" : "example",
		"data" : {}
		},
		]
	},
	{
		"id" : "open document formats",
		"name" : "open document formats",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "open document formats",
		"data" : {}
		},
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "open document formats",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "open document formats",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "open document formats",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "open document formats",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "open document formats",
		"data" : {}
		},
		]
	},
	{
		"id" : "result",
		"name" : "result",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "syntactic and document level analysis",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "use advance",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "concept extraction mechanism",
		"nodeFrom" : "result",
		"data" : {}
		},
		]
	},
	{
		"id" : "concept",
		"name" : "concept",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "related documents",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "add new relationships",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "system",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "google search",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "map",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "auto",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "search box",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "user",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "new concepts",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "existing relationships",
		"nodeFrom" : "concept",
		"data" : {}
		},
		]
	},
	{
		"id" : "concepts and edges",
		"name" : "concepts and edges",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relationship",
		"nodeFrom" : "concepts and edges",
		"data" : {}
		},
		]
	},
	{
		"id" : "plugins",
		"name" : "plugins",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "plugins",
		"data" : {}
		},
		{
		"nodeTo" : "new document types",
		"nodeFrom" : "plugins",
		"data" : {}
		},
		]
	},
	{
		"id" : "other edges and nodes",
		"name" : "other edges and nodes",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "different access capability levels",
		"name" : "different access capability levels",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "different access capability levels",
		"data" : {}
		},
		{
		"nodeTo" : "map feature",
		"nodeFrom" : "different access capability levels",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "different access capability levels",
		"data" : {}
		},
		{
		"nodeTo" : "confidentiality",
		"nodeFrom" : "different access capability levels",
		"data" : {}
		},
		{
		"nodeTo" : "user editable concept",
		"nodeFrom" : "different access capability levels",
		"data" : {}
		},
		]
	},
	{
		"id" : "document",
		"name" : "document",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "prioritizing",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "map feature",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "window",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "english language",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "confidentiality",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "document hierarchy",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "user editable concept",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "existing concepts",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "individual users",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "different access capability levels",
		"nodeFrom" : "document",
		"data" : {}
		},
		]
	},
	{
		"id" : "english language",
		"name" : "english language",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "english language",
		"data" : {}
		},
		{
		"nodeTo" : "document",
		"nodeFrom" : "english language",
		"data" : {}
		},
		]
	},
	{
		"id" : "server architecture",
		"name" : "server architecture",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "client",
		"nodeFrom" : "server architecture",
		"data" : {}
		},
		{
		"nodeTo" : "web",
		"nodeFrom" : "server architecture",
		"data" : {}
		},
		{
		"nodeTo" : "architecture",
		"nodeFrom" : "server architecture",
		"data" : {}
		},
		{
		"nodeTo" : "viable way",
		"nodeFrom" : "server architecture",
		"data" : {}
		},
		{
		"nodeTo" : "client server",
		"nodeFrom" : "server architecture",
		"data" : {}
		},
		{
		"nodeTo" : "client interface",
		"nodeFrom" : "server architecture",
		"data" : {}
		},
		]
	},
	{
		"id" : "rule",
		"name" : "rule",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "process",
		"nodeFrom" : "rule",
		"data" : {}
		},
		]
	},
	{
		"id" : "map",
		"name" : "map",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "add new relationships",
		"nodeFrom" : "map",
		"data" : {}
		},
		{
		"nodeTo" : "system",
		"nodeFrom" : "map",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "map",
		"data" : {}
		},
		{
		"nodeTo" : "new concepts",
		"nodeFrom" : "map",
		"data" : {}
		},
		{
		"nodeTo" : "existing relationships",
		"nodeFrom" : "map",
		"data" : {}
		},
		]
	},
	{
		"id" : "syntactic and document level analysis",
		"name" : "syntactic and document level analysis",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "result",
		"nodeFrom" : "syntactic and document level analysis",
		"data" : {}
		},
		{
		"nodeTo" : "use advance",
		"nodeFrom" : "syntactic and document level analysis",
		"data" : {}
		},
		{
		"nodeTo" : "concept extraction mechanism",
		"nodeFrom" : "syntactic and document level analysis",
		"data" : {}
		},
		]
	},
	{
		"id" : "enterprise corpuses",
		"name" : "enterprise corpuses",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "enterprise corpuses",
		"data" : {}
		},
		]
	},
	{
		"id" : "concept extraction",
		"name" : "concept extraction",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "multi-term words and acronyms",
		"nodeFrom" : "concept extraction",
		"data" : {}
		},
		]
	},
	{
		"id" : "web",
		"name" : "web",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "client",
		"nodeFrom" : "web",
		"data" : {}
		},
		{
		"nodeTo" : "server architecture",
		"nodeFrom" : "web",
		"data" : {}
		},
		{
		"nodeTo" : "architecture",
		"nodeFrom" : "web",
		"data" : {}
		},
		{
		"nodeTo" : "viable way",
		"nodeFrom" : "web",
		"data" : {}
		},
		{
		"nodeTo" : "client server",
		"nodeFrom" : "web",
		"data" : {}
		},
		{
		"nodeTo" : "client interface",
		"nodeFrom" : "web",
		"data" : {}
		},
		]
	},
	{
		"id" : "concept extraction mechanism",
		"name" : "concept extraction mechanism",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "interface",
		"name" : "interface",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "user",
		"nodeFrom" : "interface",
		"data" : {}
		},
		{
		"nodeTo" : "concept map",
		"nodeFrom" : "interface",
		"data" : {}
		},
		]
	},
	{
		"id" : "multi-term words and acronyms",
		"name" : "multi-term words and acronyms",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "related documents",
		"name" : "related documents",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "concept",
		"nodeFrom" : "related documents",
		"data" : {}
		},
		{
		"nodeTo" : "user",
		"nodeFrom" : "related documents",
		"data" : {}
		},
		]
	},
	{
		"id" : "process",
		"name" : "process",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "engine",
		"nodeFrom" : "process",
		"data" : {}
		},
		]
	},
	{
		"id" : "relationship",
		"name" : "relationship",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "node",
		"nodeFrom" : "relationship",
		"data" : {}
		},
		]
	},
	{
		"id" : "browsing experience",
		"name" : "browsing experience",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "browsing experience",
		"data" : {}
		},
		{
		"nodeTo" : "concept map visualization",
		"nodeFrom" : "browsing experience",
		"data" : {}
		},
		]
	},
	{
		"id" : "enterprise knowledge base",
		"name" : "enterprise knowledge base",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "several document types",
		"nodeFrom" : "enterprise knowledge base",
		"data" : {}
		},
		{
		"nodeTo" : "there",
		"nodeFrom" : "enterprise knowledge base",
		"data" : {}
		},
		]
	},
	{
		"id" : "term",
		"name" : "term",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "database management system",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "word",
		"nodeFrom" : "term",
		"data" : {}
		},
		]
	},
	{
		"id" : "google search",
		"name" : "google search",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "google search",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "google search",
		"data" : {}
		},
		{
		"nodeTo" : "search box",
		"nodeFrom" : "google search",
		"data" : {}
		},
		]
	},
	{
		"id" : "there",
		"name" : "there",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "doc",
		"name" : "doc",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "docx",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "doc",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "doc",
		"data" : {}
		},
		]
	},
	{
		"id" : "docx",
		"name" : "docx",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "project",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "knowledge base",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "pdf",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "doc",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "enterprise",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "common document types",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "open document formats",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "pptx",
		"nodeFrom" : "docx",
		"data" : {}
		},
		{
		"nodeTo" : "ppt",
		"nodeFrom" : "docx",
		"data" : {}
		},
		]
	},
	{
		"id" : "new document types",
		"name" : "new document types",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "new document types",
		"data" : {}
		},
		]
	},
	{
		"id" : "new concepts",
		"name" : "new concepts",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "add new relationships",
		"nodeFrom" : "new concepts",
		"data" : {}
		},
		{
		"nodeTo" : "concept",
		"nodeFrom" : "new concepts",
		"data" : {}
		},
		{
		"nodeTo" : "map",
		"nodeFrom" : "new concepts",
		"data" : {}
		},
		]
	},
	{
		"id" : "user",
		"name" : "user",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "concept",
		"nodeFrom" : "user",
		"data" : {}
		},
		{
		"nodeTo" : "interface",
		"nodeFrom" : "user",
		"data" : {}
		},
		{
		"nodeTo" : "concept map",
		"nodeFrom" : "user",
		"data" : {}
		},
		]
	},
	{
		"id" : "such a scenario",
		"name" : "such a scenario",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "best way",
		"nodeFrom" : "such a scenario",
		"data" : {}
		},
		{
		"nodeTo" : "concept map",
		"nodeFrom" : "such a scenario",
		"data" : {}
		},
		]
	},
	{
		"id" : "concept map",
		"name" : "concept map",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "system",
		"nodeFrom" : "concept map",
		"data" : {}
		},
		]
	}]
;  // end
  // init ForceDirected
  var fd = new $jit.ForceDirected({
    //id of the visualization container
    injectInto: 'infovis',
    //Enable zooming and panning
    //with scrolling and DnD
    Navigation: {
      enable: true,
      type: 'Native',
      //Enable panning events only if we're dragging the empty
      //canvas (and not a node).
      panning: 'avoid nodes',
      zooming: 10 //zoom speed. higher is more sensible
    },
    // Change node and edge styles such as
    // color and width.
    // These properties are also set per node
    // with dollar prefixed data-properties in the
    // JSON structure.
    Node: {
      overridable: true,
      dim: 7
    },
    Edge: {
      overridable: true,
      color: '#23A4FF',
      lineWidth: 0.4
    },
    // Add node events
    Events: {
      enable: true,
      type: 'Native',
      //Change cursor style when hovering a node
      onMouseEnter: function() {
        fd.canvas.getElement().style.cursor = 'move';
      },
      onMouseLeave: function() {
        fd.canvas.getElement().style.cursor = '';
      },
      //Update node positions when dragged
      onDragMove: function(node, eventInfo, e) {
        var pos = eventInfo.getPos();
        node.pos.setc(pos.x, pos.y);
        fd.plot();
      },
      //Implement the same handler for touchscreens
      onTouchMove: function(node, eventInfo, e) {
        $jit.util.event.stop(e); //stop default touchmove event
        this.onDragMove(node, eventInfo, e);
      }
    },
    //Number of iterations for the FD algorithm
    iterations: 200,
    //Edge length
    levelDistance: 130,
    // This method is only triggered
    // on label creation and only for DOM labels (not native canvas ones).
    onCreateLabel: function(domElement, node){
      // Create a 'name' and 'close' buttons and add them
      // to the main node label
      var nameContainer = document.createElement('span'),
          closeButton = document.createElement('span'),
          style = nameContainer.style;
      nameContainer.className = 'name';
      nameContainer.innerHTML = node.name;
      closeButton.className = 'close';
      closeButton.innerHTML = 'x';
      domElement.appendChild(nameContainer);
      domElement.appendChild(closeButton);
      style.fontSize = "0.8em";
      style.color = "#ddd";
      //Fade the node and its connections when
      //clicking the close button
      closeButton.onclick = function() {
	  alert(node.name);
var actualpath = "C:/Users/user/Downloads/Visualization of Concept and relationships.docx";	 
	  WriteToFile("visual\\rnodes.dpi",node.name,actualpath);
	  
        node.setData('alpha', 0, 'end');
        node.eachAdjacency(function(adj) {
          adj.setData('alpha', 0, 'end');
        });
        fd.fx.animate({
          modes: ['node-property:alpha',
                  'edge-property:alpha'],
          duration: 500
        });
      };
      //Toggle a node selection when clicking
      //its name. This is done by animating some
      //node styles like its dimension and the color
      //and lineWidth of its adjacencies.
      nameContainer.onclick = function() {
        //set final styles
        fd.graph.eachNode(function(n) {
          if(n.id != node.id) delete n.selected;
          n.setData('dim', 7, 'end');
          n.eachAdjacency(function(adj) {
            adj.setDataset('end', {
              lineWidth: 0.4,
              color: '#23a4ff'
            });
          });
        });
        if(!node.selected) {
          node.selected = true;
          node.setData('dim', 17, 'end');
          node.eachAdjacency(function(adj) {
            adj.setDataset('end', {
              lineWidth: 3,
              color: '#36acfb'
            });
          });
        } else {
          delete node.selected;
        }
        //trigger animation to final styles
        fd.fx.animate({
          modes: ['node-property:dim',
                  'edge-property:lineWidth:color'],
          duration: 500
        });
        // Build the right column relations list.
        // This is done by traversing the clicked node connections.
        var html = "<h4>" + node.name + "</h4><b> connections:</b><ul><li>",
            list = [];
        node.eachAdjacency(function(adj){
          if(adj.getData('alpha')) list.push(adj.nodeTo.name);
        });
        //append connections information
        $jit.id('inner-details').innerHTML = html + list.join("</li><li>") + "</li></ul>";
      };
    },
    // Change node styles when DOM labels are placed
    // or moved.
    onPlaceLabel: function(domElement, node){
      var style = domElement.style;
      var left = parseInt(style.left);
      var top = parseInt(style.top);
      var w = domElement.offsetWidth;
      style.left = (left - w / 2) + 'px';
      style.top = (top + 10) + 'px';
      style.display = '';
    }
  });
  // load JSON data.
  fd.loadJSON(json);
  // compute positions incrementally and animate.
  fd.computeIncremental({
    iter: 40,
    property: 'end',
    onStep: function(perc){
      Log.write(perc + '% loaded...');
    },
    onComplete: function(){
      Log.write('done');
      fd.animate({
        modes: ['linear'],
        transition: $jit.Trans.Elastic.easeOut,
        duration: 2500
      });
    }
  });
  // end
}
