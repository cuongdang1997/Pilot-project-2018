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

import com.magrabbit.pilot.bean.SearchProductInfo;
import com.magrabbit.pilot.bean.Product;
import com.magrabbit.pilot.service.ProductManagementService;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	
	@Autowired
	private ProductManagementService productManagementService;
	
	/**
	 * get ProductList
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProducts(){
		List<Product> products = productManagementService.getAll();
		if(products.isEmpty()) {
        	return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }else {
        	return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        }
	}
	
	/**
	 * get products per page
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/pager/{page}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductPerPage(@PathVariable("page") int page) {
		List<Product> products = productManagementService.findAll(10*page, 10);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	/**
	 * get Product by id
	 * @param idProduct
	 * @return
	 */
	@RequestMapping(value = "/{idProduct}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("idProduct") int idProduct) {
        System.out.println("Fetching product with id " + idProduct);
        Product product = productManagementService.find(idProduct);
        if (product == null) {
            System.out.println("Product with id " + idProduct + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
	

	/**
	 * create product
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		System.out.println("Creating product " + product.getProductName());
		//check isProductExist
		productManagementService.addProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	/**
	 * update product by id
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		System.out.println("Updating Product with id = " + product.getIdProduct());
		Product oldProduct = productManagementService.find(product.getIdProduct());
		if(oldProduct == null) {
			System.out.println("Product with id " + product.getIdProduct() + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		} else if(product.isChangedAvatar()) {
			productManagementService.editProduct(product, oldProduct.getAvatar());
		} else {
			productManagementService.update(product);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	/**
	 * delete product by id
	 * @param idProduct
	 * @return
	 */
	@RequestMapping(value = "/{idProduct}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteProduct(@PathVariable("idProduct") int idProduct) {
		System.out.println("Fetching & Deleting Product with id " + idProduct);
		Product product = productManagementService.find(idProduct);
		if (product == null) {
			System.out.println("Unable to delete. Product with id " + idProduct + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		productManagementService.deleteProduct(product);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * count all product
	 * @return
	 */
	@RequestMapping(value = "/countAll", method = RequestMethod.GET)
	public ResponseEntity<Integer> countAll() {
		int countAllProduct = (int) productManagementService.countAll();
		return new ResponseEntity<Integer>(countAllProduct, HttpStatus.OK);
	}
	
	/**
	 * Search Product
	 * @param searchProductInfo
	 * @return
	 */
	@RequestMapping(value = {"/search"}, method = RequestMethod.POST)
	public ResponseEntity<List<Product>> searchProduct(@RequestBody SearchProductInfo searchProductInfo) {
		List<Product> listSearchResult = productManagementService.searchProduct(searchProductInfo, 10);
		return new ResponseEntity<List<Product>>(listSearchResult, HttpStatus.OK);
	}
	
	/**
	 * count result search
	 * @param searchProductInfo
	 * @return
	 */
	@RequestMapping(value = "/countResultSearch", method = RequestMethod.POST)
	public ResponseEntity<Long> countResultSearch(@RequestBody SearchProductInfo searchProductInfo) {
		Long countResultSearch = productManagementService.countResultSearch(searchProductInfo);
		return new ResponseEntity<Long>(countResultSearch, HttpStatus.OK);
	}
}
