package org.self.cache.guava;

import java.util.concurrent.ExecutionException;

import org.self.cache.Key;
import org.self.cache.Point;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 *
 * @author zhuwenbing
 * @date 2017年3月13日
 * @version 1.0
 */

public class GuavaCacheLoader {
	private LoadingCache<Key, Point> points = CacheBuilder.newBuilder().maximumSize(1000)
			.build(new CacheLoader<Key, Point>() {
				@Override
				public Point load(Key key) throws Exception {
					return null;
				}
			});

	public Point get(Key key) throws ExecutionException {
		return points.get(key);
	}

	public void put(Key key, Point point) {
		points.put(key, point);
	}

}
