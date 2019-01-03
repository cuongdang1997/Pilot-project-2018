package com.magrabbit.pilot.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magrabbit.pilot.bean.SearchProductInfo;
import com.magrabbit.pilot.bean.Product;
import com.magrabbit.pilot.dao.ProductManagementDao;
import com.magrabbit.pilot.dao.base.BaseDao;
import com.magrabbit.pilot.service.ProductManagementService;
import com.magrabbit.pilot.service.base.BaseServiceImpl;
import com.magrabbit.pilot.utility.CommonUtil;

@Service("productManagementService")
@Transactional
public class ProductManagementServiceImpl extends BaseServiceImpl<Product, Integer> implements ProductManagementService {
	
	@Value("${LOCAL_PATH_PARENT_FOLDER}")
	private String parentFolderPath;

	protected static Logger LOGGER = Logger.getLogger(ProductManagementServiceImpl.class);
	
	private ProductManagementDao productManagementDao;
	
	@Autowired
	public ProductManagementServiceImpl(@Qualifier("productManagementDao") BaseDao<Product, Integer> baseDao) {
		super(baseDao);
		this.productManagementDao = (ProductManagementDao) baseDao;
	}

	/**
	 * Add Product
	 */
	@Override
	public void addProduct(Product product) {
		try {
			String imagePath = CommonUtil.saveImage(parentFolderPath, product.getAvatar(), null);
			product.setAvatar(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		save(product);
	}

	/**
	 * Edit Product
	 */
	@Override
	public void editProduct(Product product, String oldImagePath) {
		try {
			String imagePath = CommonUtil.saveImage(parentFolderPath, product.getAvatar(), oldImagePath);
			product.setAvatar(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		update(product);
	}
	
	/**
	 * Delete Product
	 */
	@Override
	public void deleteProduct(Product product) {
		CommonUtil.deleteFile(product.getAvatar());
		delete(product.getIdProduct());
	}

	/**
	 * Search Product
	 */
	@Override
	public List<Product> searchProduct(SearchProductInfo searchProductInfo, int maxResults) {
		return productManagementDao.searchProduct(searchProductInfo, maxResults);
	}

	/**
	 * Count result Search Product
	 */
	@Override
	public Long countResultSearch(SearchProductInfo searchProductInfo) {
		return productManagementDao.countResultSearch(searchProductInfo);
	}
	
}
