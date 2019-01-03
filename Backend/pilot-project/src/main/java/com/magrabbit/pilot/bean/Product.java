package com.magrabbit.pilot.bean;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduct;
	private String productName;
	private Integer quantity;
	private Date openingForSale;
	private String avatar;
	private long price;
	
	
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@ManyToOne(targetEntity = Brand.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idBrand")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Brand brand;
	
	@Transient
	private boolean changedAvatar;

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getOpeningForSale() {
		return openingForSale;
	}

	public void setOpeningForSale(Date openingForSale) {
		this.openingForSale = openingForSale;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	public boolean isChangedAvatar() {
		return changedAvatar;
	}

	public void setChangedAvatar(boolean changedAvatar) {
		this.changedAvatar = changedAvatar;
	}
}
