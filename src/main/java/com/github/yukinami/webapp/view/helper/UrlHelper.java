package com.github.yukinami.webapp.view.helper;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author snowblink on 16/5/25.
 */
public final class UrlHelper {

    /**
     * test if target mapping name is match current requested url
     *
     * @param mappingName
     * @param uriVariables
     * @return
     */
    public final boolean isCurrentController(String mappingName, String... uriVariables) {
        String targetUrl = MvcUriComponentsBuilder.fromMappingName(mappingName)
                .buildAndExpand((Object[]) uriVariables);

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        return request.getRequestURL().toString().equals(targetUrl);
    }

    /**
     * test if current path match the target path
     *
     * @param pattern path pattern of ant format
     * @return {@code true} if matches
     */
    public final boolean isCurrentPathMatchAnt(String pattern) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        RequestMatcher requestMatcher = new AntPathRequestMatcher(pattern);
        return requestMatcher.matches(request);

    }

    /**
     * test if current path match the target path
     *
     * @param pattern path pattern of regex format
     * @return {@code true} if matches
     */
    public final boolean isCurrentPathMatchRegex(String pattern) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        RequestMatcher requestMatcher = new RegexRequestMatcher(pattern, null);
        return requestMatcher.matches(request);

    }

}
