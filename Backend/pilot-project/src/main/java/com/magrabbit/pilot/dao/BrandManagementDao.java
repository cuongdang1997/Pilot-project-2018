package com.magrabbit.pilot.dao;

import java.util.List;

import com.magrabbit.pilot.bean.Brand;
import com.magrabbit.pilot.dao.base.BaseDao;

public interface BrandManagementDao extends BaseDao<Brand, Integer>{

	/**
	 * Search brand
	 * @param searchText
	 * @return
	 */
	List<Brand> searchBrand(String searchText, int firstResult, int maxResults);
	
	/**
	 * count result search
	 * @param sqlQuery
	 * @return
	 */
	Long countResultSearch(String searchText);
}
