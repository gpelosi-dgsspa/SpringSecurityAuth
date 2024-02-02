package com.cex.application.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class EntityUtil 
{
	/**
	 * Converte una lista di oggetti nella corrispondente lista di oggetti di destinazione
	 */
	public static <T> List<T> mapList(ModelMapper modelMapper, Class<T> destinationClass, Object[] objList) {
		List<T> destObjectList = null;
		if(objList!=null && objList.length>0) {
			destObjectList = new ArrayList<T>();
			for(Object o: objList) {
				destObjectList.add(modelMapper.map(o, destinationClass));
			}
		}
		return destObjectList;
	}
}
