package com.bignippleboy.KenPano.core.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

import com.bignippleboy.KenPano.core.domain.Employee;
import com.bignippleboy.KenPano.core.domain.Organization;

public class EmployeeTest extends KoalaBaseSpringTestCase {
	@Test
	public void test() {
		Employee employee = new Employee();
		employee.setName("张三");
		employee.setAge(18);
		employee.save();
		
		Organization org = new Organization();
		org.setName("foss");
		org.setSerialNumber("111");
		org.save();
		
		assertEquals("张三", Employee.get(Employee.class, employee.getId()).getName());
		assertTrue(Employee.findByAgeRange(0, 20).contains(employee));
		
		employee.assignTo(org);
		assertEquals(org, Employee.get(Employee.class, employee.getId()).getOrg());
		
		employee.setAge(24);
		employee.save();
		assertEquals(24, Employee.get(Employee.class, employee.getId()).getAge());
		assertTrue(Employee.findByAgeRange(10, 30).contains(employee));
		
		employee.remove();
		org.remove();
	}
}
