package com.jnutrition.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PlanItemUtils {
    public static BigDecimal calculateFat(PlanItem item) {
        return item.getIngredient().getFat().multiply(amountFactor(item)).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateCarbs(PlanItem item) {
        return item.getIngredient().getCarbs().multiply(amountFactor(item)).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateProtein(PlanItem item) {
        return item.getIngredient().getProtein().multiply(amountFactor(item)).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateKCal(PlanItem item) {

        return item.getIngredient().getKcal().multiply(amountFactor(item)).setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal amountFactor(PlanItem item) {
        return new BigDecimal( (item.getAmount()/100d) * item.getUnit().inGram() );
    }
}
