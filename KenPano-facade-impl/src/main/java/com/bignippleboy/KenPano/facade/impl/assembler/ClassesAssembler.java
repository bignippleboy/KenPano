package com.bignippleboy.KenPano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.core.domain.*;

public class ClassesAssembler {
	
	public static ClassesDTO  toDTO(Classes  classes){
		if (classes == null) {
			return null;
		}
    	ClassesDTO result  = new ClassesDTO();
	    	result.setId (classes.getId());
     	    	result.setVersion (classes.getVersion());
     	    	result.setClassName (classes.getClassName());
     	    return result;
	 }
	
	public static List<ClassesDTO>  toDTOs(Collection<Classes>  classess){
		if (classess == null) {
			return null;
		}
		List<ClassesDTO> results = new ArrayList<ClassesDTO>();
		for (Classes each : classess) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static Classes  toEntity(ClassesDTO  classesDTO){
	 	if (classesDTO == null) {
			return null;
		}
	 	Classes result  = new Classes();
        result.setId (classesDTO.getId());
         result.setVersion (classesDTO.getVersion());
         result.setClassName (classesDTO.getClassName());
 	  	return result;
	 }
	
	public static List<Classes> toEntities(Collection<ClassesDTO> classesDTOs) {
		if (classesDTOs == null) {
			return null;
		}
		
		List<Classes> results = new ArrayList<Classes>();
		for (ClassesDTO each : classesDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
