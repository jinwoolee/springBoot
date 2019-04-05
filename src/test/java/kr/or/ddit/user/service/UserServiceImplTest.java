package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;


@Transactional
@Profile("dev")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest{
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Autowired
	private DataSource datasource;
	
	@Before
	public void setup(){
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource("kr/or/ddit/config/db/init.sql"));
		rdp.setContinueOnError(false);
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	/**
	 * Method : testGetAllUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 전체 사용자 조회 테스트
	 */
	@Test
	public void testGetAllUser() {
		/***Given***/
			
		/***When***/
		List<UserVo> userList = userService.getAllUser();
		for(UserVo userVo : userList)
			System.out.println(userVo);

		/***Then***/
		assertNotNull(userList);
		assertEquals(113, userList.size());
	}
	
	/**
	 * Method : testSelectUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 조회 테스트
	 */
	@Test
	public void testSelectUser(){
		/***Given***/
			
		/***When***/
		UserVo userVo = userService.selectUser("brown");

		/***Then***/
		assertEquals("brown", userVo.getUserId());
		assertEquals("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44", userVo.getPass());
	}
	
	/**
	 * Method : testSelectUserNotExists
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 존재하지 않는 사용자 조회 테스트
	 */
	@Test
	public void testSelectUserNotExists(){
		/***Given***/
			
		/***When***/
		UserVo userVo = userService.selectUser("brownNotExists");

		/***Then***/
		assertNull(userVo);
	}
	
	
	/**
	 * Method : testSelectUserPagingList
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		Map<String, Object> resultMap =
				userService.selectUserPagingList(pageVo);
		
		List<UserVo> userList = (List<UserVo>)resultMap.get("userList");
		int userCnt = (Integer)resultMap.get("userCnt");
		
		for(UserVo user : userList)
			System.out.println(user);
		
		System.out.println("userCnt : " + userCnt);
		
		/***Then***/
		//userList
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		//userCnt
		assertEquals(113, userCnt);
	}
	
	/**
	 * Method : testInsertUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 테스트
	 */
	@Test
	public void testInsertUser(){
		/***Given***/
		UserVo userVo = new UserVo();
		userVo.setUserId("test1");
		userVo.setUserNm("테스트");
		userVo.setAlias("별명");
		userVo.setAddr1("대전 중구 대흥로 76");
		userVo.setAddr2("2층 ddit");
		userVo.setZipcode("34942");
		userVo.setPass("testpass");
		
		/***When***/
		int insertCnt = userService.insertUser(userVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	 * Method : testUpdateUser
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 정보 수정 테스트
	 */
	@Test
	public void testUpdateUser(){
		/***Given***/
		UserVo userVo = new UserVo();
		userVo.setUserId("test1");
		userVo.setUserNm("테스트");
		userVo.setAlias("별명");
		userVo.setAddr1("대전 중구 대흥로 76");
		userVo.setAddr2("2층 ddit");
		userVo.setZipcode("34942");
		userVo.setPass("testpass");
		int insertCnt = userService.insertUser(userVo);
		
		/***When***/
		userVo.setUserNm("테스트_변경");
		int updateCnt = userService.updateUser(userVo);
		UserVo selectUser = userService.selectUser(userVo.getUserId());
		
		/***Then***/
		assertEquals(1, insertCnt);
		assertEquals(1, updateCnt);
		assertEquals("테스트_변경", selectUser.getUserNm());
	}
	
	/**
	 * Method : testEncryptPass
	 * 작성자 : SEM
	 * 변경이력 :
	 * Method 설명 : 사용자 비밀번호 일괄 변경
	 */
	@Test
	public void testEncryptPass(){
		/***Given***/
		
		/***When***/
		int updateCnt = userService.encryptPass();

		/***Then***/
		assertEquals(113, updateCnt);

	}
}





