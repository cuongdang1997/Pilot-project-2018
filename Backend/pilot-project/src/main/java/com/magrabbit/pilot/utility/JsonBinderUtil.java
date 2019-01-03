package com.magrabbit.pilot.utility;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;


/**
 * 
 * @author cuong
 *
 */
public class JsonBinderUtil {

	protected static Logger LOGGER = Logger.getLogger(JsonBinderUtil.class);
	private static Object lock = new Object();
	private static JsonBinderUtil jsonBinder;
	private static JsonBinderUtil jsonBinderNonNull;
	private static ObjectMapper mapper = new ObjectMapper();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @return
	 */
	public static JsonBinderUtil getInstance() {
		if (jsonBinder == null) {
			synchronized (lock) {
				if (jsonBinder == null) {
					jsonBinder = new JsonBinderUtil(Inclusion.NON_DEFAULT);
				}
			}
		}
		return jsonBinder;
	}

	/**
	 * 
	 * @param inclusion
	 * @return
	 */
	public static JsonBinderUtil getInstanceNonNull() {
		if (jsonBinderNonNull == null) {
			synchronized (lock) {
				if (jsonBinderNonNull == null) {
					jsonBinderNonNull = new JsonBinderUtil(Inclusion.NON_NULL);
				}
			}
		}
		return jsonBinderNonNull;
	}

	protected JsonBinderUtil(Inclusion inclusion) {
		mapper = new ObjectMapper();
		mapper.getSerializationConfig().withSerializationInclusion(inclusion);
		mapper.setSerializationInclusion(inclusion);
		mapper.setDateFormat(df);
	}

	/**
	 * Convert From Json to Object
	 * 
	 * @param jsonString
	 * @param clazz Object T
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			LOGGER.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * Convert From Json to Object
	 * 
	 * @param jsonString
	 * @param type
	 * @return Object T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, TypeReference<T> type) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) mapper.readValue(jsonString, type);
		} catch (IOException e) {
			LOGGER.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * Convert From Json to Object
	 * 
	 * @param <T>
	 * @param jsonString
	 * @param type
	 * @return Object T
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> fromSTBResponseJson(String jsonString) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			Map<String, Object> stbResponseMap = mapper.readValue(jsonString, Map.class);
			return (Map<String, Object>) stbResponseMap.get("STBResponse");
		} catch (IOException e) {
			LOGGER.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(byte[] json, Class<T> clazz) {
		if (json == null || json.length == 0) {
			return null;
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			LOGGER.warn("parse json string error:", e);
			return null;
		}
	}

	/**
	 * Convert Object to Json
	 * 
	 * @param object
	 * @return JsonString
	 */
	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			LOGGER.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * Convert Json To List<Object>
	 * 
	 * @param json
	 * @param clazz
	 * @return List<T>
	 */
	public static <T> List<T> getListFromJson(String json, Class<T> clazz) {

		if (json == null || json.length() == 0) {
			return null;
		}
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} catch (IOException e) {
			LOGGER.warn("parse json string error:", e);
			return null;
		}
	}
}