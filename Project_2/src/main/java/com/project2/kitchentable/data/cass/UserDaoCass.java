package com.project2.kitchentable.data.cass;

import com.datastax.oss.driver.api.core.CqlSession;
import com.project2.kitchentable.data.UserDao;
import com.project2.kitchentable.factory.Log;
import com.project2.kitchentable.utils.CassandraUtil;

@Log
public class UserDaoCass implements UserDao{
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	

}
