package com.ssafy.yumyumcoach.model.dao;

import java.util.List;
import com.ssafy.yumyumcoach.model.dto.DietLogDto;
import com.ssafy.yumyumcoach.model.dto.DietLogSearchDto;

/**
 * <p>
 * 식단 로그(DietLog) 데이터를 로드·조회하기 위한 DAO 인터페이스입니다.
 * </p>
 * <ul>
 * <li><b>loadData()</b> : XML·CSV 등 외부 데이터 소스를 읽어 메모리에 적재</li>
 * <li><b>searchAll()</b> : 검색 조건(DietLogSearchDto)에 맞는 식단 로그 리스트 반환</li>
 * <li><b>search()</b> : 식단 ID(pk)로 단일 식단 로그 조회</li>
 * </ul>
 *
 * <p>
 * 구현체(예: {@code DietLogDaoImpl})에서는 SAX 파서나 DOM 파서를 이용해 {@code res/식단데이터.xml}
 * 등을 파싱하여 {@code List<DietLogDto>} 형태로 관리하도록 합니다.
 * </p>
 */
public interface DietLogDao {

	/**
	 * 외부 XML/JSON/DB 등에서 식단 로그 데이터를 읽어와 DAO 구현체 내부 컬렉션(예: List)에 적재합니다.
	 */
	void loadData();

	/**
	 * 검색 조건에 맞는 모든 식단 로그를 반환합니다.
	 *
	 * @param searchDto 검색 조건(날짜·음식명 등) DTO
	 * @return 조건에 부합하는 식단 로그 목록
	 */
	List<DietLogDto> searchAll(DietLogSearchDto searchDto);

	/**
	 * 식단 ID(PK)로 단일 식단 로그를 조회합니다.
	 *
	 * @param id 식단 로그 ID
	 * @return 해당 ID에 대응하는 {@code DietLogDto}, 존재하지 않으면 {@code null}
	 */
	DietLogDto search(int id);
}
