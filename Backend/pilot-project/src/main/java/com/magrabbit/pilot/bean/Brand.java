package com.magrabbit.pilot.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "brand")
@JsonAutoDetect(getterVisibility=Visibility.NONE, isGetterVisibility=Visibility.NONE, fieldVisibility=Visibility.NONE)
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBrand;
	private String brandName;
	private String logo;
	private String description;
	
	@Transient
	Set<Product> products;
	
	@Transient
	private boolean changedLogo;
	
	@JsonProperty(value="idBrand")
	public Integer getIdBrand() {
		return idBrand;
	}
	public void setIdBrand(Integer idBrand) {
		this.idBrand = idBrand;
	}
	
	@JsonProperty(value="brandName")
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@JsonProperty(value="logo")
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@JsonProperty(value="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonProperty(value="products")
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	@JsonProperty(value="changedLogo")
	public boolean isChangedLogo() {
		return changedLogo;
	}
	public void setChangedLogo(boolean changedLogo) {
		this.changedLogo = changedLogo;
	}
}
