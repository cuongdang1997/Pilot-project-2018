package com.magrabbit.pilot.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * @author cuong
 *
 */
public class CommonUtil {
	
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);

	/**
	 * Check String null or empty
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isEmpty(Object string) {
		return string == null || Constants.EMPTY_STRING.equals(string);
	}

	/**
	 * Check List null or empty
	 * 
	 * @param List
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		return list == null || list.isEmpty();
	}

	/**
	 * Convert String To TimeStamp
	 * 
	 * @param strDate
	 * @return Timestamp
	 */
	public static Timestamp cvStrToTimeStamp(String strDate) {

		Timestamp timestamp = null;
		if (!StringUtils.isEmpty(strDate)) {

			SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_MMddyyyy);
			try {
				Date date = dateFormat.parse(strDate);
				return new Timestamp(date.getTime());
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		return timestamp;
	}

	/**
	 * Convert String MM/dd/yyyy To java.util.Date
	 * 
	 * @param String MM/dd/yyyy
	 * @return Date
	 */
	public static Date cvMMddyyyToDate(String dateStr) {

		Date date = null;
		if (!StringUtils.isEmpty(dateStr)) {

			SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_MMddyyyy);
			try {
				date = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		return date;
	}

	/**
	 * Convert time To MMddYYYY
	 * 
	 * @param strDate
	 * @return dateMMddYYYY
	 */
	public static String cvTimeToMMddYYYY(String strDate) {

		String dateMMddYYYY = Constants.EMPTY_STRING;
		if (!StringUtils.isEmpty(strDate)) {

			SimpleDateFormat dateFormatInput = new SimpleDateFormat(Constants.DATE_FORMAT_yyyyMMddHHmmss);
			SimpleDateFormat dateFormatOutput = new SimpleDateFormat(Constants.DATE_FORMAT_MMddyyyy);
			try {
				Date date = dateFormatInput.parse(strDate);
				return dateFormatOutput.format(date);
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		return dateMMddYYYY;
	}

	/**
	 * Convert time To MMddYYYY
	 * 
	 * @param strDate
	 * @return dateMMddYYYY
	 */
	public static String cvTimeToMMddYYYY(Date date) {

		String dateMMddYYYY = Constants.EMPTY_STRING;
		if (date != null) {

			SimpleDateFormat dateFormatOutput = new SimpleDateFormat(Constants.DATE_FORMAT_MMddyyyy);
			dateMMddYYYY = dateFormatOutput.format(date);
		}

		return dateMMddYYYY;
	}

	/**
	 * Convert time To yyyyMMdd
	 * 
	 * @param strDate
	 * @return String Date yyyyMMdd
	 */
	public static String cvTimeToyyyyMMdd(String strDate) {

		String dateMMddYYYY = Constants.EMPTY_STRING;
		if (!StringUtils.isEmpty(strDate)) {

			SimpleDateFormat dateFormatInput = new SimpleDateFormat(Constants.DATE_FORMAT_MMddyyyy);
			SimpleDateFormat dateFormatOutput = new SimpleDateFormat(Constants.DATE_FORMAT_yyyyMMdd);
			try {
				Date date = dateFormatInput.parse(strDate);
				return dateFormatOutput.format(date);
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		return dateMMddYYYY;
	}

	/**
	 * Convert time To yyyyMMdd
	 * 
	 * @param strDate
	 * @return String Date yyyyMMdd
	 */
	public static Date cvStringToyyyyMMdd(String strDate) {

		Date dateMMddYYYY = null;
		if (!StringUtils.isEmpty(strDate)) {

			SimpleDateFormat dateFormatInput = new SimpleDateFormat(Constants.DATE_FORMAT_MMddyyyy);
			try {
				dateMMddYYYY = dateFormatInput.parse(strDate);
			} catch (ParseException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		return dateMMddYYYY;
	}

	/**
	 * Append Criteria With Operator
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param operator
	 * @return String
	 */
	public static String appendCriteria(String fieldName, Object fieldValue, String operator) {

		String strQuery = Constants.EMPTY_STRING;
		if (!CommonUtil.isEmpty(fieldValue)) {
			strQuery = String.format(Constants.CRITERIAL_COMPARE, fieldName, operator,
					fieldValue.toString().toUpperCase().trim());
		}
		return strQuery;
	}

	/**
	 * Append Like Criteria
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return String
	 */
	public static String appendCriteriaLike(String fieldName, String fieldValue) {

		String strQuery = Constants.EMPTY_STRING;
		if (!CommonUtil.isEmpty(fieldValue)) {
			strQuery = String.format(Constants.CRITERIAL_LIKE, fieldName, fieldValue.toUpperCase().trim());
		}
		return strQuery;
	}
	
	/**
	 * Append Or Like Criteria
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return String
	 */
	public static String appendCriteriaLikeOr(String fieldName, String fieldValue) {

		String strQuery = Constants.EMPTY_STRING;
		if (!CommonUtil.isEmpty(fieldValue)) {
			strQuery = String.format(Constants.CRITERIAL_LIKE_OR, fieldName, fieldValue.toUpperCase().trim());
		}
		return strQuery;
	}
	
	/**
	 * Append Max Criteria
	 * @param fieldName
	 * @return
	 */
	public static String appendCriteriaMax(String fieldName) {
		String strQuery = Constants.EMPTY_STRING;
		strQuery = String.format(Constants.CRITERIAL_MAX, fieldName);
		return strQuery;
	}
	
	/**
	 * Append Min Criteria
	 * @param fieldName
	 * @return
	 */
	public static String appendCriteriaMin(String fieldName) {
		String strQuery = Constants.EMPTY_STRING;
		strQuery = String.format(Constants.CRITERIAL_MIN, fieldName);
		return strQuery;
	}

	/**
	 * Append Between Criteria
	 * 
	 * @param fieldName
	 * @param fromValue
	 * @param toValue
	 * @return String
	 */
	public static String appendCriteriaBetween(String fieldName, String fromValue, String toValue) {

		String strQuery = Constants.EMPTY_STRING;
		if (!CommonUtil.isEmpty(fromValue) && !CommonUtil.isEmpty(toValue)) {
			strQuery = String.format(Constants.CRITERIAL_BETWEEN, fieldName, fromValue, toValue);
		}
		return strQuery;
	}

	/**
	 * Append Criteria Date With Operator
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param operator
	 * @return String
	 */
	public static String appendCriteriaDate(String fieldName, String fieldValue, String operator) {

		String strQuery = Constants.EMPTY_STRING;

		if (!CommonUtil.isEmpty(fieldValue)) {
			strQuery = String.format(Constants.CRITERIAL_DATE_COMPARE, fieldName, operator, fieldValue);
		}
		return strQuery;
	}

	/**
	 * convert To Decimal
	 * 
	 * @param value
	 * @return Money Format
	 */
	public static String convertToDecimal(double value) {

		DecimalFormat myFormatter = new DecimalFormat(Constants.MONEY_FORMAT);
		return myFormatter.format(value);
	}
	
	/*
	 * Delete File
	 * 
	 * @param imageUrl
	 * @return result
	 */
	public static boolean deleteFile(String pathName) {
		boolean isDeletedFile = false;
		String rootFolderPath = Constants.PROP_ROOT_FOLDER;
		String fullAssetPath = rootFolderPath + pathName;
		File file = new File(fullAssetPath);
		if (file.exists()) {
			isDeletedFile = file.delete();
			System.out.println("Delete file:"+ pathName);
		}
		return isDeletedFile;
	}
	
	/**
	 * Save image to storage folder
	 * 
	 * @param parentFolderPath
	 * @param fileInBase64
	 * @param oldImagePath
	 * @return assetPath
	 * @throws IOException
	 */
	public static String saveImage(String parentFolderPath, String fileInBase64, String oldImagePath)
			throws IOException {

		// Generate assetName with format: yyyyMMdd-HHmm-${randomStr}
		SecureRandom random = new SecureRandom();
		String randomStr = new BigInteger(130, random).toString(32).substring(0, 6);
		String assetName = cvDateToString(new Date(), Constants.DATE_FORMAT_FOR_FILE_NAME) + Constants.COMMON_HYPHEN
				+ randomStr;

		String rootFolderPath = Constants.PROP_ROOT_FOLDER;
		String assetPath = parentFolderPath + assetName + Constants.SUFFIX_IMAGE_PNG;
		String fullAssetPath = rootFolderPath + assetPath;

		// Decode File From Base64 Encoding To File Image
		Base64 decoder = new Base64();
		byte[] imgBytes = decoder.decode(fileInBase64);

		// Create Folder To Save Image
		File parentFolder = new File(rootFolderPath + parentFolderPath);
		if (!parentFolder.exists()) {
			parentFolder.mkdir();
		}

		if (StringUtils.isNotEmpty(oldImagePath)) {

			// Remove image if it existed
			File imageFile = new File(rootFolderPath + oldImagePath);
			if (imageFile.exists() && imageFile.length() > 0) {
				imageFile.delete();
			}
		}

		// Save File To Disk
		FileCopyUtils.copy(imgBytes, new File(fullAssetPath));
		return assetPath;
	}
	
	/**
	 * Convert date to string with input format
	 * 
	 * @param inputDate
	 * @param dateFormat
	 * @return outputDateStr
	 */
	public static String cvDateToString(Date inputDate, String dateFormat) {

		String outputDate = StringUtils.EMPTY;
		if (inputDate != null) {
			SimpleDateFormat dateFormatOutput = new SimpleDateFormat(dateFormat);
			outputDate = dateFormatOutput.format(inputDate);
		}
		
		return outputDate;
	}
	
	/**
	 * Read Properties
	 * 
	 * @param key
	 * @return value
	 */
	public static String readProperties(String key) {

		InputStream inputStream = CommonUtil.class.getClassLoader()
				.getResourceAsStream(Constants.SYSTEM_PROPS_FILE_NAME);
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}