import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FindModel {
   public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      EntityManager entitymanager = emfactory.createEntityManager();
      Employee employee = entitymanager.find( Employee.class, 238 );

      System.out.println("employee ID = " + employee);
      entitymanager.close();
      emfactory.close();
   }
}