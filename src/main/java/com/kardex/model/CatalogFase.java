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
 * Tabla de Catalogos para FASES
 */
@Entity
@Table(name = "catalog_fase")
public class CatalogFase {

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
    @JoinColumn(name = "certification_track_id", nullable = true)
	private CertificationTrack certificationTrack;
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER,
    		mappedBy = "catalogFase")
	private Set<CatalogFaseBlock> catalogFaseBlock;
	
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
	public CertificationTrack getCertificationTrack() {
		return certificationTrack;
	}
	public void setCertificationTrack(CertificationTrack certificationTrack) {
		this.certificationTrack = certificationTrack;
	}
	public Set<CatalogFaseBlock> getCatalogFaseBlock() {
		return catalogFaseBlock;
	}
	public void setCatalogFaseBlock(Set<CatalogFaseBlock> catalogFaseBlock) {
		this.catalogFaseBlock = catalogFaseBlock;
	}
}