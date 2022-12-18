package kr.or.youth.login.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.youth.common.util.YouthPolicySessionFactory;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.dao.ILoginDao;
import kr.or.youth.login.vo.MemberVO;

public class LoginServiceImpl implements ILoginService{

	// 팩토리 받고
	SqlSessionFactory sqlSessionFactory = YouthPolicySessionFactory.getSqlSessionFactory();
	
	// 멤버 db로 등록하기
	@Override
	public void registeMember(MemberVO member) throws BizNotEffectedException {
		
		// 세션 받고
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		ILoginDao loginDao = sqlSession.getMapper(ILoginDao.class);
		
		try {
			int resultCnt = loginDao.insertMember(member);
			if(resultCnt == 0) {
				throw new BizNotEffectedException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizNotEffectedException();
		} finally {
			sqlSession.close();
		}
	}

	
	// 입력받은 memId에 따라 멤버 가져오기
	@Override
	public MemberVO getMember(String memId) throws BizNotEffectedException {
		
		// 세션 받고
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		ILoginDao loginDao = sqlSession.getMapper(ILoginDao.class);
		MemberVO member = new MemberVO();
		try {
			member = loginDao.getMember(memId);
			
			if(member == null) {
				throw new BizNotEffectedException(); 
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new BizNotEffectedException();
		}finally {
			sqlSession.close();
		}
		
		return member;
	}


	// 멤버에 100포인트 추가
	@Override
	public void updatePoint(String memId) {
		// 세션 받고
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		ILoginDao loginDao = sqlSession.getMapper(ILoginDao.class);
		
		try {
			System.out.println("dao로 가는 memId : " + memId);
			int resultCnt = loginDao.updatePoint(memId);
			if(resultCnt == 0) {
				throw new BizNotEffectedException();
			}
		} catch (Exception e) {
			
		} finally {
			sqlSession.close();
		}
		
		
		
	}


	// 회원 이름에 따른 모든 출석한 날 얻기
	@Override
	public String getMemAttendDate(String memId) throws BizNotEffectedException {
		// 세션 받고
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		ILoginDao loginDao = sqlSession.getMapper(ILoginDao.class);
		System.out.println("서비스 딴의 memId : " + memId);
		try {
			String memAttendDate = loginDao.getMemAttendDate(memId);
			System.out.println("loginDao.getMemAttendDate(memId) : " + loginDao.getMemAttendDate(memId));
			System.out.println("서비스딴의 memAttendDate : " + memAttendDate);
			
			
			// null일때 따로 하자!
			if(memAttendDate == null) {
				throw new BizNotEffectedException();
			}else {
				return memAttendDate;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizNotEffectedException();
		} finally {
			sqlSession.close();
		}
		
	}
	
	
	
	
	
	
	
}
