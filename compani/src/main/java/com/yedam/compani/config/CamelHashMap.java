package com.yedam.compani.config;

import java.util.HashMap;


import com.google.common.base.CaseFormat;

@SuppressWarnings({ "serial", "rawtypes" })
public class CamelHashMap extends HashMap {
	
	@SuppressWarnings("unchecked")
	@Override
    public Object put(Object key, Object value) {
        return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,(String) key), value);
    }
}
