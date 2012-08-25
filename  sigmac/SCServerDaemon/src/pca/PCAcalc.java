package pca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pca.PCA.PrincipleComponent;

import Jama.Matrix;
import database.DBConnector;



public class PCAcalc {
	private double[][] matrix;
	DBConnector dbc ;
	int noOfConcepts = 0;
	int startConceptId=0;
	int noOfDocuments = 0;
	int startDocumentId=0;
	
	public void createMatrix() throws SQLException, FileNotFoundException{
		dbc = new DBConnector();
		Connection con = dbc.getConnection();		
		Statement stmt ;
		
		
		
		// Retriving the diamentions of the matrix
		String getDiamentions = "SELECT COUNT(DISTINCT conceptId),COUNT(DISTINCT docId),MIN(conceptId),MIN(docId) FROM concept_doc";
		
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(getDiamentions);
			if(rs.next()){
				noOfConcepts = rs.getInt(1);
				noOfDocuments = rs.getInt(2);
				startConceptId = rs.getInt(3);
				startDocumentId = rs.getInt(4);
			}
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			/*try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			matrix = new double[noOfConcepts][noOfDocuments];		
		}
		
		String getConceptDoc;
		ResultSet rs = null ;
		
		int docId;
		
		for(int i=1; i<=noOfConcepts ; i++){
			for(int j=1 ;j<=noOfDocuments;j++){
				 getConceptDoc ="SELECT frequency FROM concept_doc WHERE docId="+(startDocumentId+j-1)+
				 " AND conceptId="+(startConceptId+i-1)+";";
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(getConceptDoc);
				 if(rs.next()){
					 matrix[i-1][j-1]= rs.getInt(1); 				 
				 }
			}
			 			
		}
		
		PrintWriter pw = new PrintWriter(new File("outOriM.txt"));
		for(int i=1; i<=noOfConcepts ; i++){
			for(int j=1 ;j<=noOfDocuments;j++){
				 pw.append(matrix[i-1][j-1]+" ");
				 }
			 pw.append("\n");
		}
		pw.flush();
		pw.close();
	}
		
	public void calcPCA() throws IOException{
		Matrix originalData = new Matrix(matrix);
		PCA pca = new PCA(matrix);
		

		PrintWriter pw = new PrintWriter(new File("out.txt"));
		int numComponents = pca.getNumComponents();
	    pw.append("There are " + numComponents + " components\n");
	    int k = 2;
	    List<PrincipleComponent> mainComponents = pca.getDominantComponents(k);
	    int counter = 1;
	    pw.append("Showing top " + k + " principle components.\n");
	    for (PrincipleComponent pc : mainComponents) {
	      pw.append("Component " + (counter++) + ": " + pc+"\n");
	    }
	    Matrix features = PCA.getDominantComponentsMatrix(mainComponents);
	    pw.append("Feature matrix (k=" + k + ") :");
	    
	    features.print(pw,8, 4);

	    Matrix featuresXpose = features.transpose();
	    pw.append("Xposed feature matrix (k=" + k + ") :");
	    featuresXpose.print(pw,8, 4);

	    double[][] matrixAdjusted = PCA.getMeanAdjusted(matrix, pca.getMeans());
	    Matrix adjustedInput = new Matrix(matrixAdjusted);
	    pw.append("Original input adjusted by dimension means (k=" + k + ") :");
	    pw.append("updating doc ranks");
	    
	    updateDocRanksTable(adjustedInput, startConceptId, startDocumentId);
	    
	    adjustedInput.print(pw,8, 4);
	    Matrix xformedData = featuresXpose.times(adjustedInput.transpose());
	    pw.append("Transformed data into PCA-space (k=" + k + ") :");
	    xformedData.transpose().print(pw,8, 4);

	    k = 1;
	    mainComponents = pca.getDominantComponents(k);
	    counter = 1;
	    pw.append("Showing top " + k + " principle components.\n");
	    for (PrincipleComponent pc : mainComponents) {
	      pw.append("Component " + (counter++) + ": " + pc);
	    }
	    features = PCA.getDominantComponentsMatrix(mainComponents);
	    pw.append("Feature matrix (k=" + k + ") :");
	    features.print(pw,8, 4);

	    featuresXpose = features.transpose();
	    pw.append("Xposed feature matrix (k=" + k + ") :");
	    featuresXpose.print(pw,8, 4);

	    matrixAdjusted = PCA.getMeanAdjusted(matrix, pca.getMeans());
	    adjustedInput = new Matrix(matrixAdjusted);
	    pw.append("Original input adjusted by dimension means (k=" + k + ") :");
	    adjustedInput.print(pw,8, 4);
	    xformedData = featuresXpose.times(adjustedInput.transpose());
	    pw.append("Transformed data into PCA-space (k=" + k + ") :");
	    xformedData.transpose().print(pw,8, 4);
		
	    pw.flush();
	    pw.close();
	}
	
	void updateDocRanksTable(Matrix adjestedMetrix,int startConceptId, int startDocumentId){
		dbc = new DBConnector();
		Connection con = dbc.getConnection();		
		Statement stmt ;
		
		ConceptDoc[] conceptDocArray;
		
		for(int j=0;j<adjestedMetrix.getRowDimension();j++){
			conceptDocArray= new ConceptDoc[adjestedMetrix.getColumnDimension()];
			for(int i=0;i<adjestedMetrix.getColumnDimension();i++){
				conceptDocArray[i]=new ConceptDoc(startConceptId+j, startDocumentId+i,adjestedMetrix.get(j, i));
			}

			ArrayList<ConceptDoc> sortedConDocArray = sortDocArray(conceptDocArray);
			
			for(int i=0;i<sortedConDocArray.size();i++){
			String docInsertingQuery = "INSERT INTO doc_ranks(conceptId,docId,rank) VALUES ("+sortedConDocArray.get(i).conceptId
			+","+sortedConDocArray.get(i).docId+","+(i+1)+")";
		        try {
		        	stmt = con.createStatement();
					stmt.executeUpdate(docInsertingQuery);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	ArrayList<ConceptDoc> sortDocArray(ConceptDoc[] conceptDocArray){
		ArrayList<ConceptDoc> sortedConDocArray = new ArrayList<ConceptDoc>();
		
		//can be improved by ignoring minus values or introdusing new sorting algo
		for(int i=0;i<conceptDocArray.length-1;i++){
			for(int j=0;j<conceptDocArray.length-1-i;j++){
				if(conceptDocArray[j].getImp()<conceptDocArray[j+1].getImp()){
					ConceptDoc temp= conceptDocArray[j];
					conceptDocArray[j] = conceptDocArray[j+1];
					conceptDocArray[j+1] = temp;
				}
			}
		}
		
		for(int i=0;i<conceptDocArray.length;i++){
			if(conceptDocArray[i].getImp()>0){
				sortedConDocArray.add(conceptDocArray[i]);
			}
		}
		
		return sortedConDocArray;
		
	}
}


