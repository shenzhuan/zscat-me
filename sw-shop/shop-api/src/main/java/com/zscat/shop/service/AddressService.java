 /*
  * Powered By zsCat, Since 2014 - 2020
  */
package com.zscat.shop.service;

import java.util.List;

import com.zsCat.common.base.BaseService;
import com.zscat.shop.model.Address;

/**
 * 
 * @author zsCat 2017-4-14 13:56:18
 * @Email: 951449465@qq.com
 * @version 4.0v
 */
public interface AddressService extends BaseService<Address>  {

	List<Address> selectByMemberId();

	int updateDef(String addressId, String string);


}
