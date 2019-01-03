package com.magrabbit.pilot.service;

import java.util.List;

import com.magrabbit.pilot.bean.Brand;
import com.magrabbit.pilot.service.base.BaseService;

public interface BrandManagementService extends BaseService<Brand, Integer> {

	/**
	 * Delete Brand
	 * @param brand
	 */
	void deleteBrand(Brand brand);
	
	/**
	 * Add Brand
	 * @param brand
	 */
	void addBrand(Brand brand);
	
	/**
	 * Edit Brand
	 * @param brand
	 * @param oldImagePath
	 */
	void editBrand(Brand brand, String oldImagePath);
	
	/**
	 * Search Brand with searchText
	 * @param searchText
	 * @return
	 */
	List<Brand> searchBrand(String searchText, int firstResult, int maxResults);
	
	/**
	 * count result search
	 * @param searchText
	 * @return
	 */
	Long countResultSearch(String searchText);
}
