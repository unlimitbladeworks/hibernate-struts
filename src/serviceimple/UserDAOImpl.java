package serviceimple;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.myHibernateSessionFactory;
import entity.Users;
import service.UsersDAO;

public class UserDAOImpl implements UsersDAO{

	public boolean usersLogin(Users u) {
	
		//事物对象
		Transaction tx = null;
		String hql = "";
		try{
			
			Session session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Users where username=? and password=? ";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1, u.getPassword());
			List list = query.list();
			tx.commit();//提交事务
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}finally{
			if(tx!=null){
//				tx.commit();
				tx = null;
			}
		}
	}
}
