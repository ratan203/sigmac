package pca;

public class ConceptDoc {
	int conceptId;
	int docId;
	double imp;
	public ConceptDoc(int conceptId,int docId,double imp) {
		// TODO Auto-generated constructor stub
		this.conceptId= conceptId;
		this.docId= docId;
		this.imp= imp;
	}
	
	public double getImp() {
		return imp;
	}
	public void setImp(double imp) {
		this.imp = imp;
	}
	public int getConceptId() {
		return conceptId;
	}
	public void setConceptId(int conceptId) {
		this.conceptId = conceptId;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	

}
