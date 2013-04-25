package com.zzj.learn;

public class EnumLearn {
	public static void main(String[] args) {
		seasonEnum season = seasonEnum.spring;
		System.out.println(season);
	}
}

enum seasonEnum {
	spring, summer, autumn, winter
}

enum Season3 {
	SPRING("����"), SUMMER("����"), AUTUMN("����"), WINTER("����");

	private String name;

	private Season3(String name) {
		this.name = name;
	}

	public double getAvgTemp() {
		switch (this) {
		case SPRING:
			return 10.2;
		case SUMMER:
			return 25.8;
		case AUTUMN:
			return 19.6;
		case WINTER:
			return -3.6;
		}
		return 0.0;
	}

	public String getName() {
		return name;
	}
}

class Test {
	public static void main(String[] args) {
		m3(Season3.SPRING);
	}

	public static void m3(Season3 season) {
		System.out.println(season.getName() + ":" + season.getAvgTemp());
		Season3[] allSeason = Season3.values();// ����ö����������ö��ֵ��һ������
		System.out.println(allSeason[0]);
	}
}