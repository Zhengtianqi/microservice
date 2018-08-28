package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *  加入hystrix ,当服务全部挂掉时,提供一个默认方法
 */
@Component
public class HystrixClientFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFactory.class);

    @Override
    public UserFeignClient create(Throwable cause) {
        HystrixClientFactory.LOGGER.info("fallback; reason was: {}" , cause.getMessage());
        return new UserFeignClientWithClient() {
            @Override
            public User findById(Long id) {
                User user = new User();
                user.setId(-1L); // 让fallbackfactory区分fallback
                return user;
            }
        };
    }
}
