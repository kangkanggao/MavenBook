package com.highlion.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
/**
 * 
 * @author 鍗楅槼寰峰垰<br>
 * 2015骞�5鏈�11鏃� 涓嬪崍3:56:16<br>
 *
 * 绫昏鏄�:绾犳榛樿鎯呭喌涓嬫棩鏈熻浆鎹㈤敊璇殑闂
 */
public class MyBeanUtils {
	/**
	 * 
	 * @param bean 瑕佽璧嬪�肩殑JavaBean
	 * @param properties 鍖呭惈鍊肩殑map瀵硅薄
	 * @param dateFormat 濡傛灉鏈夋棩鏈熷垯闇�瑕佹寚瀹氭棩鏈熸牸寮�
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void populate(Object bean, Map properties, String dateFormat) {
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
		if (dateFormat!=null&&!dateFormat.isEmpty()) {		
			DateTimeConverter dtConverter = new DateConverter();
			dtConverter.setPattern(dateFormat);
			convertUtilsBean.deregister(Date.class);
			convertUtilsBean.register(dtConverter, Date.class);
		}
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean,new PropertyUtilsBean());
		try {
			beanUtilsBean.populate(bean, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("rawtypes")
	public static void populate(Object bean, Map properties) {
		populate(bean, properties, null);
		
	}
}
