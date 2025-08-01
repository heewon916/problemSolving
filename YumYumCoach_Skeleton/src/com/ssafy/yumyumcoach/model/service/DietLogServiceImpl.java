package com.ssafy.yumyumcoach.model.service;

import java.util.List;

import com.ssafy.yumyumcoach.model.dao.DietLogDao;
import com.ssafy.yumyumcoach.model.dao.DietLogDaoImpl;
import com.ssafy.yumyumcoach.model.dto.DietLogDto;
import com.ssafy.yumyumcoach.model.dto.DietLogSearchDto;

/**
 * <h2>DietLogServiceImpl</h2>
 * <p>
 * {@link DietLogService} 구현체.<br>
 * 현재는 DAO 단순 위임이지만, 추후<br>
 * - 캐싱, <br>
 * - 트랜잭션/동기화, <br>
 * - 권한 검증 <br>
 * 등 비즈니스 로직을 추가하기 위한 확장 지점을 마련해 둡니다.
 * </p>
 */
public class DietLogServiceImpl implements DietLogService {

	/** DAO 의존성 (기본적으로 DietLogDaoImpl 사용) */
	private final DietLogDao dao;

	/* ──────────── 생성자 ──────────── */

	/** 기본 생성자 : 내부에서 DAO 구현체 직접 생성 */
	public DietLogServiceImpl() {
		this(new DietLogDaoImpl());
	}

	/**
	 * DAO 주입 생성자 (테스트·Mocking 용)
	 * 
	 * @param dao 외부에서 주입할 DAO 구현체
	 */
	public DietLogServiceImpl(DietLogDao dao) {
		this.dao = dao;
	}

	/* ──────────── Service 구현 ──────────── */

	/** 검색 조건이 null 이면 ALL 조건으로 대체 후 DAO 호출 */
	@Override
	public List<DietLogDto> searchAll(DietLogSearchDto searchDto) {
		if (searchDto == null) {
			searchDto = new DietLogSearchDto(); // type=ALL (전체)
		}
		return dao.searchAll(searchDto);
	}

	/** ID 단건 조회 (예외 처리·로깅은 추후 추가 가능) */
	@Override
	public DietLogDto search(int id) {
		// complete code #02
		// null 을 return 하면 안됩니다. Dao Layer 의 적절한 method를 호출하여 Business Logic 을 완성하세요.
		return null;
	}
}
