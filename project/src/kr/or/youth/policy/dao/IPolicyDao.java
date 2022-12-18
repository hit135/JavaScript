package kr.or.youth.policy.dao;

import kr.or.youth.policy.vo.YouthPolicy;

public interface IPolicyDao {

	
	/**
	 * delete all of young_policy table
	 * @author pc32 
	 */
	public void deleteAll();

	
	/**
	 * insert youth policy to young_policy table
	 * @param youthPolicy
	 * @return
	 * @author pc32
	 */
	public int insertPolicy(YouthPolicy youthPolicy);

}
