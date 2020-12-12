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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla para PLANES DE CARRERA
 */
@Entity
@Table(name = "certification_track")
public class CertificationTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "area")
	private String area;
	@Basic
	@Column(name = "active")
	private int active;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date date;
	@Column
	@UpdateTimestamp
	private Date modified;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER,
    		mappedBy = "certificationTrack")
	private Set<CatalogFase> catalogFase;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "catalog_area_id", nullable = true)
	private CatalogArea catalogArea;
	
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public Set<CatalogFase> getCatalogFase() {
		return catalogFase;
	}
	public void setCatalogFase(Set<CatalogFase> catalogFase) {
		this.catalogFase = catalogFase;
	}
	public CatalogArea getCatalogArea() {
		return catalogArea;
	}
	public void setCatalogArea(CatalogArea catalogArea) {
		this.catalogArea = catalogArea;
	}
}