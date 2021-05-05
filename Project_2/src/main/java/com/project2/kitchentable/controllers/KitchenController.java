package com.project2.kitchentable.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project2.kitchentable.beans.Ingredient;
import com.project2.kitchentable.beans.User;
import com.project2.kitchentable.factory.BeanFactory;
import com.project2.kitchentable.services.KitchenService;
import com.project2.kitchentable.services.KitchenServiceImpl;
import com.project2.kitchentable.services.UserService;
import com.project2.kitchentable.services.UserServiceImpl;

import io.javalin.http.Context;

public class KitchenController {
	private static KitchenService ks = (KitchenService) BeanFactory.getFactory().get(KitchenService.class,
			KitchenServiceImpl.class);

	private static final Logger log = LogManager.getLogger(KitchenController.class);

	public static void removeFood(Context ctx) {
		String result = "";
		double amount = Integer.parseInt(ctx.formParam("amount"));
		User u = (User) ctx.sessionAttribute("User");
		if (u == null) {
			ctx.status(403);
			ctx.result("Not Logged In");
			return;
		}
		List<Ingredient> inv = ks.getShoppingList();

		for (Ingredient item : inv) {
			if (item.getID() == Integer.parseInt(ctx.formParam("itemID"))) {
				if (item.getAmount() >= amount) {
					item.setAmount(item.getAmount() - amount);
					result = "Successfully removed " + amount + "of " + item.getName();
				} else {
					result = "You do not have enough of " + item.getName();
				}
			} else {
				result = "You do not have any " + item.getName();
			}
		}

		ctx.result(result);
	}

	public static void cook(Context ctx) {
		String result = "";
		User u = (User) ctx.sessionAttribute("User");
		if (u == null || u.getRoleID() != 3) {
			ctx.status(403);
			ctx.result("Well that didn't work.");
			return;
		}
		List<User> players = us.getUsers();
		ctx.json(players);
	}
}
