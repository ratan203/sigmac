package XMLParser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thilina
 */
public class CoreferenceIdentifier {

    /**
     * Identify each noun phrases in the text and tag them by a corefID
     * If any set of nouns refer same proper noun all of them have same corefID
     * 
     * @param String (Which need to identify co-reference )
     * @param String (Destination to create intermediate .coref file )
     */
    public void resolveAnaph(String Txt, String tempFileDest) {
        try {
            try {
                File file = new File(tempFileDest);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                String text = Txt.replaceAll("\\([^\\(]*?\\)", " ");
                text = text.trim().replace("..", ".").replace("(", " ").replace(")", " ").replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ");
                if(text.charAt(0)=='.'){
                    text=text.substring(1, text.length()-1);
                }
                out.write(text);
                out.newLine();
                out.close();

            } catch (Exception ex) {
                Logger.getLogger(CoreferenceIdentifier.class.getName()).log(Level.SEVERE, null, ex);
            }

            String[] x = new String[1];
            x[0] = tempFileDest;

            List<String> args = new ArrayList<String>();
            args.add("java");
            //set jvm properties
            args.add("-Xmx1g");
            args.add("-jar");
            args.add("anaphora/reconcile-1.0.jar");
            args.add(x[0]);
            System.out.println(x[0]);
            Object[] y = new Object[1];
            y[0] = x[0];

            ProcessBuilder pb = new ProcessBuilder();
            pb.command(args);
            pb.redirectErrorStream(true);

            //Print command prompt output on the console
            try {
                Process p = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                line = "OK";
                line = line.substring(0, 1);
                reader.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
