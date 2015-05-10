package com.bignippleboy.KenPano.facade;

import java.util.List;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;

public interface ClassesFacade {

	public InvokeResult getClasses(Long id);
	
	public InvokeResult creatClasses(ClassesDTO classes);
	
	public InvokeResult updateClasses(ClassesDTO classes);
	
	public InvokeResult removeClasses(Long id);
	
	public InvokeResult removeClassess(Long[] ids);
	
	public List<ClassesDTO> findAllClasses();
	
	public Page<ClassesDTO> pageQueryClasses(ClassesDTO classes, int currentPage, int pageSize);
	

}

