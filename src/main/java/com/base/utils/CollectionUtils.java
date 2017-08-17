package com.base.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * 功能描述: 集合工具类 
 */
public final class CollectionUtils {
	/**
	 * 
	 * 在集合中去掉空对象
	 */
	public static Collection clearNullElement(Collection collection) {
		if (collection != null) {
			Iterator it = collection.iterator();
			while (it.hasNext()) {
				if (it.next() == null)
					it.remove();
			}
		}
		return collection;
	}

	/**
	 * 
	 * 在集合中去掉指定对象
	 */
	public static Collection clearElement(Collection collection, Object element) {
		if (collection != null && element != null) {
			Iterator it = collection.iterator();
			while (it.hasNext()) {
				if (it.next().equals(element))
					it.remove();
			}
		}
		return collection;
	}
}
