package SCAnalyzer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import anaphorares.Anaphora;

/**
 *
 * @author Thilina
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Anaphora ana=new Anaphora();
        String txt="Agile Software development method is used to develop the project.  Agile development method is an Iterative, time bound, incremental, convergent, people oriented and collaborative method.  The development team meets frequently to get the ideas about the project like current progress, current issues. Customer collaboration is a major part of agile development and meeting with the customers are done through Skype. Due to incremental development, the changes can be done easily. So the issues are discussed among team members as well as the customer. . This class is used to create a Drag and Drop GUI for adding documents to the system. . This class is used to provide methods used to implement the Drag and Drop GUI. .This is class which is controlled the whole pre possessing part. After identifying the actual file paths of the documents which are dragged to the drag and drop interface, it identifies the file format and load the relevant adaptor for text extraction. . These classes are the core of pre possessing part and there are several classes for several document formats such as pdf,  ppt, doc, docx, pptx, open document stands.  Each of these adaptors extracts the text and identifies the actual titles of the documents.  So the formatting details of each of document types used to identify the titles and the rest of the text which are not titles are considered as the body. . This is the intermediate class for setting and getting titles, body and metadata of the documents. . This class is used to resolve the anaphora in test body extracted from adaptors. . This class is used to create the intermediate XML file from the titles, body and metadata. Used body as the text which is resolved anaphora. ";
        String tmpFile="D:\\test.txt";  //Path to create temporary txt file (delete after processing)
        String anaRes=ana.resolveAnaph(txt,tmpFile);
        System.out.println(anaRes);
    }

}
