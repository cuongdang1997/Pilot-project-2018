package com.magrabbit.pilot.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.magrabbit.pilot.bean.Product;
import com.magrabbit.pilot.bean.SearchProductInfo;
import com.magrabbit.pilot.dao.ProductManagementDao;
import com.magrabbit.pilot.dao.base.BaseDaoImpl;
import com.magrabbit.pilot.utility.CommonUtil;
import com.magrabbit.pilot.utility.Constants;

@Repository("productManagementDao")
@Transactional
public class ProductManagementDaoImpl extends BaseDaoImpl<Product, Integer> implements ProductManagementDao{

	@Value("${com.pilot.sql.product.search}")
	private String searchProductSql;

	@Value("${com.pilot.sql.product.getTotalRowSearchSql}")
	private String getTotalRowSearchSql;
	/**
	 * Search Product with InfoSearchProduct
	 * @param searchProductInfo
	 * @param maxResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> searchProduct(SearchProductInfo searchProductInfo, int maxResults){
		Criteria criteria = getCriteriaQueryProduct(searchProductInfo);
		return criteria.setFirstResult(searchProductInfo.getCurrentPage()*maxResults).setMaxResults(maxResults).list();
	};
	
	/**
	 * get {@link Criteria} search Product
	 * @param searchText
	 * @return
	 */
	 public Criteria getCriteriaQueryProduct(SearchProductInfo searchProductInfo) {
		Criteria criteria = getSession().createCriteria(daoType);

		/* Append Name Search */
		if(!CommonUtil.isEmpty(searchProductInfo.getSearchText())) {
			criteria.add(Restrictions.ilike(Constants.COL_NAME_PRODUCT_NAME, "%%"+searchProductInfo.getSearchText().toLowerCase()+"%%"));
		}
		if(!(searchProductInfo.getPriceFrom() == 0 && searchProductInfo.getPriceTo() == 0)) {
			if(searchProductInfo.getPriceFrom() != searchProductInfo.getPriceTo()) {
				if(searchProductInfo.getPriceFrom() == 0) {
					Criterion critLessThan = Restrictions.lt(Constants.COL_NAME_PRODUCT_PRICE, searchProductInfo.getPriceTo());
					Criterion critEq = Restrictions.eq(Constants.COL_NAME_PRODUCT_PRICE, searchProductInfo.getPriceTo());
					Criterion critLessOrEq = Restrictions.or(critLessThan, critEq);
					criteria.add(critLessOrEq);
				} else if(searchProductInfo.getPriceTo() == 0) {
					Criterion critGreaterThan = Restrictions.gt(Constants.COL_NAME_PRODUCT_PRICE, searchProductInfo.getPriceFrom());
					Criterion critEq = Restrictions.eq(Constants.COL_NAME_PRODUCT_PRICE, searchProductInfo.getPriceFrom());
					Criterion critGreatOrEq = Restrictions.or(critGreaterThan, critEq);
					criteria.add(critGreatOrEq);
				} else {
					criteria.add(Restrictions.between(Constants.COL_NAME_PRODUCT_PRICE, searchProductInfo.getPriceFrom(), searchProductInfo.getPriceTo()));
				}
			} else {
				criteria.add(Restrictions.eq(Constants.COL_NAME_PRODUCT_PRICE, searchProductInfo.getPriceFrom()));
			}
		}
		return criteria;
	}
	
	/**
	 * count result search
	 */
	@Override
	public Long countResultSearch(SearchProductInfo searchProductInfo) {
		return countCriteria(getCriteriaQueryProduct(searchProductInfo));
	}

}
