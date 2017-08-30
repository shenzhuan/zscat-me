package com.zsCat.common.base;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



public  class ServiceMybatis<T extends BaseEntity> implements BaseService<T> {

	@Autowired
	protected MyMapper<T> mapper;
	
	
	
	public T selectOne(T record) {

		if(mapper.select(record)!=null && mapper.select(record).size()>0){
			return mapper.select(record).get(0);
		}
		return null;
		
	}
	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * @param < extend T>
	 */
	public List<T> select(T record) {
		//record.set("delFlag", Constant.DEL_FLAG_NORMAL);
		return mapper.select(record);
	}
	
	public List<T> select(T record,String orderSqlStr){
		Example example = new Example(record.getClass(),false);
		Criteria criteria = example.createCriteria();

		Map<String, Object> map=transBean2Map(record);
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if("".equals(entry.getValue())) continue;
			criteria.andEqualTo(entry.getKey().toString(), entry.getValue());
		}
		example.setOrderByClause(orderSqlStr);
		return mapper.selectByExample(example);
	}
	
	/**
	 * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
	 * @param < extend T>
	 */
	public int selectCount(T record) {
	//	record.set("delFlag", Constant.DEL_FLAG_NORMAL);
		return mapper.selectCount(record);
	}

	/**
	 * 根据主键进行查询,必须保证结果唯一 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param < extend T>
	 */
	public T selectByPrimaryKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}



	/**
	 * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	 * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	 * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	 * 
	 * @param < extend T>
	 */
	public int insertSelective(T record) {
		return mapper.insertSelective(record);
	}

	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	 * 
	 * @param < extend T>
	 */
	public int delete(T key) {
		return mapper.delete(key);
	}

	/**
	 * 通过主键进行删除,这里最多只会删除一条数据 单个字段做主键时,可以直接写主键的值 联合主键时,key可以是实体类,也可以是Map
	 * 
	 * @param < extend T>
	 */
	public int deleteByPrimaryKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}



	/**
	 * 根据主键进行更新 只会更新不是null的数据
	 * 
	 * @param < extend T>
	 */
	public int updateByPrimaryKeySelective(T record) {

		return mapper.updateByPrimaryKeySelective(record);
	}
	
	
	

	/**
	 * 单表分页
	 * @param pageNum 页码
	 * @param pageSize 条数
	 * @param record 条件实体
	 * @return
	 */
	public PageInfo<T> selectPage(int pageNum, int pageSize, T record) {
	//	record.set("delFlag", Constant.DEL_FLAG_NORMAL);
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<T>(mapper.select(record));
	}
	
	/**
	 * @Description:(单表分页可排序) 
	 * @param:@param pageNum
	 * @param:@param pageSize
	 * @param:@param record
	 * @param:@param orderSqlStr (如:id desc)
	 * @return:PageInfo<T>
	 */
	public PageInfo<T> selectPage(int pageNum, int pageSize, T record,String orderSqlStr) {
		Example example = new Example(record.getClass(),false);
		Criteria criteria = example.createCriteria();
		//	criteria.andEqualTo("delFlag", Constant.DEL_FLAG_NORMAL);
		Map<String, Object> map=transBean2Map(record);
		for(Map.Entry<String, Object> entry : map.entrySet()){
			if("".equals(entry.getValue())) continue;
			criteria.andEqualTo(entry.getKey().toString(), entry.getValue());
		}
		example.setOrderByClause(orderSqlStr);
		PageHelper.startPage(pageNum, pageSize);
		List<T> list = mapper.selectByExample(example);
		return new PageInfo<T>(list);
	}


	@Override
	public int insertList(List<T> list) {
		return mapper.insertList(list);
	}
	
	/**
	 * 保存或者更新，根据传入id主键是不是null来确认
	 * 
	 * @param record
	 * @return 影响行数
	 */
	@Override
	public int insertOrUpdate(T record) {
		int count = 0;
		if (record.getId() == null) {
			count = this.insertSelective(record);
		} else {
			count = this.updateByPrimaryKeySelective(record);
		}
		return count;
	}
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if(obj == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

}
