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
		"id" : "sun microsystems '",
		"name" : "sun microsystems '",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "sun microsystems '",
		"data" : {}
		},
		]
	},
	{
		"id" : "web browser",
		"name" : "web browser",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "web browser",
		"data" : {}
		},
		]
	},
	{
		"id" : "main markup language",
		"name" : "main markup language",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "main markup language",
		"data" : {}
		},
		]
	},
	{
		"id" : "other information",
		"name" : "other information",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "other information",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "other information",
		"data" : {}
		},
		]
	},
	{
		"id" : "sun microsystems",
		"name" : "sun microsystems",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "sun microsystems",
		"data" : {}
		},
		]
	},
	{
		"id" : "hypertext markup language",
		"name" : "hypertext markup language",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "hypertext markup language",
		"data" : {}
		},
		]
	},
	{
		"id" : "modification",
		"name" : "modification",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "modification",
		"data" : {}
		},
		]
	},
	{
		"id" : "web page",
		"name" : "web page",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "web page",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "web page",
		"data" : {}
		},
		]
	},
	{
		"id" : "core component",
		"name" : "core component",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "core component",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "core component",
		"data" : {}
		},
		]
	},
	{
		"id" : "programming language",
		"name" : "programming language",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "programming language",
		"data" : {}
		},
		]
	},
	{
		"id" : "james gosling",
		"name" : "james gosling",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "james gosling",
		"data" : {}
		},
		]
	},
	{
		"id" : "html",
		"name" : "html",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "html",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "html",
		"data" : {}
		},
		]
	},
	{
		"id" : "internet",
		"name" : "internet",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "java component",
		"nodeFrom" : "internet",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "internet",
		"data" : {}
		},
		]
	},
	{
		"id" : "java component",
		"name" : "java component",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sun microsystems '",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "web browser",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "main markup language",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "other information",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "sun microsystems",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "hypertext markup language",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "modification",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "web page",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "core component",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "programming language",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "james gosling",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "html",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "internet",
		"nodeFrom" : "java component",
		"data" : {}
		},
		{
		"nodeTo" : "html and java",
		"nodeFrom" : "java component",
		"data" : {}
		},
		]
	},
	{
		"id" : "html and java",
		"name" : "html and java",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
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
var actualpath = "C:/Users/COMPAQ/Desktop/html.docx";	 
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
