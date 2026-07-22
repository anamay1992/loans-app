package com.namay.loan.infrastructure.adapter.output.db.converter;

import com.namay.loan.domain.model.constant.ValuedEnum;
import jakarta.persistence.AttributeConverter;

public abstract class AbstractEnumConverter<E extends Enum<E> & ValuedEnum> implements AttributeConverter<E, Integer> {

    private final Class<E> enumClass;

    protected AbstractEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        return attribute != null ? attribute.getStatus() : null;
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        for (E constant : enumClass.getEnumConstants()) {
            if (constant.getStatus() == dbData) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Valor numérico no válido: " + dbData + " para el enum " + enumClass.getSimpleName());
    }
}
