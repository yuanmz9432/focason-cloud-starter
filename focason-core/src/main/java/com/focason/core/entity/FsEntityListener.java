/*
 * Copyright 2023 Focason Co.,Ltd. AllRights Reserved.
 */
package com.focason.core.entity;



import com.focason.core.domain.Switch;
import java.time.LocalDateTime;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.slf4j.MDC;

public class FsEntityListener<E extends FsEntity> implements EntityListener<E>
{
    public FsEntityListener() {}

    @Override
    public void preInsert(E entity, PreInsertContext<E> context) {
        entity.setCreatedBy(MDC.get("EMAIL") == null ? "ADMIN" : MDC.get("EMAIL"));
        entity.setModifiedBy(MDC.get("EMAIL") == null ? "ADMIN" : MDC.get("EMAIL"));
        entity.setCreatedAt(LocalDateTime.now());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setIsDeleted(Switch.OFF.getValue());
    }

    @Override
    public void preUpdate(E entity, PreUpdateContext<E> context) {
        entity.setModifiedBy(MDC.get("EMAIL") == null ? "ADMIN" : MDC.get("EMAIL"));
        entity.setModifiedAt(LocalDateTime.now());
    }
}
