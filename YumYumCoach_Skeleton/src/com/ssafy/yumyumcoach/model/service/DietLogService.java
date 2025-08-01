package com.ssafy.yumyumcoach.model.service;

import java.util.List;
import com.ssafy.yumyumcoach.model.dto.DietLogDto;
import com.ssafy.yumyumcoach.model.dto.DietLogSearchDto;

/**
 * <h2>DietLogService</h2>
 * <p>
 * DAO 레이어와 Controller(UI) 사이의 <b>비즈니스 로직</b> 인터페이스입니다. <br>
 * 현재 요구 사항 기준으로는 단순 위임 역할이지만, 향후 권한 체크·캐싱·페이징 처리 등을 추가하기 위해 분리해 둡니다.
 * </p>
 *
 * <pre>
 * ┌────────────┐   DietLogSearchDto(type,keyword)   ┌─────────────┐
 * │   View     │ ──────────────────────────────────►│  Service    │
 * └────────────┘   List<DietLogDto>                 └─────────────┘
 * </pre>
 */
public interface DietLogService {

	/**
	 * 검색 조건에 맞는 식단 로그 목록을 반환합니다.
	 * <ul>
	 * <li>{@code searchDto == null} 또는 {@code type == ALL} 전체 리스트</li>
	 * <li>{@code type == DATE} 해당 날짜만 반환</li>
	 * <li>{@code type == FOOD} 음식명 키워드 포함 여부</li>
	 * </ul>
	 *
	 * @param searchDto 검색 조건 DTO
	 * @return 정렬(날짜 → 식사구분)된 불변 리스트
	 */
	List<DietLogDto> searchAll(DietLogSearchDto searchDto);

	/**
	 * 식단 로그 ID(PK)로 단일 로그를 조회합니다.
	 *
	 * @param id 식단 로그 ID
	 * @return {@link DietLogDto} (없으면 {@code null})
	 */
	DietLogDto search(int id);
}
