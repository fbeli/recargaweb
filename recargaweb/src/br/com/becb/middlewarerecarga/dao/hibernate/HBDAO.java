package br.com.becb.middlewarerecarga.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation = Propagation.REQUIRED)
public abstract class HBDAO<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}

	protected Session getSession() {
		Session session=  getSessionFactory().getCurrentSession();
		if(!session.getTransaction().isActive())
			 session.beginTransaction();
		return session;
	}

	protected abstract Class getClazz();

	public void persistir(T objeto) {

		Session session = getSession();
		if(!session.getTransaction().isActive()){
		Transaction trans = session.beginTransaction();
		
		session.saveOrUpdate(objeto);
		trans.commit();
		}else{
			Transaction trans = session.getTransaction();
			
			session.saveOrUpdate(objeto);
			trans.commit();
		};
		if(session.isOpen())
		session.close();

	}

	public void excluir(T objeto) {
		getSession().delete(objeto);
	}

	public T get(Long id) {
		return (T) getSession().get(getClazz(), id);
	}

	public List<T> list(int offset, int max) {
		return (List<T>) getSession().createCriteria(getClazz())
				.setMaxResults(max).setFirstResult(offset).list();
	}

	public void merge(Object object) {
		Session session = getSession();
		if(!session.getTransaction().isActive())
			session.beginTransaction();
		session.merge(object);
		session.getTransaction().commit();
	}
/*	public static void closeSession() throws HibernateException {
	    Session session = (Session) threadLocal.get();
	    threadLocal.set(null);

	    if (session != null) {
	      session.close();
	    }
	  }*/
}
