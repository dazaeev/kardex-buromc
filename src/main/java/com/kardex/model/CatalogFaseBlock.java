package com.kardex.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Catalogos para FASES BLOQUES
 */
@Entity
@Table(name = "catalog_fase_block")
public class CatalogFaseBlock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "description")
	private String description;
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
    @JoinColumn(name = "catalog_fase_id", nullable = true)
	private CatalogFase catalogFase;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER,
    		mappedBy = "catalogFaseBlock")
	private Set<CatalogFaseBlockTechnology> catalogFaseBlockTechnology;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public CatalogFase getCatalogFase() {
		return catalogFase;
	}
	public void setCatalogFase(CatalogFase catalogFase) {
		this.catalogFase = catalogFase;
	}
	public Set<CatalogFaseBlockTechnology> getCatalogFaseBlockTechnology() {
		return catalogFaseBlockTechnology;
	}
	public void setCatalogFaseBlockTechnology(Set<CatalogFaseBlockTechnology> catalogFaseBlockTechnology) {
		this.catalogFaseBlockTechnology = catalogFaseBlockTechnology;
	}
}