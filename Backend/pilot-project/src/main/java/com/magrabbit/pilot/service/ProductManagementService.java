package com.magrabbit.pilot.service;

import java.util.List;

import com.magrabbit.pilot.bean.SearchProductInfo;
import com.magrabbit.pilot.bean.Product;
import com.magrabbit.pilot.service.base.BaseService;

public interface ProductManagementService extends BaseService<Product, Integer>{
	
	/**
	 * Delete Product
	 * @param product
	 */
	void deleteProduct(Product product);
	
	/**
	 * Add product
	 * @param product
	 */
	void addProduct(Product product);
	
	/**
	 * Edit product
	 * @param product
	 * @param oldImagePath
	 */
	void editProduct(Product product, String oldImagePath);
	
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
