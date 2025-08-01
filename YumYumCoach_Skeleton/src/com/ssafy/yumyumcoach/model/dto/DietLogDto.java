package com.ssafy.yumyumcoach.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <h2>DietLogDto</h2>
 * <p>단일 식단 로그(한 끼) 정보를 담는 DTO.</p>
 * <ul>
 *   <li><b>dietLogId</b> : 식단 로그 PK (1 ~ N)</li>
 *   <li><b>date</b>      : YYYY-MM-DD</li>
 *   <li><b>mealType</b>  : 아침 / 점심 / 저녁 / 간식</li>
 *   <li><b>foods</b>     : 한 끼에 포함된 음식 리스트</li>
 * </ul>
 *
 * <p>getter 는 내부 리스트의 불변 뷰를 반환하여 외부에서 직접
 * {@code add/clear} 로 수정하지 못하도록 보호합니다.
 * 음식 추가는 <b>{@link #addFood(FoodDto)}</b> 메서드로만 수행하세요.</p>
 */
public class DietLogDto {

    /** 식단 로그 ID (PK) */
    private int dietLogId;

    /** 식단 날짜 (YYYY-MM-DD) */
    private String date;

    /** 식사 구분 (아침 / 점심 / 저녁 / 간식) */
    private String mealType;

    /** 포함 음식 리스트 (mutable) */
    private final List<FoodDto> foods = new ArrayList<>();

    /* ──────────────────── Getter / Setter ──────────────────── */

    public int getDietLogId() {
        return dietLogId;
    }
    public void setDietLogId(int dietLogId) {
        this.dietLogId = dietLogId;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    /**
     * 음식 리스트의 <b>읽기 전용 뷰</b>를 반환합니다.
     * 외부 코드가 직접 add/remove 하지 못하도록 불변 리스트로 감쌉니다.
     */
    public List<FoodDto> getFoods() {
        return Collections.unmodifiableList(foods);
    }

    /**
     * 한 끼에 포함된 음식을 추가합니다.
     *
     * @param food {@link FoodDto} 객체
     * @throws NullPointerException food == null
     */
    public void addFood(FoodDto food) {
        Objects.requireNonNull(food, "food must not be null");
        this.foods.add(food);
    }

    /* ──────────────────── Object Overrides ──────────────────── */

    @Override
    public String toString() {
        return "DietLogDto{" +
               "dietLogId=" + dietLogId +
               ", date='" + date + '\'' +
               ", mealType='" + mealType + '\'' +
               ", foods=" + foods +
               '}';
    }

    /** ID가 같으면 동일 객체로 간주 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DietLogDto)) return false;
        DietLogDto that = (DietLogDto) o;
        return dietLogId == that.dietLogId;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(dietLogId);
    }
}
