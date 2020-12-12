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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Nazario Dazaeev Gonzalez Herrera 
 * Tabla de Catalogos para AREA
 */
@Entity
@Table(name = "catalog_area")
public class CatalogArea {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "value")
	private String value;
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
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "catalogArea")
	private Set<CatalogWorkPlace> catalogWorkPlace;
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "catalogArea")
	private CertificationTrack certificationTrack;
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public Set<CatalogWorkPlace> getCatalogWorkPlace() {
		return catalogWorkPlace;
	}
	public void setCatalogWorkPlace(Set<CatalogWorkPlace> catalogWorkPlace) {
		this.catalogWorkPlace = catalogWorkPlace;
	}
	public CertificationTrack getCertificationTrack() {
		return certificationTrack;
	}
	public void setCertificationTrack(CertificationTrack certificationTrack) {
		this.certificationTrack = certificationTrack;
	}
}