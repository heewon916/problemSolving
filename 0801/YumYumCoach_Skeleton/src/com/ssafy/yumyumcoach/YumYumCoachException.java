package com.ssafy.yumyumcoach;

/**
 * <h2>YumYumCoachException</h2>
 * <p>애플리케이션 전반에서 발생할 수 있는
 *   <b>도메인·비즈니스 예외</b>를 표현하는 커스텀 예외 클래스입니다.</p>
 *
 * <ul>
 *   <li>기본 생성자 ‒ 공통 메시지(“데이터를 처리 중 오류 발생”) 사용</li>
 *   <li>메시지 생성자 ‒ 호출 측에서 구체적 에러 설명 전달</li>
 *   <li><code>serialVersionUID</code> ‒ 직렬화 버전 관리</li>
 * </ul>
 *
 * <p>사용 예시</p>
 * <pre>
 * if (dietLog == null) {
 *     throw new YumYumCoachException("식단 로그를 찾을 수 없습니다.");
 * }
 * </pre>
 */
public class YumYumCoachException extends Exception {

    /** 직렬화 호환 버전 UID */
    private static final long serialVersionUID = 1L;

    /** 공통 메시지를 사용하는 기본 생성자 */
    public YumYumCoachException() {
        super("데이터를 처리 중 오류 발생");
    }

    /**
     * 호출 측에서 상세 메시지를 지정하는 생성자
     * @param msg  예외 상세 설명
     */
    public YumYumCoachException(String msg) {
        super(msg);
    }
}
