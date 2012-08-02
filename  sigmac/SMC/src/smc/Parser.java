/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package smc;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Parser {

    LexicalizedParser lp;
    TreebankLanguagePack tlp;
    GrammaticalStructureFactory gsf;
    SentenceAnalyzer analyzer;
    StrengthCalculator calc;

    public Parser(String modelFile){
        lp=new LexicalizedParser(modelFile);
        tlp = new PennTreebankLanguagePack();
        gsf = tlp.grammaticalStructureFactory();
        analyzer=new SentenceAnalyzer();
        calc=new StrengthCalculator();
    }

    public void parse(String fileName,DocumentPreprocessor.DocType doctype,
            String delimeter){
        DocumentPreprocessor p=new DocumentPreprocessor(fileName,doctype);
        p.setElementDelimiter(delimeter);
        Document doc=new Document(fileName);
        for (List<HasWord> sentence : p){
            Tree parse = lp.apply(sentence);
            ArrayList<Concept> cons=analyzer.analyze(parse);
//            for(Concept con : cons){
//                con.printConcept();
//            }
//            System.out.println("ddssssssssssssssss");
            doc.addConcepts(cons);
        }
//        System.out.println("done");
        calc.calculateImportance(doc);
        doc.printDoc();
//        try {
//            FileOutputStream fos = new FileOutputStream("doccc.ser");
//            try {
//                ObjectOutputStream oout = new ObjectOutputStream(fos);
//                oout.writeObject(doc);
//                oout.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
