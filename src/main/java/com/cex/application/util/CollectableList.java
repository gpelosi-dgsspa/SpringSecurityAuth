package com.cex.application.util;

import java.util.ArrayList;
import java.util.Collection;

public class CollectableList<T> extends ArrayList<T>
{
	private static final long serialVersionUID = 1L;

	public CollectableList() {
		super();
	}

	public CollectableList(Collection<? extends T> c) {
		super(c);
	}

	public CollectableList(int initialCapacity) {
		super(initialCapacity);
	}
	
	public boolean collect(T e) 
	{
		boolean added = false;
		if(!this.contains(e)) {
			this.add(e);
			added = true;
		}
		return added;
	}
}
