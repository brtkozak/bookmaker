package ga

import data.entity.bets.SingleBet
import ga.entity.Coupon
import ga.entity.CouponsGroup
import kotlin.random.Random

class BasePopulationInitializer : PopulationInitializer {

    override fun initPopulation(populationSize: Int, availableBets: List<SingleBet>): List<CouponsGroup> {
        val result = mutableListOf<CouponsGroup>()
        for (i in 0 until populationSize) {
            result.add(getGroup(availableBets))
        }
        result.forEach {
            FixUtils.handleSupportedBets(it)
        }
        return result
    }

    private fun getGroup(availableBets: List<SingleBet>): CouponsGroup {
        val bets = availableBets.toMutableList()
        val couponsGroup = CouponsGroup()
        while (bets.isNotEmpty()) {
            var couponSize = Random.nextInt(Main.MIN_COUPON_SIZE, Main.MAX_COUPON_SIZE)
            var foundProperSize = false
            while (!foundProperSize) {
                if (couponSize <= bets.size)
                    foundProperSize = true
                else {
                    couponSize--
                }
            }
            val coupon = Coupon()
            for (i in 0 until couponSize) {
                val randomIndex = if (bets.size == 1) 0 else Random.nextInt(0, bets.size - 1)
                val bet = bets[randomIndex]
                coupon.bets.add(bet.copy())
                bets.remove(bet)
            }
            couponsGroup.coupons.add(coupon)
        }
        return couponsGroup
    }
}