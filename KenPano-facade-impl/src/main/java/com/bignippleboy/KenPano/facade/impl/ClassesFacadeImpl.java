package com.bignippleboy.KenPano.facade.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.KenPano.application.ClassesApplication;
import com.bignippleboy.KenPano.core.domain.Classes;
import com.bignippleboy.KenPano.facade.ClassesFacade;
import com.bignippleboy.KenPano.facade.dto.ClassesDTO;
import com.bignippleboy.KenPano.facade.impl.assembler.ClassesAssembler;

@Named
public class ClassesFacadeImpl implements ClassesFacade {

	@Inject
	private ClassesApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getClasses(Long id) {
		return InvokeResult.success(ClassesAssembler.toDTO(application.getClasses(id)));
	}
	
	public InvokeResult creatClasses(ClassesDTO classesDTO) {
		application.creatClasses(ClassesAssembler.toEntity(classesDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateClasses(ClassesDTO classesDTO) {
		application.updateClasses(ClassesAssembler.toEntity(classesDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeClasses(Long id) {
		application.removeClasses(application.getClasses(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeClassess(Long[] ids) {
		Set<Classes> classess= new HashSet<Classes>();
		for (Long id : ids) {
			classess.add(application.getClasses(id));
		}
		application.removeClassess(classess);
		return InvokeResult.success();
	}
	
	public List<ClassesDTO> findAllClasses() {
		return ClassesAssembler.toDTOs(application.findAllClasses());
	}
	
	public Page<ClassesDTO> pageQueryClasses(ClassesDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _classes from Classes _classes  where 1=1 ");
	   	if (queryVo.getClassName() != null && !"".equals(queryVo.getClassName())) {
	   		jpql.append(" and _classes.className like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getClassName()));
	   	}		
        Page<Classes> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<ClassesDTO>(pages.getStart(), pages.getResultCount(),pageSize, ClassesAssembler.toDTOs(pages.getData()));
	}
	
	public ClassesDTO findClassesByClasses(Long id) {
		String jpql = "select e from Classes o right join o.classes e where o.id=?";
		Classes result = (Classes) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[] { id }).singleResult();
		ClassesDTO  dto = new ClassesDTO();
		if (result != null) {
			try {
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	
}
