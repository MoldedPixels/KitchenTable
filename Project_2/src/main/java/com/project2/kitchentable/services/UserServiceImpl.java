//package com.project2.kitchentable.services;
//
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.project2.kitchentable.beans.User;
//import com.project2.kitchentable.data.UserDao;
////import com.project2.kitchentable.data.cass.UserDaoCass;
//import com.project2.kitchentable.factory.BeanFactory;
//import com.project2.kitchentable.factory.Log;
//
//// The service layer allows us to do more complicated actions that strict data access
//// Even if you don't do anything more important, the reason we need a service layer
//// is to loosely couple our business layer from our data layer
//@Log
//public class UserServiceImpl implements UserService {
//	private static Logger log = LogManager.getLogger(UserServiceImpl.class);
////	private UserDao ud = (UserDao) BeanFactory.getFactory().get(UserDao.class, UserDaoCass.class);
//
//	@Override
//	public User getUser(String fname, String lname) {
//		return ud.getUserByName(fname, lname);
//	}
//
//	@Override
//	public boolean addUser(User u) {
//		try {
//			ud.addUser(u);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.warn("User already exists " + u.getFirstName() + u.getLastName());
//			return false;
//		}
//	}
//
//	@Override
//	public List<User> getUsers() {
//		return ud.getUsers();
//	}
//
//	@Override
//	public void updateUser(User u) {
//		ud.updateUser(u);
//	}
//
//	@Override
//	public User getUserByID(int userID) {
//		return ud.getUserByID(userID);
//	}
//}
