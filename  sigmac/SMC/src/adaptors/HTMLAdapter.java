/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adaptors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author user
 */
public class HTMLAdapter {

    public HTMLAdapter(){

    }

    public SCDocument parseHTML(String file) throws IOException{
        File f=new File(file);
        Document doc = Jsoup.parse(f, "UTF-8", "");
        ArrayList<Title> titles=processTitles(doc);
        String body="";
        Elements txt=doc.select("p:matchesOwn(([a-z]|[A-Z])+)");
        for(Element e : txt){
            body+=e.text()+" \n";
        }
        SCDocument document=new SCDocument(body, titles);
        return document;
    }

    private ArrayList<Title> processTitles(Document doc){
        ArrayList<Title> titles=new ArrayList<Title>();
        String htag="h";
        for(int i=1;i<6;i++){
            htag="h"+i;
            Elements h1 = doc.select(htag);
            for(Element h : h1){
                String text=h.text();
                if(text.length()>2){
                    Title title=new Title(purifyTitle(h.text()),6-i);
                    titles.add(title);
                }
            }
            doc.select(htag).remove();
        }
        Elements links = doc.select("a");
        for(Element link : links){
            Title title=new Title(purifyTitle(link.text()), 1);
            titles.add(title);
        }
        Elements bolds = doc.select("b");
        for(Element bold : bolds){
            Title title=new Title(purifyTitle(bold.text()),1);
            titles.add(title);
        }
        return titles;
    }

    private String purifyTitle(String title){
        String cleaned="";
        cleaned = title.replaceAll("[^A-Za-z]+$", "");
        return cleaned;
    }
}
