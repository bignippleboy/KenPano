package com.bignippleboy.KenPano.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.bignippleboy.KenPano.application.ClassesApplication;
import com.bignippleboy.KenPano.core.domain.Classes;

@Named
@Transactional
public class ClassesApplicationImpl implements ClassesApplication {

	public Classes getClasses(Long id) {
		return Classes.get(Classes.class, id);
	}
	
	public void creatClasses(Classes classes) {
		classes.save();
	}
	
	public void updateClasses(Classes classes) {
		classes .save();
	}
	
	public void removeClasses(Classes classes) {
		if(classes != null){
			classes.remove();
		}
	}
	
	public void removeClassess(Set<Classes> classess) {
		for (Classes classes : classess) {
			classes.remove();
		}
	}
	
	public List<Classes> findAllClasses() {
		return Classes.findAll(Classes.class);
	}
	
}
