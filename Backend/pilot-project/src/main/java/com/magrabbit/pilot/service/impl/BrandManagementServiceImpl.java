package com.magrabbit.pilot.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magrabbit.pilot.bean.Brand;
import com.magrabbit.pilot.dao.BrandManagementDao;
import com.magrabbit.pilot.dao.base.BaseDao;
import com.magrabbit.pilot.service.BrandManagementService;
import com.magrabbit.pilot.service.base.BaseServiceImpl;
import com.magrabbit.pilot.utility.CommonUtil;

@Service("brandManagementService")
@Transactional
public class BrandManagementServiceImpl extends BaseServiceImpl<Brand, Integer> implements BrandManagementService {
	
	@Value("${LOCAL_PATH_PARENT_FOLDER}")
	private String parentFolderPath;
	
	protected static Logger LOGGER = Logger.getLogger(BrandManagementServiceImpl.class);
	
	private BrandManagementDao brandManagementDao;

	@Autowired
	public BrandManagementServiceImpl(@Qualifier("brandManagementDao") BaseDao<Brand, Integer> baseDao) {
		super(baseDao);
		this.brandManagementDao = (BrandManagementDao) baseDao;
	}
	

	/**
	 * Delete Brand
	 */
	@Override
	public void deleteBrand(Brand brand) {
		CommonUtil.deleteFile(brand.getLogo());
		delete(brand.getIdBrand());
	}

	/**
	 * Add Brand
	 */
	@Override
	public void addBrand(Brand brand) {
		try {
			String pathName = CommonUtil.saveImage(parentFolderPath, brand.getLogo(), null);
			brand.setLogo(pathName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		save(brand);
	}

	/**
	 * Edit Brand
	 */
	@Override
	public void editBrand(Brand brand, String oldImagePath) {
		try {
			String imagePath = CommonUtil.saveImage(parentFolderPath, brand.getLogo(), oldImagePath);
			brand.setLogo(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		update(brand);
	}
	
	/**
	 * Search Brand
	 */
	@Override
	public List<Brand> searchBrand(String searchText, int firstResult, int maxResults) {
		return brandManagementDao.searchBrand(searchText, firstResult, maxResults);
	}

	/**
	 * Count result Search 
	 */
	@Override
	public Long countResultSearch(String searchText) {
		return brandManagementDao.countResultSearch(searchText);
	}
}
