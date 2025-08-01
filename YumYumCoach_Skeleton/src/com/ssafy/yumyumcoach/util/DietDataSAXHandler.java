package com.ssafy.yumyumcoach.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.yumyumcoach.model.dto.DietLogDto;
import com.ssafy.yumyumcoach.model.dto.FoodDto;

/**
 * <h2>DietDataSAXHandler</h2>
 * <p>
 * SAX 파서 핸들러: {@code res/식단데이터.xml} 을 읽어 {@link DietLogDto} 리스트를 생성합니다.
 * </p>
 *
 * <pre>
 * <식단>
 *   <식단ID>1</식단ID>
 *   <날짜>2025-01-01</날짜>
 *   <식사구분>아침</식사구분>
 *   <음식>
 *     <식품> … </식품>
 *   </음식>
 * </식단>
 * </pre>
 */
public class DietDataSAXHandler extends DefaultHandler {

	/** 파싱 완료 후 반환될 식단 로그 리스트 */
	private final List<DietLogDto> diets = new ArrayList<>();

	/** 현재 파싱 중인 식단 / 음식 객체 */
	private DietLogDto currentDiet;
	private FoodDto currentFood;

	/** 문자 데이터 누적 버퍼 */
	private final StringBuilder sb = new StringBuilder();

	/** 외부에서 파싱 결과를 가져갈 때 호출 */
	public List<DietLogDto> getDiets() {
		return diets;
	}

	/* ───────────────────── SAX 이벤트 ───────────────────── */

	/** 태그 시작 */
	@Override
	public void startElement(String uri, String local, String qName, Attributes atts) {
		// complete code #04
		// 코드를 완성하세요.
	}

	/** 태그 내부 문자 */
	@Override
	public void characters(char[] ch, int start, int length) {
		sb.append(ch, start, length);
	}

	/** 태그 종료 */
	@Override
	public void endElement(String uri, String local, String qName) {
		// complete code #05
		// 코드를 완성하세요.
	}
}
