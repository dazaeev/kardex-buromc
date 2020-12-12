package com.kardex.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Catalogos para BLOQUES TECNOLOGIA
 */
@Entity
@Table(name = "catalog_fase_block_technology")
public class CatalogFaseBlockTechnology {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "technology")
	private String technology;
	@Basic
	@Column(name = "product")
	private String product;
	@Basic
	@Column(name = "active")
	private int active;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date date;
	@Column
	@UpdateTimestamp
	private Date modified;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_fase_block_id", nullable = true)
	private CatalogFaseBlock catalogFaseBlock;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public CatalogFaseBlock getCatalogFaseBlock() {
		return catalogFaseBlock;
	}
	public void setCatalogFaseBlock(CatalogFaseBlock catalogFaseBlock) {
		this.catalogFaseBlock = catalogFaseBlock;
	}
}