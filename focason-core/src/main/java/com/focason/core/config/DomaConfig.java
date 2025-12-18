// =====================================================
// Copyright 2025 Focason Co.,Ltd. AllRights Reserved.
// =====================================================
package com.focason.core.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.springframework.context.annotation.Configuration;

/**
 * Global Doma configuration.
 * <p>
 * Provides the {@link Config} implementation required by Doma DAOs.
 * This configuration is shared across modules that use the same database.
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class DomaConfig implements Config
{

    private final DataSource dataSource;

    private final Dialect dialect = new MysqlDialect();

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }
}
