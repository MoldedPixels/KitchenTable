package com.project2.kitchentable.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project2.kitchentable.factory.BeanFactory;
import com.project2.kitchentable.services.KitchenService;
import com.project2.kitchentable.services.KitchenServiceImpl;


public class KitchenController {
	private static KitchenService ks = (KitchenService) BeanFactory.getFactory().get(KitchenService.class,
			KitchenServiceImpl.class);

	private static final Logger log = LogManager.getLogger(KitchenController.class);

	


}
