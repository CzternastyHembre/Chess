package var2019;

import java.util.Collection;

/**
 * A doctor has the capacity to treat one patient at a time.
 * The doctor as a list of competencies (of type String) that
 * indicates what conditions s/he can treat.
 */
public class Doctor {
       // Internal variables go here:
     	private Patient patient;
     	private Collection<String> competencies;
 
       /**     
 * @return the patient this doctor is treating, or null if s/he isn't currently treating any patient.
        */
//       public Patient getPatient() { // 1a
//    	   return patient;
//       }
       
       /**
     * Indicates to what extent this doctor can treat the provided patient.
     * The value is the number of the patient's conditions this doctor
     * can treat divided by the number of conditions the patient has.
     * Conditions and competences are matched using simple String comparison.
     * @param patient
     * @return the ratio of the patient's conditions that this
     * doctor can treat.
     */
       public double canTreat(final Patient patient) { // 2b
    	   double treatables = 0;
    	   
    	   for (String condition: patient) {
    		   if (competencies.stream().anyMatch(comp -> comp.equals(condition))) {
    			   treatables++;
    		   }
    	   }
    	   return treatables / patient.sizeOfConditions();
       }
 
 
       
       
       /**
    * "Treats" the patient by removing all the patient's conditions
    * that this doctor can treat.
    */
       public void treat() { // 2b
    	   for (String condition : patient) {
    		   if (competencies.contains(condition)) {
				patient.removeCondition(condition);
			}
	   }
       }
 
 
       /**
    * @return the patient this doctor is treating, or null if s/he
    * isn't currently treating any patient.
    */
       public Patient getPatient() {
    	   return this.patient;
       }
 
 
       /**
    * @return true if this doctor is currently treating a patient, otherwise
    * false.
    */
       public boolean isAvailable() {
    	   return patient == null;
       }
 
 
       /**
    * Sets the patient that this doctor is treating, use null to indicate
    * s/he isn't currently treating any patient.
    * @param patient
    */
       public void setPatient(final Patient patient) {
    	   this.patient = patient;
       }
}
  
 