package com.akchengtou.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.akchengtou.web.entity.Authentic;
import com.akchengtou.web.entity.AuthenticDAO;
import com.akchengtou.web.entity.AuthenticstatusDAO;
import com.akchengtou.web.entity.Building;
import com.akchengtou.web.entity.BuildingDAO;
import com.akchengtou.web.entity.Homehouse;
import com.akchengtou.web.entity.HomehouseDAO;
import com.akchengtou.web.entity.House;
import com.akchengtou.web.entity.HouseDAO;
import com.akchengtou.web.entity.Identity;
import com.akchengtou.web.entity.Identiytype;
import com.akchengtou.web.entity.Unit;
import com.akchengtou.web.entity.UnitDAO;

public class AuthenticManager {
	private AuthenticDAO authenticDao;
	private AuthenticstatusDAO authenticStatus;
	private HomehouseDAO homeHouseDao;
	private HouseDAO houseDao;
	private BuildingDAO buildingDao;
	private UnitDAO unitDao;

	/***
	 * 保存认证信息
	 * 
	 * @param authentic
	 */
	public void saveAuthentic(Authentic authentic) {
		getAuthenticDao().save(authentic);
	}

	/***
	 * 更新认证信息
	 * 
	 * @param authentic
	 */
	public void updateAuthentic(Authentic authentic) {
		getAuthenticDao().saveOrUpdate(authentic);
	}

	/***
	 * 根据authId 获取认证记录
	 * 
	 * @param authId
	 * @return
	 */
	public Authentic findAuthenticById(Integer authId) {
		List list = getAuthenticDao().findByProperty("authId", authId);
		if (list != null && list.size() > 0) {
			return (Authentic) list.get(0);
		}
		return null;
	}

	public Authentic findAuthenticByUserId(Integer userId) {
		List list = this.authenticDao.findAuthenticByUserId(userId);
		if (list != null && list.size() > 0) {
			return (Authentic) list.get(0);
		}
		return null;
	}
	
	public List findRankingByIdentitype(Identity type,Integer page,Integer size) {
		List list = this.authenticDao.findAuthenticByIdentiytype(type.getIdentiyTypeId(),page,size);
		if (list != null && list.size() > 0) {
			List results = new ArrayList();
			for(int i = 0;i<list.size();i++)
			{
				Object[] l =  (Object[]) list.get(i);
				
				Map map = new HashMap();
				map.put("userId", l[0]);
				map.put("name", l[1]);
				map.put("score", l[2]);
				map.put("image", l[3]);
				map.put("gender", l[4]);
				map.put("sort", i+1);
				map.put("orderList", 10);
				
				results.add(map);
			}
			return results;
		}
		return null;
	}

	/***
	 * 想通过id获取House记录
	 * 
	 * @param houseId
	 * @return
	 */
	public House findHouseById(Integer houseId) {
		House house = null;
		// 通过数据库进行查询
		if (houseId != null) {
			house = this.getHouseDao().findById(houseId);
		}
		return house;
	}

	/***
	 * 根据小区id获取楼栋
	 * 
	 * @param houseId
	 * @return
	 */
	public List findBuildingByHouseId(Integer houseId) {
		List list = null;
		if (houseId != null) {
			Homehouse house = new Homehouse();
			house.setHomeId(houseId);

			list = this.getBuildingDao().findByHomeHouse(house);
		}

		return list;
	}

	/***
	 * 根据楼栋Id获取单元
	 * 
	 * @param buildId
	 * @return
	 */
	public List findUnitByBuildId(Integer buildId) {
		List list = null;
		if (buildId != null) {
			
			Building build = new Building();
			build.setBuildId(buildId);
			list =getUnitDao().findByBuilding(build);
		}

		return list;
	}
	
	/***
	 * 根据单元id获取室
	 * @param unitId
	 * @return
	 */
	public List findHouseByUnitId(Integer unitId)
	{
		List list  = null ;
		if(unitId!=null)
		{
			Unit unit = new Unit();
			unit.setUnitId(unitId);
			list = getHouseDao().findByUnit(unit);
		}
		return list;
	}

	public AuthenticDAO getAuthenticDao() {
		return authenticDao;
	}

	@Autowired
	public void setAuthenticDao(AuthenticDAO authenticDao) {
		this.authenticDao = authenticDao;
	}

	public AuthenticstatusDAO getAuthenticStatus() {
		return authenticStatus;
	}

	@Autowired
	public void setAuthenticStatus(AuthenticstatusDAO authenticStatus) {
		this.authenticStatus = authenticStatus;
	}

	public HomehouseDAO getHomeHouseDao() {
		return homeHouseDao;
	}

	@Autowired
	public void setHomeHouseDao(HomehouseDAO homeHouseDao) {
		this.homeHouseDao = homeHouseDao;
	}

	public HouseDAO getHouseDao() {
		return houseDao;
	}

	@Autowired
	public void setHouseDao(HouseDAO houseDao) {
		this.houseDao = houseDao;
	}

	public BuildingDAO getBuildingDao() {
		return buildingDao;
	}

	@Autowired
	public void setBuildingDao(BuildingDAO buildingDao) {
		this.buildingDao = buildingDao;
	}

	public UnitDAO getUnitDao() {
		return unitDao;
	}

	@Autowired
	public void setUnitDao(UnitDAO unitDao) {
		this.unitDao = unitDao;
	}
}
