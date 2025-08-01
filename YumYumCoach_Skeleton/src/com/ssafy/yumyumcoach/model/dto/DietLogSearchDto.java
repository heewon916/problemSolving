package com.ssafy.yumyumcoach.model.dto;

/**
 * <h2>DietLogSearchDto</h2>
 * <p>
 * 식단 로그 검색 조건을 전달하는 DTO입니다.<br>
 * UI(드롭다운) 요구 사항에 맞춰 <b>날짜 검색</b> 또는 <b>음식명(키워드) 검색</b>을 모두 지원하도록 설계했습니다.
 * </p>
 *
 * <pre>
 * ┌─────────────┐     keyword → "2025-04-01"
 * │  DATE       │ ──────────────────────────────► 날짜 필터
 * ├─────────────┤     keyword → "김밥"
 * │  FOOD       │ ──────────────────────────────► 음식명 포함 여부
 * ├─────────────┤
 * │  ALL        │  (keyword 무시) 전체 조회
 * └─────────────┘
 * </pre>
 */
public class DietLogSearchDto {

	/** 검색 종류 열거형 */
	public enum SearchType {
		/** 날짜(y-M-d)로만 필터링 */
		DATE,
		/** 음식명 키워드로만 필터링 */
		FOOD,
		/** 조건 없이 전체 조회 */
		ALL
	}

	/** 검색 구분 (기본 = ALL) */
	private SearchType type = SearchType.ALL;

	/**
	 * 검색 키워드
	 * <ul>
	 * <li>type == DATE → YYYY-MM-DD 형식의 날짜</li>
	 * <li>type == FOOD → 음식명 키워드</li>
	 * <li>type == ALL → 사용되지 않음(null 혹은 빈문자열)</li>
	 * </ul>
	 */
	private String keyword;

	/* ──────────── 생성자 ──────────── */

	public DietLogSearchDto() {
	}

	public DietLogSearchDto(SearchType type, String keyword) {
		this.type = type;
		this.keyword = keyword;
	}

	/* ──────────── Getter / Setter ──────────── */

	public SearchType getType() {
		return type;
	}

	public void setType(SearchType type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/* ──────────── Object Overrides ──────────── */

	@Override
	public String toString() {
		return "DietLogSearchDto{" + "type=" + type + ", keyword='" + keyword + '\'' + '}';
	}
}
