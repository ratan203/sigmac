package smc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnector.DbConnnector;

public class DocObjectCreator {
	
	public Document getDoc(String docURI){
		
		Document doc = new Document();
		DbConnnector dbc = new DbConnnector();
		Connection con = dbc.getConnection();
		Statement stmt;
		int docId = 0;
		int conceptId;
		
		String getDocId = "SELECT docId from document WHERE docURI=\'"+docURI+"\'";

		ArrayList<Concept> concepts = null;
		
			try {
				
				stmt = con.createStatement();
				ResultSet rs1 = stmt.executeQuery(getDocId);
				
				if(rs1.next()){
				docId= rs1.getInt("docId");	
				}
				
				String getConceptId = "SELECT id from conceptdoc WHERE docId="+docId+"";
				ResultSet rs2 = stmt.executeQuery(getConceptId);
				
				// iterate concept ids
					while(rs2.next()){
						
						Concept tempConcept = null;
						
						conceptId = rs2.getInt(1);
						System.out.print(conceptId);
						Statement stmt2 = con.createStatement();
						String getConcepts = "SELECT * from concept WHERE id="+conceptId+"";
						ResultSet rs3 = stmt2.executeQuery(getConcepts);
						
						if(rs3.next()){
							tempConcept = new Concept(rs3.getString(2));
							tempConcept.setFreequency(rs3.getInt(3));
							tempConcept.setStrength(rs3.getInt(4));
						}
						
						//for selecting related concepts
						Statement stmt3 = con.createStatement();
						String getRelConIDs = "SELECT relatioship.type,relatioship.frequency,relatioship.strength,concept.concept, relationship.isHead" +
						"from relatedconcept INNERJOIN relationship ON relatedconcept.relId = relationship INNERJOIN concept ON concept.id=relationship.relConceptId " +
						"WHERE relatedconcept.id="+conceptId+"";
						ResultSet rs4 = stmt3.executeQuery(getRelConIDs);
						
							while(rs4.next()){
								tempConcept.addRelatedConcept(rs4.getString(4), rs4.getString(1), rs4.getBoolean(5), rs4.getInt(2));
								
							}
						
						concepts.add(tempConcept);
					}
				doc.addConcepts(concepts);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		

			
		return null;
		
	}

}
