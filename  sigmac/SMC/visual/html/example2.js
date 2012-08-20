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


function init(){
  // init data
  
var json = [
	{
		"id" : "technique",
		"name" : "technique",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "technique",
		"data" : {}
		}		]
	},
	{
		"id" : "need",
		"name" : "need",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation extraction",
		"nodeFrom" : "need",
		"data" : {}
		}		]
	},
	{
		"id" : "anaphor",
		"name" : "anaphor",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "anaphora resolution",
		"nodeFrom" : "anaphor",
		"data" : {}
		}		]
	},
	{
		"id" : "relex",
		"name" : "relex",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation extractor",
		"nodeFrom" : "relex",
		"data" : {}
		},
		{
		"nodeTo" : "relation",
		"nodeFrom" : "relex",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "relex",
		"data" : {}
		}		]
	},
	{
		"id" : "work-flow",
		"name" : "work-flow",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "work-flow",
		"data" : {}
		}		]
	},
	{
		"id" : "tag",
		"name" : "tag",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "tag",
		"data" : {}
		}		]
	},
	{
		"id" : "tool",
		"name" : "tool",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "natural language processing",
		"nodeFrom" : "tool",
		"data" : {}
		},
		{
		"nodeTo" : "relation",
		"nodeFrom" : "tool",
		"data" : {}
		}		]
	},
	{
		"id" : "entity",
		"name" : "entity",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "reference",
		"nodeFrom" : "entity",
		"data" : {}
		}		]
	},
	{
		"id" : "type",
		"name" : "type",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "type",
		"data" : {}
		}		]
	},
	{
		"id" : "opencog 's",
		"name" : "opencog 's",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "opencog 's",
		"data" : {}
		}		]
	},
	{
		"id" : "correct sense",
		"name" : "correct sense",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "word sense disambiguation",
		"nodeFrom" : "correct sense",
		"data" : {}
		}		]
	},
	{
		"id" : "generic approaches",
		"name" : "generic approaches",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation extraction",
		"nodeFrom" : "generic approaches",
		"data" : {}
		}		]
	},
	{
		"id" : "level",
		"name" : "level",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "level",
		"data" : {}
		}		]
	},
	{
		"id" : "pre-defined set",
		"name" : "pre-defined set",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "k",
		"nodeFrom" : "pre-defined set",
		"data" : {}
		}		]
	},
	{
		"id" : "extractor",
		"name" : "extractor",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "extractor",
		"data" : {}
		}		]
	},
	{
		"id" : "nlp techniques",
		"name" : "nlp techniques",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "natural language processing",
		"nodeFrom" : "nlp techniques",
		"data" : {}
		}		]
	},
	{
		"id" : "occurrence",
		"name" : "occurrence",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "occurrence",
		"data" : {}
		}		]
	},
	{
		"id" : "nlp",
		"name" : "nlp",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "natural language processing",
		"nodeFrom" : "nlp",
		"data" : {}
		},
		{
		"nodeTo" : "ontology",
		"nodeFrom" : "nlp",
		"data" : {}
		},
		{
		"nodeTo" : "anaphora resolution",
		"nodeFrom" : "nlp",
		"data" : {}
		}		]
	},
	{
		"id" : "\/ \/ identify noun posnoun_number",
		"name" : "\/ \/ identify noun posnoun_number",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "\/ \/ identify noun posnoun_number",
		"data" : {}
		}		]
	},
	{
		"id" : "text",
		"name" : "text",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "text",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "text",
		"data" : {}
		}		]
	},
	{
		"id" : "different senses",
		"name" : "different senses",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "wordnet",
		"nodeFrom" : "different senses",
		"data" : {}
		}		]
	},
	{
		"id" : "phrase structure tree",
		"name" : "phrase structure tree",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "stanford parser",
		"nodeFrom" : "phrase structure tree",
		"data" : {}
		}		]
	},
	{
		"id" : "relation",
		"name" : "relation",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relex",
		"nodeFrom" : "relation",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "relation",
		"data" : {}
		}		]
	},
	{
		"id" : "gate",
		"name" : "gate",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relex",
		"nodeFrom" : "gate",
		"data" : {}
		},
		]
	},
	{
		"id" : "bioinformatics field",
		"name" : "bioinformatics field",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "bioinformatics field",
		"data" : {}
		},
		]
	},
	{
		"id" : "collocation",
		"name" : "collocation",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "concept extraction",
		"nodeFrom" : "collocation",
		"data" : {}
		}		]
	},
	{
		"id" : "speech",
		"name" : "speech",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "speech",
		"data" : {}
		}		]
	},
	{
		"id" : "pos tag patterns",
		"name" : "pos tag patterns",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "pos tag patterns",
		"data" : {}
		}		]
	},
	{
		"id" : "major problem",
		"name" : "major problem",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "major problem",
		"data" : {}
		}		]
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
		"nodeTo" : "word sense disambiguation",
		"nodeFrom" : "word",
		"data" : {}
		},
		{
		"nodeTo" : "concept identification",
		"nodeFrom" : "word",
		"data" : {}
		},
		{
		"nodeTo" : "wordnet",
		"nodeFrom" : "word",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "word",
		"data" : {}
		}		]
	},
	{
		"id" : "po",
		"name" : "po",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "sentence boundary detection",
		"nodeFrom" : "po",
		"data" : {}
		},
		{
		"nodeTo" : "relation",
		"nodeFrom" : "po",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "po",
		"data" : {}
		}		]
	},
	{
		"id" : "multi term concepts",
		"name" : "multi term concepts",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "multi term concepts",
		"data" : {}
		}		]
	},
	{
		"id" : "output",
		"name" : "output",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relex",
		"nodeFrom" : "output",
		"data" : {}
		},
		]
	},
	{
		"id" : "english",
		"name" : "english",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "english",
		"data" : {}
		}		]
	},
	{
		"id" : "parser",
		"name" : "parser",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "parser",
		"data" : {}
		},
		]
	},
	{
		"id" : "relation extractor",
		"name" : "relation extractor",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relex",
		"nodeFrom" : "relation extractor",
		"data" : {}
		},
		]
	},
	{
		"id" : "other syntax",
		"name" : "other syntax",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "other syntax",
		"data" : {}
		}		]
	},
	{
		"id" : "native speaker",
		"name" : "native speaker",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "native speaker",
		"data" : {}
		}		]
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
		"nodeTo" : "method",
		"nodeFrom" : "result",
		"data" : {}
		},
		{
		"nodeTo" : "reference",
		"nodeFrom" : "result",
		"data" : {}
		}		]
	},
	{
		"id" : "blind frequency analysis",
		"name" : "blind frequency analysis",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "blind frequency analysis",
		"data" : {}
		}		]
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
		"nodeTo" : "relation",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "method",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "concept",
		"data" : {}
		},
		{
		"nodeTo" : "k",
		"nodeFrom" : "concept",
		"data" : {}
		}		]
	},
	{
		"id" : "input sentence",
		"name" : "input sentence",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relex",
		"nodeFrom" : "input sentence",
		"data" : {}
		},
		{
		"nodeTo" : "wordnet",
		"nodeFrom" : "input sentence",
		"data" : {}
		}		]
	},
	{
		"id" : "det",
		"name" : "det",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "det",
		"data" : {}
		},
		]
	},
	{
		"id" : "sentence",
		"name" : "sentence",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "pos tag signatures",
		"nodeFrom" : "sentence",
		"data" : {}
		},
		{
		"nodeTo" : "relation",
		"nodeFrom" : "sentence",
		"data" : {}
		},
		{
		"nodeTo" : "concept identification",
		"nodeFrom" : "sentence",
		"data" : {}
		},
		{
		"nodeTo" : "method",
		"nodeFrom" : "sentence",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "sentence",
		"data" : {}
		},
		{
		"nodeTo" : "wsd",
		"nodeFrom" : "sentence",
		"data" : {}
		}		]
	},
	{
		"id" : "frequency",
		"name" : "frequency",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "frequency",
		"data" : {}
		}		]
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
		"nodeTo" : "reference",
		"nodeFrom" : "algorithm",
		"data" : {}
		}		]
	},
	{
		"id" : "such frames",
		"name" : "such frames",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "such frames",
		"data" : {}
		}		]
	},
	{
		"id" : "drawback",
		"name" : "drawback",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "drawback",
		"data" : {}
		}		]
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
		"nodeTo" : "natural language processing",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "semantic data source",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "collocation identification",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "relation",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "concept identification",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "method",
		"nodeFrom" : "document",
		"data" : {}
		},
		{
		"nodeTo" : "concept extraction",
		"nodeFrom" : "document",
		"data" : {}
		}		]
	},
	{
		"id" : "particular domain",
		"name" : "particular domain",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "concept extraction",
		"nodeFrom" : "particular domain",
		"data" : {}
		}		]
	},
	{
		"id" : "domain",
		"name" : "domain",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "reference",
		"nodeFrom" : "domain",
		"data" : {}
		}		]
	},
	{
		"id" : "complicated problem",
		"name" : "complicated problem",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "anaphora resolution",
		"nodeFrom" : "complicated problem",
		"data" : {}
		}		]
	},
	{
		"id" : "semantic information",
		"name" : "semantic information",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "semantic information",
		"data" : {}
		}		]
	},
	{
		"id" : "noun phrase",
		"name" : "noun phrase",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "anaphora resolution",
		"nodeFrom" : "noun phrase",
		"data" : {}
		}		]
	},
	{
		"id" : "= = = =",
		"name" : "= = = =",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "= = = =",
		"data" : {}
		},
		]
	},
	{
		"id" : "lexas",
		"name" : "lexas",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "word sense disambiguation",
		"nodeFrom" : "lexas",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "lexas",
		"data" : {}
		},
		{
		"nodeTo" : "wsd",
		"nodeFrom" : "lexas",
		"data" : {}
		}		]
	},
	{
		"id" : "task",
		"name" : "task",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relationship extraction",
		"nodeFrom" : "task",
		"data" : {}
		}		]
	},
	{
		"id" : "natural language processing",
		"name" : "natural language processing",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "ontology",
		"nodeFrom" : "natural language processing",
		"data" : {}
		},
		{
		"nodeTo" : "anaphora resolution",
		"nodeFrom" : "natural language processing",
		"data" : {}
		}		]
	},
	{
		"id" : "a",
		"name" : "a",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "a",
		"data" : {}
		},
		]
	},
	{
		"id" : "singular",
		"name" : "singular",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "singular",
		"data" : {}
		},
		]
	},
	{
		"id" : "bio medical sub domains",
		"name" : "bio medical sub domains",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "ontology",
		"nodeFrom" : "bio medical sub domains",
		"data" : {}
		}		]
	},
	{
		"id" : "section",
		"name" : "section",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "natural language processing",
		"nodeFrom" : "section",
		"data" : {}
		},
		]
	},
	{
		"id" : "methodology",
		"name" : "methodology",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relex",
		"nodeFrom" : "methodology",
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
		"nodeTo" : "relex",
		"nodeFrom" : "relationship",
		"data" : {}
		},
		{
		"nodeTo" : "relation",
		"nodeFrom" : "relationship",
		"data" : {}
		},
		]
	},
	{
		"id" : "w",
		"name" : "w",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "w",
		"data" : {}
		}		]
	},
	{
		"id" : "training corpus",
		"name" : "training corpus",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "training corpus",
		"data" : {}
		}		]
	},
	{
		"id" : "t",
		"name" : "t",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "t",
		"data" : {}
		},
		]
	},
	{
		"id" : "uncountable",
		"name" : "uncountable",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "uncountable",
		"data" : {}
		},
		]
	},
	{
		"id" : "noun",
		"name" : "noun",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "noun",
		"data" : {}
		},
		{
		"nodeTo" : "wordnet",
		"nodeFrom" : "noun",
		"data" : {}
		}		]
	},
	{
		"id" : "there",
		"name" : "there",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "wordnet",
		"nodeFrom" : "there",
		"data" : {}
		}		]
	},
	{
		"id" : "approach",
		"name" : "approach",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relationship extraction",
		"nodeFrom" : "approach",
		"data" : {}
		},
		{
		"nodeTo" : "relation",
		"nodeFrom" : "approach",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "approach",
		"data" : {}
		},
		{
		"nodeTo" : "ontology",
		"nodeFrom" : "approach",
		"data" : {}
		}		]
	},
	{
		"id" : "dependency relationship generation",
		"name" : "dependency relationship generation",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "dependency relationship generation",
		"data" : {}
		}		]
	},
	{
		"id" : "synset",
		"name" : "synset",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "synset",
		"data" : {}
		}		]
	},
	{
		"id" : "method",
		"name" : "method",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "method",
		"nodeFrom" : "method",
		"data" : {}
		},
		{
		"nodeTo" : "k",
		"nodeFrom" : "method",
		"data" : {}
		}		]
	},
	{
		"id" : "part",
		"name" : "part",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		]
	},
	{
		"id" : "accuracy",
		"name" : "accuracy",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "semantic data source",
		"nodeFrom" : "accuracy",
		"data" : {}
		},
		{
		"nodeTo" : "reference",
		"nodeFrom" : "accuracy",
		"data" : {}
		}		]
	},
	{
		"id" : "y",
		"name" : "y",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "y",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "y",
		"data" : {}
		}		]
	},
	{
		"id" : "meronym",
		"name" : "meronym",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "part",
		"nodeFrom" : "meronym",
		"data" : {}
		}		]
	},
	{
		"id" : "x",
		"name" : "x",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "relation",
		"nodeFrom" : "x",
		"data" : {}
		},
		{
		"nodeTo" : "part",
		"nodeFrom" : "x",
		"data" : {}
		}		]
	},
	{
		"id" : "other sections",
		"name" : "other sections",
		"data" : {
		"$color" : "#83548B",
		"$type" :  "circle" 
		},
		"adjacencies" : [
		{
		"nodeTo" : "natural language processing",
		"nodeFrom" : "other sections",
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
