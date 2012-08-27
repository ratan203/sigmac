package SCAnalyzer;



import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import java.awt.BorderLayout;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author COMPAQ
 */
 class InnerWeb extends JPanel {

  public InnerWeb(String path) {
    super(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    final JWebBrowser webBrowser = new JWebBrowser();
    webBrowser.setLocationBarVisible(false);
    webBrowser.setMenuBarVisible(false);
    
    webBrowser.navigate(path);
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    
    add(webBrowserPanel, BorderLayout.CENTER);
    webBrowserPanel.setVisible(true);
//  
//      webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
//      @Override
//      public void commandReceived(WebBrowserCommandEvent e) {
//        String command = e.getCommand();
//        Object[] parameters = e.getParameters();
//        if("store".equals(command)) {
//          String data = (String)parameters[0];
//          if(JOptionPane.showConfirmDialog(webBrowser, "Do you want to store \"" + data + "\" in a database?\n(Not for real of course!)", "Data received from the web browser", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//            // Data should be used here
//          }
//        }
//      }
//    });
  }

}
