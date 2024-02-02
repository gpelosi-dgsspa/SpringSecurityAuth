package com.cex.application.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectableMap<K, V> extends HashMap<K, V> 
{
	private static final long serialVersionUID = 1L;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public CollectableMap() {
		super();
	}

	public CollectableMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public CollectableMap(int initialCapacity) {
		super(initialCapacity);
	}

	public CollectableMap(Map<? extends K, ? extends V> m) {
		super(m);
	}
	
	@SuppressWarnings("unchecked")
	public void collect(K key, V value) 
	{
		if(value instanceof Number) 
		{
			if(value.getClass().equals(Integer.class)) 
			{
				log.info("Integer");
				Integer v = (Integer)value;
				int valoreFinale = v.intValue();
				if(this.containsKey(key)) 
				{
					Integer val = (Integer)this.get(key);
					valoreFinale = val.intValue() + v.intValue();
				}
				this.put(key, (V)Integer.valueOf(valoreFinale));
			}
			
			if(value.getClass().equals(Long.class)) 
			{
				log.info("Long");
				Long v = (Long)value;
				long valoreFinale = v.longValue();
				if(this.containsKey(key)) 
				{
					Long val = (Long)this.get(key);
					valoreFinale = val.longValue() + v.longValue();
				}
				this.put(key, (V)Long.valueOf(valoreFinale));
			}
			
			if(value.getClass().equals(BigInteger.class)) 
			{
				log.info("BigInteger");
				BigInteger valoreFinale = (BigInteger)value;
				if(this.containsKey(key)) 
				{
					BigInteger val = (BigInteger)this.get(key);
					valoreFinale = valoreFinale.add(val);
				}
				this.put(key, (V)valoreFinale);
			}
			
			if(value.getClass().equals(BigDecimal.class)) 
			{
				log.info("BigDecimal");
				BigDecimal valoreFinale = (BigDecimal)value;
				if(this.containsKey(key)) 
				{
					BigDecimal val = (BigDecimal)this.get(key);
					valoreFinale = valoreFinale.add(val);
				}
				this.put(key, (V)valoreFinale);
			}
			
			if(value.getClass().equals(Float.class)) 
			{
				log.info("Float");
				float valoreFinale = ((Float)value).floatValue();
				if(this.containsKey(key)) 
				{
					Float val = (Float)this.get(key);
					valoreFinale = valoreFinale + val.floatValue();
				}
				this.put(key, (V)Float.valueOf(valoreFinale));
			}
			
			if(value.getClass().equals(Double.class)) 
			{
				log.info("Double");
				Double v = (Double)value;
				double valoreFinale = v.doubleValue();
				if(this.containsKey(key)) 
				{
					Double val = (Double)this.get(key);
					valoreFinale = val.doubleValue() + v.doubleValue();
				}
				this.put(key, (V)Double.valueOf(valoreFinale));
			}
			
			if(value.getClass().equals(Short.class)) 
			{
				log.info("Short");
				Short v = (Short)value;
				short valoreFinale = v.shortValue();
				if(this.containsKey(key)) 
				{
					Short val = (Short)this.get(key);
					valoreFinale = (short) (val.shortValue() + v.shortValue());
				}
				this.put(key, (V)Short.valueOf(valoreFinale));
			}
			
			if(value.getClass().equals(Byte.class)) 
			{
				log.info("Byte");
				Byte v = (Byte)value;
				byte valoreFinale = v.byteValue();
				if(this.containsKey(key)) 
				{
					Byte val = (Byte)this.get(key);
					valoreFinale = (byte) (val.byteValue() + v.byteValue());
				}
				this.put(key, (V)Byte.valueOf(valoreFinale));
			}
		} 
		else 
		{
			this.put(key, value);
		}
	}
}
