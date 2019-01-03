package com.magrabbit.pilot.dao;

import java.util.List;

import com.magrabbit.pilot.bean.SearchProductInfo;
import com.magrabbit.pilot.bean.Product;
import com.magrabbit.pilot.dao.base.BaseDao;

public interface ProductManagementDao extends BaseDao<Product , Integer> {

	/**
	 * Search Product with InfoSearchProduct
	 * @param searchProductInfo
	 * @param maxResults
	 * @return
	 */
	List<Product> searchProduct(SearchProductInfo searchProductInfo, int maxResults);
	
	/**
	 * count result search
	 * @param searchProductInfo
	 * @return
	 */
	Long countResultSearch(SearchProductInfo searchProductInfo);
	
}
