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
		
		String getDocId = "SELECT docId from documents WHERE docURI=\'"+docURI+"\'";

		ArrayList<Concept> concepts = new ArrayList<Concept>();
		
			try {
				
				stmt = con.createStatement();
				ResultSet rs1 = stmt.executeQuery(getDocId);
				
				if(rs1.next()){
				docId= rs1.getInt("docId");	
				}
				
				String getConceptId = "SELECT conceptId from concept_doc WHERE docId="+docId+" AND concept_doc.titleStrength >3";
				ResultSet rs2 = stmt.executeQuery(getConceptId);

				// iterate concept ids
					while(rs2.next()){
						//System.out.println("Concept iteration"+i++);
						Concept tempConcept = null;
						
						conceptId = rs2.getInt(1);
						//System.out.println("ConceptID "+conceptId);
						Statement stmt2 = con.createStatement();
						String getConcepts = "SELECT concepts.conceptName,concept_doc.frequency,concept_doc.strength" +
								" from concepts JOIN concept_doc ON concepts.conceptId = concept_doc.conceptId " +
								" WHERE concepts.conceptId="+conceptId+"";
						ResultSet rs3 = stmt2.executeQuery(getConcepts);
						
							if(rs3.next()){
								tempConcept = new Concept(rs3.getString(1));
								tempConcept.setFreequency(rs3.getInt(2));
								tempConcept.setStrength(rs3.getInt(3));
								
							}
						
						//for selecting related concepts
						Statement stmt3 = con.createStatement();
						/*String getRelConIDs = "SELECT relationships.type,relationships.frequency,relationships.strength,concepts.conceptName," +
						" relationships.isHead FROM related_concept JOIN relationships ON related_concept.relId = relationships.relId JOIN concepts " +
						"ON concepts.conceptId=relationships.conceptId WHERE related_concept.conceptId= "+conceptId+" AND relationships.type !='aso'";*/
						
						String getRelConIDs = "SELECT relationships.type,relationships.frequency,relationships.strength,concepts.conceptName," +
						" relationships.isHead FROM related_concept INNER JOIN relationships ON related_concept.relId = relationships.relId INNER JOIN concepts " +
						"ON concepts.conceptId=relationships.conceptId INNER JOIN concept_doc ON concept_doc.conceptId =concepts.conceptId " +
						"WHERE related_concept.conceptId= "+conceptId+" ";
						
						ResultSet rs4 = stmt3.executeQuery(getRelConIDs);
						
						if (!rs4.next()) continue;
						
						while(rs4.next()){
							tempConcept.addRelatedConcept(rs4.getString(4), rs4.getString(1), rs4.getBoolean(5), rs4.getInt(2));
						}
						if(!tempConcept.getRelationships().isEmpty()){
						concepts.add(tempConcept);
						}					
						}
				doc.addConcepts(concepts);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		

		
		return doc;
		
	}

}
