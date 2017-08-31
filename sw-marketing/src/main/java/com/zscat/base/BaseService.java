package com.zscat.base;

import java.util.List;

import com.github.pagehelper.PageInfo;


public interface BaseService<T extends BaseEntity> {
	
	/**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	* @param < extend T>
	 */
    public List<T> select(T record);
    public List<T> select(T record, String order);

    /**
	 * 根据实体类不为null的字段查询总数,条件全部使用=号and条件
	* @param < extend T>
	 */
    public int selectCount(T record);

    /**
	 * 根据主键进行查询,必须保证结果唯一
	*  单个字段做主键时,可以直接写主键的值
	*  联合主键时,key可以是实体类,也可以是Map
	* @param < extend T>
	 */
    public T selectById(Object key);
    
    public T selectOne(T record);
   
    /**
	 * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
	*支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
	*优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
	* @param < extend T>
	 */
    public int insert(T record);

    /**
	 * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
	* @param < extend T>
	 */
    public int delete(T key);

    /**
	 * 通过主键进行删除,这里最多只会删除一条数据
	*单个字段做主键时,可以直接写主键的值
	*联合主键时,key可以是实体类,也可以是Map
	* @param < extend T>
	 */
    public int deleteById(Object key);


    /**
	 *根据主键进行更新
	*只会更新不是null的数据
	* @param < extend T>
	 */
    public int updateById(T record);

    int  insertList(List<T> list);
    
    
    public PageInfo<T> selectPage(int pageNum, int pageSize, T record);
    public PageInfo<T> selectPage(int pageNum, int pageSize, T record, String orderStr);
}
