import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

class Pagination {
	public static void main(String args[]) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		BigDecimal l = (BigDecimal) entitymanager.createNativeQuery("Select count(1) from APS_CODE_LOOKUP").getSingleResult();

		System.out.println("total count = " + l.intValue());
		
		Query typedQuery = entitymanager.createNativeQuery("Select ID, DESCRIPTION from APS_CODE_LOOKUP order by ID asc");
		int pageNumber = 1;
		int pageSize = 100;
		while (pageNumber  <= l.intValue()) {
			   typedQuery.setFirstResult(pageNumber - 1);
			  
			typedQuery.setMaxResults(pageSize);
			   System.out.println("Current page: " + pageNumber);
			   
			   List<Object[]> listObj = typedQuery.getResultList();
			 //  System.out.println("Page number ="+pageNumber);
			for (Object[] models : listObj) {
				
				System.out.println(models[0].toString());
			}
			   pageNumber += pageSize;
			}
		
		
		entitymanager.close();
		emfactory.close();
	}
}