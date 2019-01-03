package com.magrabbit.pilot.utility;

/**
 * Constants utility
 * @author cuong
 *
 */
public class Constants {

	public static final String EMPTY_STRING = "";
	public static final String EQUAL_COMPARE = "=";
	public static final String GREATER_COMPARE = ">";
	public static final String LESS_COMPARE = "<";
	public static final String GREATER_OR_EQUAL_COMPARE = ">=";
	public static final String LESS_OR_EQUAL_COMPARE = "<=";

	public static final String COMMON_HYPHEN = "-";
	public static final String SUFFIX_IMAGE_JPG = ".jpg";
	public static final String SUFFIX_IMAGE_PNG = ".png";
	public static final String DATE_FORMAT_MMddyyyy = "MM/dd/yyyy";
	public static final String DATE_FORMAT_yyyyMMdd = "yyyy/MM/dd";
	public static final String DATE_FORMAT_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_FOR_FILE_NAME = "yyyyMMdd-HHmm";

	public static final String CRITERIAL_LIKE = "AND (UPPER(%s) LIKE '%%%s%%') ";
	public static final String OPERATOR_LIKE = " '%%%s%%' ";
	public static final String CRITERIAL_LIKE_OR = "OR (UPPER(%s) LIKE '%%%s%%') ";
	public static final String CRITERIAL_MAX = "MAX(%s) ";
	public static final String CRITERIAL_MIN = "MIN(%s) ";
	public static final String CRITERIAL_COMPARE = "AND (%s %s '%s') ";
	public static final String CRITERIAL_DATE_COMPARE = "AND (DATE(%s) %s '%s') ";
	public static final String CRITERIAL_BETWEEN = "AND (UPPER(%s) BETWEEN %s AND %s) ";
	public static final String SORT_STRING_AS_NUMBER = "length(%s) %s, %s %s";
	public static final String SORT_STRING_WITH_UTF_8 = "%s COLLATE utf8_persian_ci %s";
	public static final String SORT_NUMBER = "%s %s";
	
	public static final String COL_NAME_BRAND_ID = "idBrand";
	public static final String COL_NAME_BRAND_NAME = "brandName";
	public static final String COL_NAME_BRAND_LOGOURL = "logo";
	public static final String COL_NAME_BRAND_DESCRIPTION = "description";
	
	public static final String COL_NAME_PRODUCT_ID = "idProduct";
	public static final String COL_NAME_PRODUCT_NAME = "productName";
	public static final String COL_NAME_PRODUCT_PRICE = "price";
	public static final String COL_NAME_PRODUCT_QUANTITY = "quantity";
	public static final String COL_NAME_PRODUCT_OPENING_FOR_SALE = "openingForSale";
	public static final String COL_NAME_PRODUCT_AVARTAURL = "avatar";
	
	public static final String NO_IMAGE_URL = "no-image.jpg";

	public static final String KEY_LIST_SEARCH_RESULT = "result";
	public static final String KEY_LIST_BRAND = "BrandList";
	public static final String KEY_LIST_PRODUCT = "ProductList";
	
	public static final String PROP_ROOT_FOLDER = "D:\\"; //root.storage.folder

	public static final String SQL_STATEMENT = "SQL Statement: ";
	
	/** System Properties File */
	public static final String SYSTEM_PROPS_FILE_NAME = "application.properties";

	/**
	 * MONEY FORMAT
	 */
	public static final String MONEY_FORMAT = "###,###,###.###";

	/**
	 * SORT_DIR.
	 */
	public static final String SORT_DIR = "order[0][dir]";
	/**
	 * SORT_COLUMN.
	 */
	public static final String SORT_COLUMN = "order[0][column]";

	/**
	 * AA_DATA.
	 */
	public static final String AA_DATA = "data";
	/**
	 * ITOTAL_DISPLAY_RECORDS.
	 */
	public static final String ITOTAL_DISPLAY_RECORDS = "recordsFiltered";
	/**
	 * ITOTAL_RECORDS.
	 */
	public static final String ITOTAL_RECORDS = "recordsTotal";
	/**
	 * S_ECHO.
	 */
	public static final String S_ECHO = "draw";
}