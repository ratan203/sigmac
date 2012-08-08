/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
//package chrriis.dj.nativeswing.swtimpl.demo.examples.webbrowser;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * @author Christopher Deckers
 */
 class Web extends JPanel {

  public Web(String path) {
    super(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
 //   webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
    final JWebBrowser webBrowser = new JWebBrowser();
    //webBrowser.setStatusBarVisible(false);
    webBrowser.setLocationBarVisible(false);
    webBrowser.setMenuBarVisible(false);
    
    webBrowser.navigate(path);
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    add(webBrowserPanel, BorderLayout.CENTER);
    webBrowserPanel.setVisible(true);
  //  webBrowserPanel.setBounds(20, 20, 300, 300);
   
  }

}
