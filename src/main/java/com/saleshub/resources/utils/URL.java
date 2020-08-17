package com.saleshub.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String param) {
		
		try {
		return URLDecoder.decode(param,"UTF-8");
		
		} catch(UnsupportedEncodingException ude) {
			return "";
		}
	}
	
	public static List<Integer> decodeIntList(String uri){
		
		String[] diggestedURI = uri.split(",");
		
		List<Integer> integerArgs = new ArrayList<>();
		
		for(String character : diggestedURI) {
			integerArgs.add(Integer.parseInt(character));
		}
		
		return integerArgs;
	}
	
}
