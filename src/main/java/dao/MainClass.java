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

		entityTransaction.commit();

		ObjectB b = new ObjectB();
		b.setPNo("ALUPU1526E");
		b.setAddress("Kotturu");

		ObjectA a = new ObjectA();
		a.setAge(23);
		a.setPName("ulli sagar");
		a.setSalary(25000.00);
		a.setOb(b);

//		------------------ Persist ------------------

		entityTransaction.begin();
		entityManager.persist(a);
		entityTransaction.commit();

		entityTransaction.begin();
		entityManager.persist(b);
		entityTransaction.commit();

//		------------------ Fetch ------------------

		ObjectA fetchA = entityManager.find(ObjectA.class, "ulli sagar");
		System.out.println(fetchA);

		ObjectB fetchB = entityManager.find(ObjectB.class, "ALUPU1526E");
		System.out.println(fetchB);

//		------------------ Update ------------------

		ObjectA updateA = entityManager.find(ObjectA.class, "ulli sagar");
		updateA.setPName("suprith");
		entityTransaction.begin();
		entityManager.merge(updateA);
		entityTransaction.commit();

		ObjectB updateB = entityManager.find(ObjectB.class, "ALUPU1526E");
		updateB.setAddress("Bengaluru");
		entityTransaction.begin();
		entityManager.merge(updateB);
		entityTransaction.commit();

//		------------------ Delete ------------------

		ObjectA deleteParent = entityManager.find(ObjectA.class, "ulli sagar");
		deleteParent.setOb(null);
		entityTransaction.begin();
		entityManager.merge(deleteParent);
		entityTransaction.commit();

		ObjectB deleteChild = entityManager.find(ObjectB.class, "Bengaluru");
		entityTransaction.begin();
		entityManager.remove(deleteChild);
		entityTransaction.commit();

	}

}
