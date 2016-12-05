package com.github.yukinami.webapp.view.thymeleaf;

import com.github.yukinami.webapp.view.helper.UrlHelper;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author snowblink on 16/5/25.
 */
public class ApplicationDialect extends AbstractDialect implements IExpressionObjectDialect {

    public ApplicationDialect() {
        super("app");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {

        Map<String, Object> expressionObjects = new HashMap<>();
        expressionObjects.put("urlHelper", new UrlHelper());

        return new IExpressionObjectFactory() {

            @Override
            public Set<String> getAllExpressionObjectNames() {
                return expressionObjects.keySet();
            }

            @Override
            public Object buildObject(IExpressionContext context, String expressionObjectName) {
                return expressionObjects.get(expressionObjectName);
            }

            @Override
            public boolean isCacheable(String expressionObjectName) {
                return true;
            }
        };
    }
}
