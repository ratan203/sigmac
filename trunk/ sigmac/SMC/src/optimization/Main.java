/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimization;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import net.didion.jwnl.JWNLException;

/**
 *
 * @author Thilina
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JWNLException, FileNotFoundException{
        // TODO code application logic here
        jwnlOps jnw=new jwnlOps();
        String s1;
        s1=jnw.getMorphologicalRoot("database management systems");
        Boolean is=jnw.assetIsRel("java", "object-oriented programming languages");
        Boolean part=jnw.assetPartOfRel("car", "bumpers");

        System.out.println(is);
        System.out.println(part);
//        for(String s : s1){
        System.out.println(s1);
//        }
//        if(s1.size()>0){
//        for(String s : s1){
//            System.out.println("Morphological Root : "+s);
//            Set<String> s2=new HashSet<String>();
//            s2=jnw.getHyponyms("database management system");
//            if(s2.size()>0){
//                for(String s3 : s2){
//                    System.out.println("Meronyms (Has parts) : "+s3);
//                }
//            }
//        }
//        }else{
//            System.out.println("No Result");
//        }
    }

}