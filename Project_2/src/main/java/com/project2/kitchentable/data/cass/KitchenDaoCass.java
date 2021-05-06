package com.project2.kitchentable.data.cass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.data.KitchenDao;
import com.project2.kitchentable.factory.Log;
import com.project2.kitchentable.utils.CassandraUtil;
import com.project2.kitchentable.utils.JSONUtil;

@Service
public class KitchenDaoCass implements KitchenDao {
	@Autowired
	private CqlSession session;
	private JsonParser jp = null;

	@Override
	public List<Ingredient> getShoppingList(int kitchen) throws Exception {
		List<Ingredient> shoppinglist = new ArrayList<Ingredient>();

		String query = "Select shoppinglist from kitchen where kitchen_id = ?";
		BoundStatement bound = session.prepare(query).bind(kitchen);
		ResultSet rs = session.execute(bound);
		Row data = rs.one();
		if (data != null) {
			jp = JSONUtil.getInstance().getParser(data.getString("shoppinglist"));
			
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				Ingredient item = new Ingredient();
				String fieldName = jp.getCurrentName();
				// Let's move to value
				jp.nextToken();
				if (fieldName.equals("id")) {
					item.setID(jp.getIntValue());
				} else if (fieldName.equals("name")) {
					item.setName(jp.getText());
				} else if (fieldName.equals("amount")) {
					item.setAmount(jp.getDoubleValue());
				} else { // ignore, or signal error?
					throw new Exception("Unrecognized field '" + fieldName + "'");
				}
				shoppinglist.add(item);
			}
		}
		return shoppinglist;

	}

	@Override
	public List<Ingredient> getKitchenInv(int kitchen) throws JsonParseException, Exception {
		List<Ingredient> inventory = new ArrayList<Ingredient>();

		String query = "Select shoppinglist from kitchen where kitchen_id = ?";
		BoundStatement bound = session.prepare(query).bind(kitchen);
		ResultSet rs = session.execute(bound);
		Row data = rs.one();
		if (data != null) {
			jp = JSONUtil.getInstance().getParser(data.getString("inventory"));
			
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				Ingredient item = new Ingredient();
				String fieldName = jp.getCurrentName();
				// Let's move to value
				jp.nextToken();
				if (fieldName.equals("id")) {
					item.setID(jp.getIntValue());
				} else if (fieldName.equals("name")) {
					item.setName(jp.getText());
				} else if (fieldName.equals("amount")) {
					item.setAmount(jp.getDoubleValue());
				} else { // ignore, or signal error?
					throw new Exception("Unrecognized field '" + fieldName + "'");
				}
				inventory.add(item);
			}
		}
		return inventory;
	}

}
