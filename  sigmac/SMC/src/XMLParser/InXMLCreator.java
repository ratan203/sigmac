package XMLParser;
	 
import adaptors.SCDocument;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

	 
public class InXMLCreator {
 
	public void createXML(SCDocument document,String outputFilePath, String url,String dateString) throws IOException {
 
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// Document elements
		Document doc = docBuilder.newDocument();
                
                String tmpFile="anaphora//test1.txt";
                String textToResolve=document.getBody().replace("\n", "").replace("\r", "");
                AnaphoraResolver ana=new AnaphoraResolver(textToResolve,tmpFile);
                String anaRes=ana.resolveAnaphora();

                Element rootElement = doc.createElement("document");
		doc.appendChild(rootElement);
                
                //Document Metadata
                
                Element uri = doc.createElement("Url");
                rootElement.appendChild(uri);
                uri.appendChild(doc.createTextNode(url));
                
                Element lastModified = doc.createElement("LastModified");
                rootElement.appendChild(lastModified);
                lastModified.appendChild(doc.createTextNode(dateString));
 
		// body elements
		Element body = doc.createElement("body");
                rootElement.appendChild(body);
		body.appendChild(doc.createTextNode(anaRes));                
//		body.appendChild(doc.createTextNode(document.getBody()));
 
		//Title elements
		Element titles = doc.createElement("titles");
		rootElement.appendChild(titles);
		
		int size = document.getTitles().size();
		for(int i=0 ;i< size;i++){
			Element title = doc.createElement("title");
			titles.appendChild(title);
			title.appendChild(doc.createTextNode(document.getTitles().get(i).getTitleText()));
			
			Attr attr = doc.createAttribute("scale");
			attr.setValue(String.valueOf(document.getTitles().get(i).getScale()));
			title.setAttributeNode(attr);
		}
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(outputFilePath));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}


