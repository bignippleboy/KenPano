package com.bignippleboy.KenPano.application;


import java.util.List;
import java.util.Set;
import  com.bignippleboy.KenPano.core.domain.Classes;

public interface ClassesApplication {

	public Classes getClasses(Long id);
	
	public void creatClasses(Classes classes);
	
	public void updateClasses(Classes classes);
	
	public void removeClasses(Classes classes);
	
	public void removeClassess(Set<Classes> classess);
	
	public List<Classes> findAllClasses();
	
}

