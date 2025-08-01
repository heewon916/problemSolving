package com.ssafy.yumyumcoach.model.dao;

import java.util.*;
import java.util.stream.Collectors;

import com.ssafy.yumyumcoach.model.dto.DietLogDto;
import com.ssafy.yumyumcoach.model.dto.DietLogSearchDto;
import com.ssafy.yumyumcoach.model.dto.DietLogSearchDto.SearchType;
import com.ssafy.yumyumcoach.util.DietDataSAXParser;

/**
 * {@link DietLogDao} 구현체. • XML 파싱 → 리스트·맵 캐싱 • DATE/FOOD/ALL 타입으로 검색
 */
public class DietLogDaoImpl implements DietLogDao {

	private List<DietLogDto> dietList;
	private Map<Integer, DietLogDto> dietMap;

	private static final List<String> MEAL_ORDER = List.of("아침", "점심", "저녁", "간식");

	public DietLogDaoImpl() {
		loadData();
	}

	@Override
	public void loadData() {
		List<DietLogDto> parsed = DietDataSAXParser.parse("res/식단데이터.xml");
		// 정렬: 날짜 ↑ → 식사구분 순
		parsed.sort((a, b) -> {
			int c = a.getDate().compareTo(b.getDate());
			return (c != 0) ? c
					: Integer.compare(MEAL_ORDER.indexOf(a.getMealType()), MEAL_ORDER.indexOf(b.getMealType()));
		});
		dietList = Collections.unmodifiableList(parsed);
		dietMap = dietList.stream().collect(Collectors.toUnmodifiableMap(DietLogDto::getDietLogId, d -> d));
	}

	/**
	 * SearchType에 따라 • DATE → date.contains(keyword) • FOOD →
	 * anyFood.name.contains(keyword) • ALL → 전체
	 */
	@Override
	public List<DietLogDto> searchAll(DietLogSearchDto cond) {
		// complete code #03
		// SearchType에 따라서 올바른 데이터를 반환하도록 코드를 작성하세요.
		return null;
	}

	@Override
	public DietLogDto search(int id) {
		return dietMap.get(id);
	}
}
