package kr.or.youth.login.dao;

import kr.or.youth.login.vo.MemberVO;

public interface ILoginDao {

	/**
	 * insert Member to DB
	 * @param member
	 * @author pc32
	 */
	public int insertMember(MemberVO member);

	/**
	 * get member by memId
	 * @param memId
	 * @return MemberVO
	 * @author hit13
	 */
	public MemberVO getMember(String memId);

	/**
	 * update point +100 for memId 
	 * @param memId
	 * @return resultCnt
	 * @author hit13
	 */
	public int updatePoint(String memId);

	/**
	 * 
	 * @param memId
	 * @return
	 */
	public String getMemAttendDate(String memId);



}
