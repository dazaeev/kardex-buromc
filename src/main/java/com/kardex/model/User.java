package com.kardex.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Nazario Dazaeev Gonzalez Herrera
 * Tabla de Usuarios
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "uuid")
	private int uuid;
	
	@Column(name = "account")
	@NotEmpty(message = "Por favor proporciona un cuenta valida")
	private String account;
	
	@Column(name = "email")
	@NotEmpty(message = "Por favor proporciona una cuenta de correo valida")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "Por favor ingrese su nombre")
	private String name;
	
	@Column(name = "last_name")
	@NotEmpty(message = "Por favor ingrese su apellido")
	private String lastName;
	
	@Column(name = "active")
	private int active;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date date;
	
	@Column
	@UpdateTimestamp
	private Date modified;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "user")
	private Set<EmployeeNotification> employeeNotification;
	
	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "user")
	private Set<EmployeeMessage> employeeMessage;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private EmployeeGral employee;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<EmployeeNotification> getEmployeeNotification() {
		return employeeNotification;
	}

	public void setEmployeeNotification(Set<EmployeeNotification> employeeNotification) {
		this.employeeNotification = employeeNotification;
	}

	public Set<EmployeeMessage> getEmployeeMessage() {
		return employeeMessage;
	}

	public void setEmployeeMessage(Set<EmployeeMessage> employeeMessage) {
		this.employeeMessage = employeeMessage;
	}

	public EmployeeGral getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeGral employee) {
		this.employee = employee;
	}
}