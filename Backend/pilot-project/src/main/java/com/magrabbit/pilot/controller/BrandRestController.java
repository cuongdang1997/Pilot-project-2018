package com.magrabbit.pilot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.magrabbit.pilot.bean.Brand;
import com.magrabbit.pilot.service.BrandManagementService;

@RestController
@RequestMapping(value ="/api/brands")
public class BrandRestController {
	
	@Autowired
	private BrandManagementService brandManagementService;
	
	/**
	 * get BrandList
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> getListBrands(){
		List<Brand> brands = brandManagementService.getAll();
        return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
	}
	
	/**
	 * get brands per page
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/pager/{page}", method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> getBrandPerPage(@PathVariable("page") int page) {
		List<Brand> brands = brandManagementService.findAll(10*page, 10);
        return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
	}
	
	/**
	 * get Brand by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{idBrand}", method = RequestMethod.GET)
    public ResponseEntity<Brand> getBrand(@PathVariable("idBrand") int idBrand) {
        System.out.println("Fetching brand with id " + idBrand);
        Brand brand = brandManagementService.find(idBrand);
        if (brand == null) {
            System.out.println("Brand with id " + idBrand + " not found");
            return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Brand>(brand, HttpStatus.OK);
    }
	
	/**
	 * create Brand
	 * @param brand
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Brand> createBrand(@RequestBody Brand brand){
		System.out.println("Creating Brand " + brand.getBrandName());
		//check isBrandExist
		brandManagementService.addBrand(brand);
		return new ResponseEntity<Brand>(brand, HttpStatus.CREATED);
	}
	
	/**
	 * Update Brand
	 * @param brand
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand){
		System.out.println("Updating Brand with id = " + brand.getIdBrand());
		Brand oldBrand = brandManagementService.find(brand.getIdBrand());
		if(oldBrand == null) {
			System.out.println("Brand with id " + brand.getIdBrand() + " not found");
			return new ResponseEntity<Brand>(HttpStatus.NOT_FOUND);
		} else if(brand.isChangedLogo()) {
			brandManagementService.editBrand(brand, oldBrand.getLogo());
		} else {
			brandManagementService.update(brand);
		}
		return new ResponseEntity<Brand>(brand, HttpStatus.OK);
	}

	/**
	 * Delete Brand by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{idBrand}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteBrand(@PathVariable("idBrand") int idBrand) {
		System.out.println("Fetching & Deleting Brand with id " + idBrand);
		Brand brand = brandManagementService.find(idBrand);
		if (brand == null) {
			System.out.println("Unable to delete. Brand with id " + idBrand + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		brandManagementService.deleteBrand(brand);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * count all brand
	 * @return
	 */
	@RequestMapping(value = "/countAll", method = RequestMethod.GET)
	public ResponseEntity<Integer> countAll() {
		int countAllBrand = (int) brandManagementService.countAll();
		return new ResponseEntity<Integer>(countAllBrand, HttpStatus.OK);
	}
	
	/**
	 * search brand by brandName
	 * @param page
	 * @param searchText
	 * @return
	 */
	@RequestMapping(value = {"/search/{page}/{searchText}"}, method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> searchBrand(@PathVariable("page") int page, 
			@PathVariable("searchText") String searchText) {
		List<Brand> listSearchResult = brandManagementService.searchBrand(searchText, page*10, 10);
		return new ResponseEntity<List<Brand>>(listSearchResult, HttpStatus.OK);
	}
	
	/**
	 * count result search
	 * @param searchText
	 * @return
	 */
	@RequestMapping(value = "/countResultSearch/{searchText}", method = RequestMethod.GET)
	public ResponseEntity<Long> countResultSearch(@PathVariable("searchText") String searchText) {
		Long countResultSearch = brandManagementService.countResultSearch(searchText);
		return new ResponseEntity<Long>(countResultSearch, HttpStatus.OK);
	}

}
