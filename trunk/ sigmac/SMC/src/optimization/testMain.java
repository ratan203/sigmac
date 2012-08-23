/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

import java.util.HashMap;
import smc.Title;
import smc.XMLFormattingExtractor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.didion.jwnl.JWNLException;

/**
 *
 * @author Thilina
 */
public class testMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JWNLException, FileNotFoundException{
        // TODO code application logic here
        JWNLOps jnw=new JWNLOps();
        String s1;
        s1=jnw.getMorphologicalRoot("programming languages ");
        Boolean is=jnw.assertIsRel("java", "object-oriented programming languages");
        Boolean part=jnw.assertPartOfRel("horn", "car");

        System.out.println(is);
        System.out.println(part);
//        for(String s : s1){
        System.out.println("-"+s1+"-");
//        }
//        if(s1.size()>0){
//        for(String s : s1){
////            System.out.println("Morphological Root : "+s);
            Set<String> s2=new HashSet<String>();
            s2=jnw.getHypernyms("java");
            if(s2.size()>0){
                for(String s3 : s2){
                    System.out.println("Meronyms (Has parts) : "+s3);
                }
            }
//        }
//        }else{
//            System.out.println("No Result");
//        }

//        XMLFormattingExtractor xml=new XMLFormattingExtractor();
//        HashMap<String, Title> al1= xml.getXMLFormatting("testDocx.xml");
//         for(String a:al1.keySet()){
//             System.out.println(a);
//             System.out.println(al1.get(a).getTitleStrength());
//             for(String b:al1.get(a).getTitleSet()){
//                 System.out.println(b);
//             }
//         }
    }

}
