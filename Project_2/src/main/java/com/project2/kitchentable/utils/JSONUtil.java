package com.project2.kitchentable.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class JSONUtil {
	private static JSONUtil instance = null;
	private JsonFactory jf = null;
	
	private JSONUtil() {
		log.trace("Setting up JSONUtil");
		
		try {
			jf = new JsonFactory();
		} catch (Exception e) {
			log.error("Method threw exception: " + e);
			for (StackTraceElement s : e.getStackTrace()) {
				log.warn(s);
			}
			throw e;
		}
	}

	private static final Logger log = LogManager.getLogger(JSONUtil.class);

	public static synchronized JSONUtil getInstance() {
		if (instance == null) {
			instance = new JSONUtil();
		}
		return instance;
	}
	
	public synchronized JsonParser getParser(String input) throws JsonParseException, IOException
	{
		return jf.createParser(input);
	}
}
