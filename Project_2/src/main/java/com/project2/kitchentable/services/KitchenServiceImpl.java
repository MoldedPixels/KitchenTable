package com.project2.kitchentable.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.data.KitchenDao;
import com.project2.kitchentable.data.UserDao;
import com.project2.kitchentable.data.cass.KitchenDaoCass;
import com.project2.kitchentable.data.cass.UserDaoCass;
import com.project2.kitchentable.factory.BeanFactory;
import com.project2.kitchentable.factory.Log;

// The service layer allows us to do more complicated actions that strict data access
// Even if you don't do anything more important, the reason we need a service layer
// is to loosely couple our business layer from our data layer
@Log
public class KitchenServiceImpl implements KitchenService {
	private static Logger log = LogManager.getLogger(KitchenServiceImpl.class);
	private KitchenDao kd = (KitchenDao) BeanFactory.getFactory().get(KitchenDao.class, KitchenDaoCass.class);
	@Override
	public void removeFood() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cook() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Ingredient> getShoppingList() {
		List<Ingredient> list = kd.getShoppingList();
		return list;
	}

	
}
