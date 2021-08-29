package com.alkbackend.util;

import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import com.alkbackend.entity.MovieQualif;


public class MovieQualificationUtil {

	public Map<Integer, Integer> utilListToMap(List<MovieQualif> list) {

		Map<Integer, Integer> qual = list.stream()
				.collect(Collectors.toMap(MovieQualif::getQualification, MovieQualif::getNumberOfVote));

		return qual;
	}

	public float promedio(Map<Integer, Integer> map) {
		float xfMult = 0;
		int keySum = 0;
		int fSum = 0;
		float prom = 0.f;

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			xfMult = (entry.getKey() * entry.getValue()) + xfMult;
			fSum = entry.getValue() + fSum;
			keySum = entry.getKey() + keySum;
			prom = xfMult / fSum;
		}

		return prom;
	}

}
