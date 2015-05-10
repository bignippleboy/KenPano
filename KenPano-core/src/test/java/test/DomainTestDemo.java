package test;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

import com.bignippleboy.KenPano.core.domain.Mapspot;
import com.bignippleboy.KenPano.core.domain.Organization;
import com.bignippleboy.KenPano.core.domain.PanoInfo;
import com.bignippleboy.KenPano.core.domain.Vtour;
import com.bignippleboy.KenPano.core.domain.VtourMap;

/**
 * 领域层的测试类，直接继承KoalaBaseSpringTestCase
 * 
 * @author lingen
 *
 */
public class DomainTestDemo extends KoalaBaseSpringTestCase {

	@Test
	public void test() {
		// 在这里，你可以直接使用InstanceFactory获取各种spring的bean
		// EntityRepository repository =
		// InstanceFactory.getInstance(EntityRepository.class);
		// 同样，你可以直接调用领域类的领域方法
		// User.addUser("koala");
		PanoInfo info = new PanoInfo();
		info.setImg("img");
		info.setName("name");
		info.setPath("path");
		info.save();

		Organization org = new Organization();
		org.setName("foss");
		org.setSerialNumber("111");
		org.save();
	}
	
	@Test
	public void testSql() {
//		QueryChannelService queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
//
//		List<Object> conditionVals = new ArrayList<Object>();
//	   	StringBuilder jpql = new StringBuilder("select _Vtour.vtourMap from Vtour _Vtour   where 1=1 ");
	   	Long id = 2L;
//	   	String uuid="00ca0a0f-c3e3-49c9-9baf-1485efefb233";
//	   	if (id != null && !"".equals(id)) {
//	   		jpql.append(" and _Vtour.id = ?");
//	   		conditionVals.add(MessageFormat.format("{0}", Long.valueOf(id)));
//	   	}		
//		List<VtourMap> mapspots = queryChannel
//			.createJpqlQuery(jpql.toString())
//			.setParameters(conditionVals)
//			.list();
		Set<Mapspot> mapspots = Vtour.get(Vtour.class, id).getVtourMap().getMapspots();
		for(Mapspot mapspot: mapspots) {
			System.out.println(mapspot.getSceneName());
		}
		System.out.println("over..");
	}

	@Test
	public void testPer() {
		assertEquals("not equal", "65%", getPercent(65, 100));
	}

	public static String getPercent(int x, int total) {
		String result = "";// 接受百分比的值
		double x_double = x * 1.0;
		double tempresult = x / total;
		// NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是一种方法
		// nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("0.00%"); // ##.00%
														// 百分比格式，后面不足2位的用0补齐
		// result=nf.format(tempresult);
		result = df1.format(tempresult);
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getPercent(65, 100));
		int diliverNum = 3;// 举例子的变量
		int queryMailNum = 9;// 举例子的变量
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((float) diliverNum
				/ (float) queryMailNum * 100);
		System.out.println("diliverNum和queryMailNum的百分比为:" + result + "%");
//		entity.setDiliverPer(result + "%");
//		resultList.add(entity);
	}

}