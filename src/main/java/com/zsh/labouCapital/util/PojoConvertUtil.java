package com.zsh.labouCapital.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import com.google.common.collect.Lists;

/**
 * VoPo 转换工具（浅复制）
 * 
 * @author czp
 * @version 2019-01-24
 */
public class PojoConvertUtil {

	private static ConcurrentHashMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<String, BeanCopier>();

	/**
	 * 初始化 BeanCopier
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private static BeanCopier initCopier(Class<?> source, Class<?> target) {
		BeanCopier find = beanCopierMap.get(source.getName() + "_" + target.getName());
		if (find != null) {
			return find;
		}
		BeanCopier beanCopier = BeanCopier.create(source, target, false);
		beanCopierMap.put(source.getName() + "_" + target.getName(), beanCopier);
		return beanCopier;
	}

	/**
	 * 获取BeanCopier
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
		BeanCopier beanCopier = beanCopierMap.get(source.getClass().getName() + "_" + target.getName());
		if (beanCopier != null) {
			return beanCopier;
		}
		return initCopier(source, target);
	}

	/**
	 * Pojo 类型转换（浅复制，字段名&类型相同则被复制）
	 * 
	 * @param source
	 * @param targetClass
	 * @param <T>
	 * @return
	 */
	public static <T> T convert(Object source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		BeanCopier beanCopier = getBeanCopier(source.getClass(), targetClass);
		try {
			T target = targetClass.newInstance();
			beanCopier.copy(source, target, null);
			return target;

		} catch (Exception e) {
			throw new RuntimeException("对象拷贝失败" + source + "_" + targetClass);
		}
	}

	/**
	 * 属性值增量拷贝覆盖（浅复制，字段名&类型相同&source属性值不为空则被替换）
	 */
	public static void incrementalCopies(Object source, final Object target) {
		if (source == null) {
			return;
		}
		BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), true);
		try {
			beanCopier.copy(source, target, new Converter() {

				@Override
				public Object convert(Object sourceValue, @SuppressWarnings("rawtypes") Class targetClass, Object methodName) {
					if (sourceValue != null) {
						return sourceValue;
					}
					String mname = methodName.toString();
					String name = mname.substring(3, 4).toLowerCase() + mname.substring(4, mname.length());
					Object value = null;
					try {
						value = getProperty(target, name);
					} catch (Exception e) {
						throw new RuntimeException("对象拷贝失败" + sourceValue + "_" + targetClass);
					}
					return value;

				}
			});
			return;

		} catch (Exception e) {
			throw new RuntimeException("对象拷贝失败" + source + "_" + target);
		}
	}

	/**
	 * 反射获取所有属性值
	 * @param owner
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	private static Object getProperty(Object owner, String fieldName) throws Exception {

		Field field = getFieldByClasss(fieldName, owner);
		field.setAccessible(true);
		Object property = field.get(owner);

		return property;
	}

	/**
	 * 根据属性名获取属性元素，包括各种安全范围和所有父类
	 * 
	 * @param fieldName
	 * @param object
	 * @return
	 */
	private static Field getFieldByClasss(String fieldName, Object object) {
		Field field = null;
		Class<?> clazz = object.getClass();

		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (Exception e) {
				// 这里甚么都不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会进入

			}
		}
		return field;

	}

	/**
	 * Pojo 类型转换（浅复制，字段名&类型相同则被复制）
	 * 
	 * @param source
	 * @param targetClass
	 * @param <E>
	 * @return
	 */
	public static <S extends Object, T extends Object> List<T>  convert(List<S> source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		try {
			if (source.isEmpty()) {
				return Lists.newArrayList();
			}
			List<T> result = Lists.newArrayList();
			for (S each : source) {
				result.add(convert(each, targetClass));
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException("对象拷贝失败" + source + "_" + targetClass);
		}
	}

}
