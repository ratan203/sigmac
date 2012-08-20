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
		"id" : "follows",
		"name" : "follows",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "simple understanding",
		"nodeFrom" : "follows",
		"data" : {}
		},
		{
		"nodeTo" : "returned hash value",
		"nodeFrom" : "follows",
		"data" : {}
		},
		]
	},
	{
		"id" : "2-bit int addition",
		"name" : "2-bit int addition",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "term",
		"nodeFrom" : "2-bit int addition",
		"data" : {}
		},
		{
		"nodeTo" : "prime number",
		"nodeFrom" : "2-bit int addition",
		"data" : {}
		},
		{
		"nodeTo" : "th character",
		"nodeFrom" : "2-bit int addition",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "2-bit int addition",
		"data" : {}
		},
		{
		"nodeTo" : "length",
		"nodeFrom" : "2-bit int addition",
		"data" : {}
		},
		{
		"nodeTo" : "s. 31",
		"nodeFrom" : "2-bit int addition",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "2-bit int addition",
		"data" : {}
		},
		]
	},
	{
		"id" : "hash function",
		"name" : "hash function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "execution time and number",
		"nodeFrom" : "hash function",
		"data" : {}
		},
		{
		"nodeTo" : "00 000 and 700 000 strings",
		"nodeFrom" : "hash function",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "hash function",
		"data" : {}
		},
		]
	},
	{
		"id" : "count",
		"name" : "count",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "int",
		"nodeFrom" : "count",
		"data" : {}
		},
		]
	},
	{
		"id" : "for",
		"name" : "for",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "i -lrb- = l ; i -lrb-",
		"nodeFrom" : "for",
		"data" : {}
		},
		{
		"nodeTo" : "int i",
		"nodeFrom" : "for",
		"data" : {}
		},
		]
	},
	{
		"id" : "char val -lrb- -rrb-",
		"name" : "char val -lrb- -rrb-",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "value",
		"nodeFrom" : "char val -lrb- -rrb-",
		"data" : {}
		},
		]
	},
	{
		"id" : "public int aphash",
		"name" : "public int aphash",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "string str",
		"nodeFrom" : "public int aphash",
		"data" : {}
		},
		]
	},
	{
		"id" : "current java hashcode function",
		"name" : "current java hashcode function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "modification",
		"nodeFrom" : "current java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "current java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "iteration",
		"nodeFrom" : "current java hashcode function",
		"data" : {}
		},
		]
	},
	{
		"id" : "ap hash function",
		"name" : "ap hash function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "java hash code function",
		"nodeFrom" : "ap hash function",
		"data" : {}
		},
		{
		"nodeTo" : "execution time",
		"nodeFrom" : "ap hash function",
		"data" : {}
		},
		{
		"nodeTo" : "same inputs",
		"nodeFrom" : "ap hash function",
		"data" : {}
		},
		{
		"nodeTo" : "java hashcode function",
		"nodeFrom" : "ap hash function",
		"data" : {}
		},
		{
		"nodeTo" : "much time",
		"nodeFrom" : "ap hash function",
		"data" : {}
		},
		{
		"nodeTo" : "execution",
		"nodeFrom" : "ap hash function",
		"data" : {}
		},
		]
	},
	{
		"id" : "lrb- int i = 0",
		"name" : "lrb- int i = 0",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "i -lrb- str",
		"nodeFrom" : "lrb- int i = 0",
		"data" : {}
		},
		]
	},
	{
		"id" : "function",
		"name" : "function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "existing java hashcode function",
		"nodeFrom" : "function",
		"data" : {}
		},
		{
		"nodeTo" : "modified java",
		"nodeFrom" : "function",
		"data" : {}
		},
		{
		"nodeTo" : "java hashcode function",
		"nodeFrom" : "function",
		"data" : {}
		},
		{
		"nodeTo" : "table",
		"nodeFrom" : "function",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "function",
		"data" : {}
		},
		]
	},
	{
		"id" : "iteration",
		"name" : "iteration",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "modification",
		"nodeFrom" : "iteration",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "iteration",
		"data" : {}
		},
		]
	},
	{
		"id" : "calculation",
		"name" : "calculation",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "ram",
		"nodeFrom" : "calculation",
		"data" : {}
		},
		{
		"nodeTo" : "core2duo 2.0 processor",
		"nodeFrom" : "calculation",
		"data" : {}
		},
		]
	},
	{
		"id" : "rrb-",
		"name" : "rrb-",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "length",
		"nodeFrom" : "rrb-",
		"data" : {}
		},
		{
		"nodeTo" : "i + +",
		"nodeFrom" : "rrb-",
		"data" : {}
		},
		]
	},
	{
		"id" : "rrb- \*",
		"name" : "rrb- \*",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "-rcb-",
		"nodeFrom" : "rrb- \*",
		"data" : {}
		},
		{
		"nodeTo" : "charat",
		"nodeFrom" : "rrb- \*",
		"data" : {}
		},
		{
		"nodeTo" : "hash",
		"nodeFrom" : "rrb- \*",
		"data" : {}
		},
		]
	},
	{
		"id" : "existing java hashcode function",
		"name" : "existing java hashcode function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "function",
		"nodeFrom" : "existing java hashcode function",
		"data" : {}
		},
		]
	},
	{
		"id" : "value",
		"name" : "value",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "string",
		"nodeFrom" : "value",
		"data" : {}
		},
		]
	},
	{
		"id" : "i -lrb- str",
		"name" : "i -lrb- str",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "collision",
		"name" : "collision",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "prime number",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "th character",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "execution time and number",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "00 000 and 700 000 strings",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "length",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "table",
		"nodeFrom" : "collision",
		"data" : {}
		},
		{
		"nodeTo" : "s. 31",
		"nodeFrom" : "collision",
		"data" : {}
		},
		]
	},
	{
		"id" : "collisions and execution time",
		"name" : "collisions and execution time",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "much performance",
		"nodeFrom" : "collisions and execution time",
		"data" : {}
		},
		{
		"nodeTo" : "code function",
		"nodeFrom" : "collisions and execution time",
		"data" : {}
		},
		{
		"nodeTo" : "other hash code functions",
		"nodeFrom" : "collisions and execution time",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "collisions and execution time",
		"data" : {}
		},
		{
		"nodeTo" : "cases java hash",
		"nodeFrom" : "collisions and execution time",
		"data" : {}
		},
		]
	},
	{
		"id" : "returned hash value",
		"name" : "returned hash value",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "simple understanding",
		"nodeFrom" : "returned hash value",
		"data" : {}
		},
		]
	},
	{
		"id" : "sample java code",
		"name" : "sample java code",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "algorithm",
		"nodeFrom" : "sample java code",
		"data" : {}
		},
		]
	},
	{
		"id" : "i + +",
		"name" : "i + +",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "length",
		"nodeFrom" : "i + +",
		"data" : {}
		},
		{
		"nodeTo" : "len",
		"nodeFrom" : "i + +",
		"data" : {}
		},
		{
		"nodeTo" : "int i = 0",
		"nodeFrom" : "i + +",
		"data" : {}
		},
		]
	},
	{
		"id" : "current java hash function",
		"name" : "current java hash function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "computer",
		"nodeFrom" : "current java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "result",
		"nodeFrom" : "current java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "modified java hash function",
		"nodeFrom" : "current java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "ram",
		"nodeFrom" : "current java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "core2duo 2.0 processor",
		"nodeFrom" : "current java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "current java hash function",
		"data" : {}
		},
		]
	},
	{
		"id" : "other hash code functions",
		"name" : "other hash code functions",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "code function",
		"nodeFrom" : "other hash code functions",
		"data" : {}
		},
		{
		"nodeTo" : "much performance",
		"nodeFrom" : "other hash code functions",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "other hash code functions",
		"data" : {}
		},
		{
		"nodeTo" : "comparison java hash code",
		"nodeFrom" : "other hash code functions",
		"data" : {}
		},
		{
		"nodeTo" : "collisions and execution time",
		"nodeFrom" : "other hash code functions",
		"data" : {}
		},
		{
		"nodeTo" : "cases java hash",
		"nodeFrom" : "other hash code functions",
		"data" : {}
		},
		]
	},
	{
		"id" : "hash",
		"name" : "hash",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "-rcb-",
		"nodeFrom" : "hash",
		"data" : {}
		},
		{
		"nodeTo" : "charat",
		"nodeFrom" : "hash",
		"data" : {}
		},
		{
		"nodeTo" : "int",
		"nodeFrom" : "hash",
		"data" : {}
		},
		]
	},
	{
		"id" : "int",
		"name" : "int",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "i -lrb- = l ; i -lrb-",
		"name" : "i -lrb- = l ; i -lrb-",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "for",
		"nodeFrom" : "i -lrb- = l ; i -lrb-",
		"data" : {}
		},
		{
		"nodeTo" : "int i",
		"nodeFrom" : "i -lrb- = l ; i -lrb-",
		"data" : {}
		},
		]
	},
	{
		"id" : "number",
		"name" : "number",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "modification",
		"nodeFrom" : "number",
		"data" : {}
		},
		{
		"nodeTo" : "32 and 64 characters",
		"nodeFrom" : "number",
		"data" : {}
		},
		{
		"nodeTo" : "idea",
		"nodeFrom" : "number",
		"data" : {}
		},
		{
		"nodeTo" : "our comparison",
		"nodeFrom" : "number",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "number",
		"data" : {}
		},
		{
		"nodeTo" : "current java hashcode function",
		"nodeFrom" : "number",
		"data" : {}
		},
		{
		"nodeTo" : "character",
		"nodeFrom" : "number",
		"data" : {}
		},
		{
		"nodeTo" : "algorithm",
		"nodeFrom" : "number",
		"data" : {}
		},
		]
	},
	{
		"id" : "table",
		"name" : "table",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "collision",
		"nodeFrom" : "table",
		"data" : {}
		},
		{
		"nodeTo" : "function",
		"nodeFrom" : "table",
		"data" : {}
		},
		]
	},
	{
		"id" : "int i",
		"name" : "int i",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "much time",
		"name" : "much time",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "same inputs",
		"nodeFrom" : "much time",
		"data" : {}
		},
		{
		"nodeTo" : "java hashcode function",
		"nodeFrom" : "much time",
		"data" : {}
		},
		{
		"nodeTo" : "ap hash function",
		"nodeFrom" : "much time",
		"data" : {}
		},
		{
		"nodeTo" : "execution",
		"nodeFrom" : "much time",
		"data" : {}
		},
		]
	},
	{
		"id" : "comparison",
		"name" : "comparison",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "function and ap function",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "computer",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "result",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "execution time",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "modified java hash function",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "ram",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "reason key factor",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "core2duo 2.0 processor",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		{
		"nodeTo" : "java hash",
		"nodeFrom" : "comparison",
		"data" : {}
		},
		]
	},
	{
		"id" : "function and ap function",
		"name" : "function and ap function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "execution time",
		"nodeFrom" : "function and ap function",
		"data" : {}
		},
		{
		"nodeTo" : "reason key factor",
		"nodeFrom" : "function and ap function",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "function and ap function",
		"data" : {}
		},
		{
		"nodeTo" : "java hash",
		"nodeFrom" : "function and ap function",
		"data" : {}
		},
		]
	},
	{
		"id" : "our comparison",
		"name" : "our comparison",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "32 and 64 characters",
		"nodeFrom" : "our comparison",
		"data" : {}
		},
		{
		"nodeTo" : "idea",
		"nodeFrom" : "our comparison",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "our comparison",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "our comparison",
		"data" : {}
		},
		{
		"nodeTo" : "character",
		"nodeFrom" : "our comparison",
		"data" : {}
		},
		{
		"nodeTo" : "algorithm",
		"nodeFrom" : "our comparison",
		"data" : {}
		},
		]
	},
	{
		"id" : "modified java",
		"name" : "modified java",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "function",
		"nodeFrom" : "modified java",
		"data" : {}
		},
		]
	},
	{
		"id" : "method invocation",
		"name" : "method invocation",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "character",
		"nodeFrom" : "method invocation",
		"data" : {}
		},
		]
	},
	{
		"id" : "table 2",
		"name" : "table 2",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "result",
		"nodeFrom" : "table 2",
		"data" : {}
		},
		{
		"nodeTo" : "string length",
		"nodeFrom" : "table 2",
		"data" : {}
		},
		]
	},
	{
		"id" : "uniform distribution shifting",
		"name" : "uniform distribution shifting",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "side",
		"nodeFrom" : "uniform distribution shifting",
		"data" : {}
		},
		{
		"nodeTo" : "character",
		"nodeFrom" : "uniform distribution shifting",
		"data" : {}
		},
		]
	},
	{
		"id" : "much performance",
		"name" : "much performance",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "code function",
		"nodeFrom" : "much performance",
		"data" : {}
		},
		{
		"nodeTo" : "other hash code functions",
		"nodeFrom" : "much performance",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "much performance",
		"data" : {}
		},
		{
		"nodeTo" : "collisions and execution time",
		"nodeFrom" : "much performance",
		"data" : {}
		},
		{
		"nodeTo" : "cases java hash",
		"nodeFrom" : "much performance",
		"data" : {}
		},
		]
	},
	{
		"id" : "computer",
		"name" : "computer",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "result",
		"nodeFrom" : "computer",
		"data" : {}
		},
		{
		"nodeTo" : "current java hash function",
		"nodeFrom" : "computer",
		"data" : {}
		},
		{
		"nodeTo" : "execution time",
		"nodeFrom" : "computer",
		"data" : {}
		},
		{
		"nodeTo" : "modified java hash function",
		"nodeFrom" : "computer",
		"data" : {}
		},
		{
		"nodeTo" : "ram",
		"nodeFrom" : "computer",
		"data" : {}
		},
		{
		"nodeTo" : "performance",
		"nodeFrom" : "computer",
		"data" : {}
		},
		{
		"nodeTo" : "core2duo 2.0 processor",
		"nodeFrom" : "computer",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "computer",
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
		"nodeTo" : "computer",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "current java hash function",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "modified java hash function",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "ram",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "table 2",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "core2duo 2.0 processor",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "string length",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "result",
		"data" : {}
		},
		]
	},
	{
		"id" : "idea",
		"name" : "idea",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "32 and 64 characters",
		"nodeFrom" : "idea",
		"data" : {}
		},
		{
		"nodeTo" : "our comparison",
		"nodeFrom" : "idea",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "idea",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "idea",
		"data" : {}
		},
		{
		"nodeTo" : "character",
		"nodeFrom" : "idea",
		"data" : {}
		},
		{
		"nodeTo" : "algorithm",
		"nodeFrom" : "idea",
		"data" : {}
		},
		]
	},
	{
		"id" : "execution time",
		"name" : "execution time",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "java hash code function",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		{
		"nodeTo" : "computer",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		{
		"nodeTo" : "function and ap function",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		{
		"nodeTo" : "performance",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		{
		"nodeTo" : "reason key factor",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		{
		"nodeTo" : "ap hash function",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		{
		"nodeTo" : "java hash",
		"nodeFrom" : "execution time",
		"data" : {}
		},
		]
	},
	{
		"id" : "charat",
		"name" : "charat",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "-rcb-",
		"nodeFrom" : "charat",
		"data" : {}
		},
		{
		"nodeTo" : "hash",
		"nodeFrom" : "charat",
		"data" : {}
		},
		{
		"nodeTo" : "rrb- \*",
		"nodeFrom" : "charat",
		"data" : {}
		},
		{
		"nodeTo" : ". -rcb- .",
		"nodeFrom" : "charat",
		"data" : {}
		},
		]
	},
	{
		"id" : "prime number",
		"name" : "prime number",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "2-bit int addition",
		"nodeFrom" : "prime number",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "prime number",
		"data" : {}
		},
		{
		"nodeTo" : "th character",
		"nodeFrom" : "prime number",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "prime number",
		"data" : {}
		},
		{
		"nodeTo" : "length",
		"nodeFrom" : "prime number",
		"data" : {}
		},
		{
		"nodeTo" : "s. 31",
		"nodeFrom" : "prime number",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "prime number",
		"data" : {}
		},
		]
	},
	{
		"id" : "comparison java hash code",
		"name" : "comparison java hash code",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "other hash code functions",
		"nodeFrom" : "comparison java hash code",
		"data" : {}
		},
		]
	},
	{
		"id" : "00 000 and 700 000 strings",
		"name" : "00 000 and 700 000 strings",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "hash function",
		"nodeFrom" : "00 000 and 700 000 strings",
		"data" : {}
		},
		{
		"nodeTo" : "execution time and number",
		"nodeFrom" : "00 000 and 700 000 strings",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "00 000 and 700 000 strings",
		"data" : {}
		},
		]
	},
	{
		"id" : "java hashcode function",
		"name" : "java hashcode function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "aphash",
		"nodeFrom" : "java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "performance",
		"nodeFrom" : "java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "same inputs",
		"nodeFrom" : "java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "ap hash function",
		"nodeFrom" : "java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "much time",
		"nodeFrom" : "java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "function",
		"nodeFrom" : "java hashcode function",
		"data" : {}
		},
		{
		"nodeTo" : "execution",
		"nodeFrom" : "java hashcode function",
		"data" : {}
		},
		]
	},
	{
		"id" : "int i = 0",
		"name" : "int i = 0",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "len",
		"nodeFrom" : "int i = 0",
		"data" : {}
		},
		]
	},
	{
		"id" : "string length",
		"name" : "string length",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "result",
		"nodeFrom" : "string length",
		"data" : {}
		},
		]
	},
	{
		"id" : "bellow",
		"name" : "bellow",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "other hash functions",
		"nodeFrom" : "bellow",
		"data" : {}
		},
		]
	},
	{
		"id" : "character",
		"name" : "character",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "idea",
		"nodeFrom" : "character",
		"data" : {}
		},
		{
		"nodeTo" : "32 and 64 characters",
		"nodeFrom" : "character",
		"data" : {}
		},
		{
		"nodeTo" : "our comparison",
		"nodeFrom" : "character",
		"data" : {}
		},
		{
		"nodeTo" : "side",
		"nodeFrom" : "character",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "character",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "character",
		"data" : {}
		},
		{
		"nodeTo" : "algorithm",
		"nodeFrom" : "character",
		"data" : {}
		},
		]
	},
	{
		"id" : "algorithm",
		"name" : "algorithm",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "32 and 64 characters",
		"nodeFrom" : "algorithm",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "algorithm",
		"data" : {}
		},
		{
		"nodeTo" : "me arash partow",
		"nodeFrom" : "algorithm",
		"data" : {}
		},
		]
	},
	{
		"id" : "execution",
		"name" : "execution",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "same inputs",
		"nodeFrom" : "execution",
		"data" : {}
		},
		]
	},
	{
		"id" : "aphash",
		"name" : "aphash",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "performance",
		"nodeFrom" : "aphash",
		"data" : {}
		},
		{
		"nodeTo" : "java hashcode function",
		"nodeFrom" : "aphash",
		"data" : {}
		},
		]
	},
	{
		"id" : "-rcb-",
		"name" : "-rcb-",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "charat",
		"nodeFrom" : "-rcb-",
		"data" : {}
		},
		{
		"nodeTo" : "hash",
		"nodeFrom" : "-rcb-",
		"data" : {}
		},
		{
		"nodeTo" : "rrb- \*",
		"nodeFrom" : "-rcb-",
		"data" : {}
		},
		{
		"nodeTo" : "hash = h",
		"nodeFrom" : "-rcb-",
		"data" : {}
		},
		]
	},
	{
		"id" : "* h + s. charat",
		"name" : "* h + s. charat",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "s. charat",
		"nodeFrom" : "* h + s. charat",
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
		"nodeTo" : "current java hashcode function",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "modification",
		"data" : {}
		},
		{
		"nodeTo" : "iteration",
		"nodeFrom" : "modification",
		"data" : {}
		},
		]
	},
	{
		"id" : "side",
		"name" : "side",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "uniform distribution shifting",
		"nodeFrom" : "side",
		"data" : {}
		},
		{
		"nodeTo" : "character",
		"nodeFrom" : "side",
		"data" : {}
		},
		]
	},
	{
		"id" : "performance",
		"name" : "performance",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "computer",
		"nodeFrom" : "performance",
		"data" : {}
		},
		{
		"nodeTo" : "aphash",
		"nodeFrom" : "performance",
		"data" : {}
		},
		{
		"nodeTo" : "execution time",
		"nodeFrom" : "performance",
		"data" : {}
		},
		{
		"nodeTo" : "java hashcode function",
		"nodeFrom" : "performance",
		"data" : {}
		},
		]
	},
	{
		"id" : "same inputs",
		"name" : "same inputs",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "java hashcode function",
		"nodeFrom" : "same inputs",
		"data" : {}
		},
		{
		"nodeTo" : "much time",
		"nodeFrom" : "same inputs",
		"data" : {}
		},
		{
		"nodeTo" : "ap hash function",
		"nodeFrom" : "same inputs",
		"data" : {}
		},
		]
	},
	{
		"id" : "length",
		"name" : "length",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "2-bit int addition",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "rrb-",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "prime number",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "th character",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "s. 31",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "length",
		"data" : {}
		},
		{
		"nodeTo" : "i + +",
		"nodeFrom" : "length",
		"data" : {}
		},
		]
	},
	{
		"id" : "hash = h",
		"name" : "hash = h",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "s. charat",
		"name" : "s. charat",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "* h + val",
		"name" : "* h + val",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "off + + -rrb-",
		"nodeFrom" : "* h + val",
		"data" : {}
		},
		]
	},
	{
		"id" : "core2duo 2.0 processor",
		"name" : "core2duo 2.0 processor",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "computer",
		"nodeFrom" : "core2duo 2.0 processor",
		"data" : {}
		},
		{
		"nodeTo" : "result",
		"nodeFrom" : "core2duo 2.0 processor",
		"data" : {}
		},
		{
		"nodeTo" : "current java hash function",
		"nodeFrom" : "core2duo 2.0 processor",
		"data" : {}
		},
		{
		"nodeTo" : "modified java hash function",
		"nodeFrom" : "core2duo 2.0 processor",
		"data" : {}
		},
		{
		"nodeTo" : "ram",
		"nodeFrom" : "core2duo 2.0 processor",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "core2duo 2.0 processor",
		"data" : {}
		},
		]
	},
	{
		"id" : "s. 31",
		"name" : "s. 31",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "2-bit int addition",
		"nodeFrom" : "s. 31",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "s. 31",
		"data" : {}
		},
		{
		"nodeTo" : "prime number",
		"nodeFrom" : "s. 31",
		"data" : {}
		},
		{
		"nodeTo" : "th character",
		"nodeFrom" : "s. 31",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "s. 31",
		"data" : {}
		},
		{
		"nodeTo" : "length",
		"nodeFrom" : "s. 31",
		"data" : {}
		},
		]
	},
	{
		"id" : "cases java hash",
		"name" : "cases java hash",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "code function",
		"nodeFrom" : "cases java hash",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "cases java hash",
		"data" : {}
		},
		]
	},
	{
		"id" : "code function",
		"name" : "code function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "much performance",
		"nodeFrom" : "code function",
		"data" : {}
		},
		{
		"nodeTo" : "other hash code functions",
		"nodeFrom" : "code function",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "code function",
		"data" : {}
		},
		{
		"nodeTo" : "collisions and execution time",
		"nodeFrom" : "code function",
		"data" : {}
		},
		{
		"nodeTo" : "cases java hash",
		"nodeFrom" : "code function",
		"data" : {}
		},
		]
	},
	{
		"id" : "th character",
		"name" : "th character",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "2-bit int addition",
		"nodeFrom" : "th character",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "th character",
		"data" : {}
		},
		{
		"nodeTo" : "prime number",
		"nodeFrom" : "th character",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "th character",
		"data" : {}
		},
		{
		"nodeTo" : "length",
		"nodeFrom" : "th character",
		"data" : {}
		},
		{
		"nodeTo" : "s. 31",
		"nodeFrom" : "th character",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "th character",
		"data" : {}
		},
		]
	},
	{
		"id" : "execution time and number",
		"name" : "execution time and number",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "hash function",
		"nodeFrom" : "execution time and number",
		"data" : {}
		},
		{
		"nodeTo" : "00 000 and 700 000 strings",
		"nodeFrom" : "execution time and number",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "execution time and number",
		"data" : {}
		},
		]
	},
	{
		"id" : "reason key factor",
		"name" : "reason key factor",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "function and ap function",
		"nodeFrom" : "reason key factor",
		"data" : {}
		},
		{
		"nodeTo" : "execution time",
		"nodeFrom" : "reason key factor",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "reason key factor",
		"data" : {}
		},
		{
		"nodeTo" : "java hash",
		"nodeFrom" : "reason key factor",
		"data" : {}
		},
		]
	},
	{
		"id" : "len",
		"name" : "len",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "i + +",
		"nodeFrom" : "len",
		"data" : {}
		},
		]
	},
	{
		"id" : ". -rcb- .",
		"name" : ". -rcb- .",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "other hash functions",
		"name" : "other hash functions",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "string str",
		"name" : "string str",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "java hash code function",
		"name" : "java hash code function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "execution time",
		"nodeFrom" : "java hash code function",
		"data" : {}
		},
		{
		"nodeTo" : "ap hash function",
		"nodeFrom" : "java hash code function",
		"data" : {}
		},
		]
	},
	{
		"id" : "simple understanding",
		"name" : "simple understanding",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "follows",
		"nodeFrom" : "simple understanding",
		"data" : {}
		},
		]
	},
	{
		"id" : "32 and 64 characters",
		"name" : "32 and 64 characters",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "idea",
		"nodeFrom" : "32 and 64 characters",
		"data" : {}
		},
		{
		"nodeTo" : "our comparison",
		"nodeFrom" : "32 and 64 characters",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "32 and 64 characters",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "32 and 64 characters",
		"data" : {}
		},
		{
		"nodeTo" : "character",
		"nodeFrom" : "32 and 64 characters",
		"data" : {}
		},
		{
		"nodeTo" : "algorithm",
		"nodeFrom" : "32 and 64 characters",
		"data" : {}
		},
		]
	},
	{
		"id" : "off + + -rrb-",
		"name" : "off + + -rrb-",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "modified java hash function",
		"name" : "modified java hash function",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "computer",
		"nodeFrom" : "modified java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "result",
		"nodeFrom" : "modified java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "current java hash function",
		"nodeFrom" : "modified java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "ram",
		"nodeFrom" : "modified java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "core2duo 2.0 processor",
		"nodeFrom" : "modified java hash function",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "modified java hash function",
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
		"nodeTo" : "code function",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "much performance",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "2-bit int addition",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "other hash code functions",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "prime number",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "th character",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "string",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "length",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "s. 31",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "term",
		"data" : {}
		},
		{
		"nodeTo" : "collisions and execution time",
		"nodeFrom" : "term",
		"data" : {}
		},
		]
	},
	{
		"id" : "ram",
		"name" : "ram",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "calculation",
		"nodeFrom" : "ram",
		"data" : {}
		},
		{
		"nodeTo" : "computer",
		"nodeFrom" : "ram",
		"data" : {}
		},
		{
		"nodeTo" : "result",
		"nodeFrom" : "ram",
		"data" : {}
		},
		{
		"nodeTo" : "current java hash function",
		"nodeFrom" : "ram",
		"data" : {}
		},
		{
		"nodeTo" : "modified java hash function",
		"nodeFrom" : "ram",
		"data" : {}
		},
		{
		"nodeTo" : "core2duo 2.0 processor",
		"nodeFrom" : "ram",
		"data" : {}
		},
		{
		"nodeTo" : "comparison",
		"nodeFrom" : "ram",
		"data" : {}
		},
		]
	},
	{
		"id" : "string",
		"name" : "string",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "2-bit int addition",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "idea",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "prime number",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "th character",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "number",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "character",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "algorithm",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "32 and 64 characters",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "term",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "our comparison",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "length",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "s. 31",
		"nodeFrom" : "string",
		"data" : {}
		},
		{
		"nodeTo" : "collision",
		"nodeFrom" : "string",
		"data" : {}
		},
		]
	},
	{
		"id" : "me arash partow",
		"name" : "me arash partow",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "java hash",
		"name" : "java hash",
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
var actualpath = "G:/Education/Level 4/Advanced Database/Database H2/Comparison Java Hash code function Other Hash Code function1.docx";	 
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
