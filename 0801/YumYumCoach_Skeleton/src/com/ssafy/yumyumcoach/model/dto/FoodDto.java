package com.ssafy.yumyumcoach.model.dto;

import java.util.Objects;

/**
 * <h2>FoodDto</h2>
 * <p>식단에 포함된 단일 음식 정보를 담는 DTO입니다.</p>
 *
 * <ul>
 *   <li><b>code</b>     : 식품코드 (예 D101-004160000-0001)</li>
 *   <li><b>name</b>     : 식품명</li>
 *   <li><b>energy</b>   : kcal</li>
 *   <li><b>carb</b>     : 탄수화물 g</li>
 *   <li><b>protein</b>  : 단백질 g</li>
 *   <li><b>fat</b>      : 지방 g</li>
 *   <li><b>weight</b>   : 중량 문자열(“200g”)</li>
 * </ul>
 *
 * <p>무결성을 위해 equals/hashCode 는 <b>code</b> 기준으로만 비교합니다.
 * 같은 코드면 동일 상품으로 간주합니다.</p>
 */
public class FoodDto {

    /* ──────────── Fields ──────────── */
    private String code;    // 식품코드
    private String name;    // 식품명
    private double energy;  // kcal
    private double carb;    // g
    private double protein; // g
    private double fat;     // g
    private String weight;  // “200g”

    /* ──────────── Getter / Setter ──────────── */

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getEnergy() {
        return energy;
    }
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getCarb() {
        return carb;
    }
    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getProtein() {
        return protein;
    }
    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }

    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /* ──────────── Convenience ──────────── */

    /**
     * <p>“200g” 과 같은 {@link #weight} 문자열을 정수 g 로 변환합니다.</p>
     * <p>숫자가 없거나 파싱 실패 시 0 반환.</p>
     */
    public int getWeightGram() {
        try {
            return Integer.parseInt(weight.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /* ──────────── Object Overrides ──────────── */

    @Override
    public String toString() {
        return "FoodDto{" +
               "code='" + code + '\'' +
               ", name='" + name + '\'' +
               ", energy=" + energy +
               ", carb=" + carb +
               ", protein=" + protein +
               ", fat=" + fat +
               ", weight='" + weight + '\'' +
               '}';
    }

    /** code 가 같으면 동일 음식으로 취급 */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodDto)) return false;
        FoodDto foodDto = (FoodDto) o;
        return Objects.equals(code, foodDto.code);
    }
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
