package SCAnalyzer;



import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
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
  
  }

}
