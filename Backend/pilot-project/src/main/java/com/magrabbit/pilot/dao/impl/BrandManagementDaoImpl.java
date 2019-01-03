package com.magrabbit.pilot.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.magrabbit.pilot.bean.Brand;
import com.magrabbit.pilot.dao.BrandManagementDao;
import com.magrabbit.pilot.dao.base.BaseDaoImpl;
import com.magrabbit.pilot.utility.CommonUtil;
import com.magrabbit.pilot.utility.Constants;

@Repository("brandManagementDao")
@Transactional
public class BrandManagementDaoImpl extends BaseDaoImpl<Brand, Integer> implements BrandManagementDao {

	@Value("${com.pilot.sql.brand.search}")
	private String searchBrandSql;

	@Value("${com.pilot.sql.brand.getTotalRowSearchSql}")
	private String getTotalRowSearchSql;
	
	/**
	 * search brand
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Brand> searchBrand(String searchText, int firstResult, int maxResults) {
		String querySearchBrandSql = getQuerySearchBrandSql(searchText);
		String querySearchBrand = String.format(searchBrandSql, querySearchBrandSql);
		List<Brand> listBrandResult = (List<Brand>) getSession().createSQLQuery(querySearchBrand).addEntity("item", Brand.class).
				setFirstResult(firstResult).setMaxResults(maxResults).list();
		return listBrandResult;
	}
	
	/**
	 * get query search Brand
	 * @param searchText
	 * @return
	 */
	private static String getQuerySearchBrandSql(String searchText) {
		StringBuilder strQuerySearch = new StringBuilder();

		/* Append Name Search */
		strQuerySearch.append(CommonUtil.appendCriteriaLike(Constants.COL_NAME_BRAND_NAME, searchText));

		return strQuerySearch.toString();
	}

	/**
	 * count result search
	 */
	@Override
	public Long countResultSearch(String searchText) {
		String querySearchBrandSql = getQuerySearchBrandSql(searchText);
		String queryGetTotalRowSearch = String.format(getTotalRowSearchSql, querySearchBrandSql);
		return countQuery(queryGetTotalRowSearch);
	}
}
