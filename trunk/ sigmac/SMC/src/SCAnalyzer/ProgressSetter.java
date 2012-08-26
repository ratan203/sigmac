package SCAnalyzer;


import javax.swing.JProgressBar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thilina
 */
public class ProgressSetter implements Runnable{
    private JProgressBar jp;
    private JProgressBar jp1;
    private int limit;
    private int currentFileNo;
    public ProgressSetter(JProgressBar j1,JProgressBar j2,int lim, int noFile) {
        this.jp=j1;
        this.jp1=j2;
        this.limit=lim;
        this.currentFileNo=noFile;
    }

    public void run(){
        for (int i=0; i<=(limit-jp1.getValue()-50)*10; i++){
            jp.setValue(jp.getValue()+i/10);
            jp.repaint();
            jp1.setValue(jp1.getValue()+i/10);
            jp1.repaint();
            try{Thread.sleep(500);}
            catch (InterruptedException err){}
        }
    }

    public void setLimit(int limi){
        this.limit=limi;
    }

    public void stopProgress(){
        jp.setValue((currentFileNo-1)*1000+limit);
        jp.repaint();
        jp1.setValue(limit);
        jp1.repaint();
    }
}
