package rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="User") // define qual sera o nome da classe pai para criar o xml final
@XmlAccessorType (XmlAccessType.FIELD) // informa para que seja gerado at�  os atributos  sem get ou set criado 

public class Users {
	
@XmlAttribute	
 private String id;	
 private String name;
 private Integer age;
 private double salary;

 
 public Users() {}
 
 public Users(String name, Integer age) {
	super();
	this.name = name;
	this.age = age;
}
 
 
 
public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}


@Override
public String toString() {
	return "Users [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
}
 
 
}
