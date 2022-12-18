package kr.or.youth.policy.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.youth.common.util.YouthPolicySessionFactory;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.policy.dao.IPolicyDao;
import kr.or.youth.policy.vo.YouthPolicy;

public class PolicyServiceImpl implements IPolicyService {

	
	  SqlSessionFactory sqlSessionFactory =
	  YouthPolicySessionFactory.getSqlSessionFactory();
	 

	@Override
	public void insertPolicy(String policyData) {

		SqlSession sqlSession = sqlSessionFactory.openSession(true);  // true 자동커밋
		IPolicyDao policyDao = sqlSession.getMapper(IPolicyDao.class); 
		try {
			System.out.println("서비스딴 policyData : " + policyData);
			ObjectMapper objectMapper = new ObjectMapper();
			List<YouthPolicy> list = null;

			try {
				list = objectMapper.readValue(policyData, new TypeReference<List<YouthPolicy>>() {});
				System.out.println("listBizId " + list.get(0).getBizId());
				System.out.println("listAge " + list.get(0).getAgeInfo());
				System.out.println("list.size() " + list.size());
				if (list.size() == 0) {
					throw new BizNotEffectedException();
				}
				
				// 먼저 table 다 지우고 정보 넣기
				policyDao.deleteAll();
				
				for(int i=0; i<list.size(); i++) {
					YouthPolicy youthPolicy = new YouthPolicy();
					youthPolicy = list.get(i);
					int resultCnt = policyDao.insertPolicy(youthPolicy);
				  
					if(resultCnt == 0) { throw new BizNotEffectedException(); } }
				 
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			sqlSession.close(); 
		}

	}

}
