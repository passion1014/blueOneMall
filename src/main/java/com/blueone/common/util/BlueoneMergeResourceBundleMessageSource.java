package com.blueone.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class BlueoneMergeResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {
	
    @Override
    public void setBasenames(String... basenames) {
        CollectionUtils.reverseArray(basenames);
        super.setBasenames(basenames);
    }
}
