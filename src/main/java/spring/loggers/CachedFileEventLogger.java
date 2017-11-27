package spring.loggers;

import spring.events.AbstractEvent;
import spring.events.Event;

import java.util.List;

public class CachedFileEventLogger extends FileEventLogger {
	private int cacheSize;
	private List<AbstractEvent> cache;

	public CachedFileEventLogger(String filename, int cacheSize) {
		super(filename);
		this.cacheSize = cacheSize;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public List<AbstractEvent> getCache() {
		return cache;
	}

	public void setCache(List<AbstractEvent> cache) {
		this.cache = cache;
	}

	@Override
	public void logEvent(AbstractEvent event) {
		cache.add(event);
		if (cache.size() == cacheSize) {
			flushCache();
		}
	}

	private void flushCache() {
		for (AbstractEvent event : cache) {
			super.logEvent(event);
		}
		cache.clear();
	}

	private void destroy(){
		flushCache();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CachedFileEventLogger that = (CachedFileEventLogger) o;

		if (cacheSize != that.cacheSize) return false;
		return cache != null ? cache.equals(that.cache) : that.cache == null;
	}

	@Override
	public int hashCode() {
		int result = cacheSize;
		result = 31 * result + (cache != null ? cache.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "CachedFileEventLogger{" +
				"cacheSize=" + cacheSize +
				", cache=" + cache +
				'}';
	}
}
