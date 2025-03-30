package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.ObjectA;
import model.ObjectB;

public class MainClass {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		
		ObjectB b1 = new ObjectB();
		b1.setPNo("ALUPU1526E");
		b1.setAddress("Kotturu");

		ObjectA a1 = new ObjectA();
		a1.setAge(23);
		a1.setPName("ulli sagar");
		a1.setSalary(25000.00);
		a1.setOb(b1);
		
		ObjectB b2 = new ObjectB();
		b2.setPNo("ALUPU1535E");
		b2.setAddress("Kotturu");

		ObjectA a2 = new ObjectA();
		a2.setAge(32);
		a2.setPName("ulli shridhar");
		a2.setSalary(75000.00);
		a2.setOb(b2);
		
				

//		------------------ Persist ------------------

		entityTransaction.begin();
		entityManager.persist(a1);
		entityTransaction.commit();

		entityTransaction.begin();
		entityManager.persist(b1);
		entityTransaction.commit();

		entityTransaction.begin();
		entityManager.persist(a2);
		entityTransaction.commit();

		entityTransaction.begin();
		entityManager.persist(b2);
		entityTransaction.commit();

		
//		------------------ Fetch ------------------

		ObjectA fetchA = entityManager.find(ObjectA.class, "ulli sagar");
		System.out.println(fetchA);

		ObjectB fetchB = entityManager.find(ObjectB.class, "ALUPU1526E");
		System.out.println(fetchB);
		

//		------------------ Update ------------------

		ObjectA updateA = entityManager.find(ObjectA.class, "ulli sagar");
		updateA.setSalary(25000.00);
		entityTransaction.begin();
		entityManager.merge(updateA);
		entityTransaction.commit();

		ObjectB updateB = entityManager.find(ObjectB.class, "ALUPU1526E");
		updateB.setAddress("Bengaluru");
		entityTransaction.begin();
		entityManager.merge(updateB);
		entityTransaction.commit();

//		------------------ Delete ------------------

		ObjectA deleteParent = entityManager.find(ObjectA.class, "ulli shridhar");
		deleteParent.setOb(null);
		entityTransaction.begin();
		entityManager.merge(deleteParent);
		entityTransaction.commit();

		ObjectB deleteChild = entityManager.find(ObjectB.class, "ALUPU1526E");
		entityTransaction.begin();
		entityManager.remove(deleteChild);
		entityTransaction.commit();

	}

}
