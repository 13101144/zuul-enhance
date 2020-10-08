package com.github.zuul.enhance.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Iterator;

public class IpUtils {
    private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

    private IpUtils() {
    }

    public static boolean match(Collection<String> ruleList, String ip) {
        if (CollectionUtils.isEmpty(ruleList)) {
            return false;
        } else {
            Iterator var2 = ruleList.iterator();

            String rule;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                rule = (String)var2.next();
            } while(!match(rule, ip));

            return true;
        }
    }

    public static boolean match(String rule, String ip) {
        if (!StringUtils.hasText(rule)) {
            return false;
        } else {
            String[] ruleSegments = rule.split("\\.");
            if (ruleSegments.length != 4) {
                logger.error("Non-compliant IP rules : {}", rule);
                return false;
            } else {
                String[] ipSegments = ip.split("\\.");
                if (ipSegments.length != 4) {
                    logger.error("Non-compliant IP : {}", ip);
                    return false;
                } else {
                    boolean match = true;

                    for(int i = 0; i < 4; ++i) {
                        String ruleSegment = ruleSegments[i];
                        int ipSegment = Integer.parseInt(ipSegments[i]);
                        match &= match(ruleSegment, ipSegment);
                    }

                    return match;
                }
            }
        }
    }

    public static boolean match(String ruleSegment, int ipSegment) {
        if (!StringUtils.hasText(ruleSegment)) {
            return false;
        } else {
            String[] ruleItems = ruleSegment.split(",");
            String[] var3 = ruleItems;
            int var4 = ruleItems.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String ruleItem = var3[var5];
                if ("*".equals(ruleItem)) {
                    return true;
                }

                if (ruleItem.contains("-")) {
                    String[] ruleIps = ruleItem.split("-");
                    int start = Integer.parseInt(ruleIps[0]);
                    int end = Integer.parseInt(ruleIps[ruleIps.length - 1]);
                    if (start <= ipSegment && ipSegment <= end) {
                        return true;
                    }
                } else {
                    int target = Integer.parseInt(ruleItem);
                    if (target == ipSegment) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
