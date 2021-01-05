package ga.entity

import data.entity.bets.BetResult

data class CouponsGroup(
        var coupons: MutableList<Coupon> = mutableListOf(),
        var rate: Double = 0.0
) {
    fun copy() : CouponsGroup {
        val copy = CouponsGroup()
        this.coupons.forEach {
            copy.coupons.add(it.copy())
        }
        copy.rate = this.rate
        return copy
    }

    fun getContribution() : Double {
        return coupons.sumByDouble { it.contribution }
    }

    fun getGain(): Double {
        var gain = 0.0
        coupons.forEach { coupon ->
            if (!coupon.bets.any { bet -> bet.betResult == BetResult.Lose }) {
                gain += coupon.getWinCashAfterResults()
            }
        }
        gain -= getContribution()
        if(gain < -1000.0) {
            val x = 2
        }
        return gain
    }
}