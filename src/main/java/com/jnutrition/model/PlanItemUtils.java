package com.jnutrition.model;

import java.math.BigDecimal;

public class PlanItemUtils {
    public static BigDecimal calculateFat(PlanItem item) {
        return item.getIngredient().getFat().multiply(amountFactor(item));
    }

    public static BigDecimal calculateCarbs(PlanItem item) {
        return item.getIngredient().getCarbs().multiply(amountFactor(item));
    }

    public static BigDecimal calculateProtein(PlanItem item) {
        return item.getIngredient().getProtein().multiply(amountFactor(item));
    }

    public static BigDecimal calculateKCal(PlanItem item) {

        return item.getIngredient().getKcal().multiply(amountFactor(item));
    }

    protected static BigDecimal amountFactor(PlanItem item) {
        return new BigDecimal( (item.getAmount()/100d) * item.getUnit().inGram() );
    }
}
