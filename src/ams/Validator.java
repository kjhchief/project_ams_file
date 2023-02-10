package ams;

/**
 * AMS 프로젝트에서 공통적으로 사용하는 유효성 검증 유틸리티 클래스
 * @Author 김재훈
 * @Date 2023. 1. 26.
 */
public class Validator {
	
	public static boolean isEmpty(String text) {
		return text == null || text.trim().length() == 0;
	}

}
