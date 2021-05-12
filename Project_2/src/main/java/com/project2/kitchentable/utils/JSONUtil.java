package com.project2.kitchentable.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project2.kitchentable.beans.Ingredient;

public class JSONUtil {
	private static JSONUtil instance = null;
	private JsonFactory jf = null;
	ObjectMapper mapper = null;

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

	public synchronized List<Ingredient> readIngredients(String s) throws IOException {
		mapper = new ObjectMapper();
		List<Ingredient> result = mapper.readValue(s, new TypeReference<List<Ingredient>>() {
		});
		return result;
	}

	public synchronized String writeIngredients(List<Ingredient> list)
			throws JsonGenerationException, JsonMappingException, IOException {

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper = new ObjectMapper();

		mapper.writeValue(out, list);

		return out.toString();
	}
}
