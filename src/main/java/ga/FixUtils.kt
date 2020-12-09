package ga

import data.entity.bets.SingleBet
import ga.entity.Coupon
import ga.entity.CouponsGroup
import org.apache.commons.math3.analysis.function.Sin
import kotlin.random.Random

class FixUtils {

    companion object {

        fun handleSupportedBets(couponsGroup: CouponsGroup) {
            var supported = mutableListOf<SingleBet>()
            couponsGroup.coupons.forEach {coupon ->
                var supportedOnSingleCoupon = mutableListOf<SingleBet>()
                coupon.bets.forEach { bet ->
                    val jumper1Bets = coupon.bets.filter { it.name1 == bet.name1 || it.name2 == bet.name2 }
                    if (jumper1Bets.size > 1) {
                        supportedOnSingleCoupon.addAll(jumper1Bets.takeLast(jumper1Bets.size - 1))
                    }
                }
                supportedOnSingleCoupon = supportedOnSingleCoupon.distinctBy { it.id } as MutableList<SingleBet>
                supported.addAll(supportedOnSingleCoupon)
            }
            supported = supported.map { it.copy() } as MutableList<SingleBet>
            // sprobowac dodac do istenijacych kuponow zaklady z supoprted tak zeby sie nie wspieraly i zeby rozmiar nie przekroczyl rozmiaru max z main
            // albo wygenerowac nowe kupony z supported tak zeby sie nie wspierdaly i dodac do istniejaccyh
            // ale chodzi o to zeby manipulwoac rozmiarem kuponow
        }

        fun removeCouponsRepeats(couponsGroup: CouponsGroup) {
            val toDelete = mutableListOf<Coupon>()
            for (i in 0 until couponsGroup.coupons.size - 1) {
                for (j in i + 1 until couponsGroup.coupons.size) {
                    if (couponsGroup.coupons[i].areBetsTheSame(couponsGroup.coupons[j]))
                        toDelete.add(couponsGroup.coupons[j])
                }
            }
            toDelete.forEach {
                val x = 2
                couponsGroup.coupons.remove(it)
            }
        }


        fun removeBetsRepeats(couponsGroup: CouponsGroup) {
            var couponsFlatten = mutableListOf<Pair<Int, SingleBet>>()
            for (i in couponsGroup.coupons.indices) {
                couponsGroup.coupons[i].bets.forEach { bet ->
                    couponsFlatten.add(Pair(i, bet))
                }
            }

            couponsFlatten = couponsFlatten.distinctBy { it.second.id } as MutableList<Pair<Int, SingleBet>>
            val groupedPairs = couponsFlatten.groupBy { it.first }
            val newCoupons = mutableListOf<Coupon>()
            groupedPairs.forEach {
                val coupon = Coupon()
                it.value.forEach {
                    coupon.bets.add(it.second)
                }
                newCoupons.add(coupon)
            }

            couponsGroup.coupons = newCoupons
            couponsGroup.coupons = couponsGroup.coupons.filter { it.bets.size > 0 } as MutableList<Coupon>
        }

        fun addRemainingAvailableBets(couponsGroup: CouponsGroup) {
            val availableBets = Main.AVAILABLE_BETS.toMutableList()
            var alreadyOnCoupons = mutableListOf<Int>()
            couponsGroup.coupons.forEach {
                it.bets.forEach { bet ->
                    alreadyOnCoupons.add(bet.id)
                }
            }

            alreadyOnCoupons = alreadyOnCoupons.distinct() as MutableList<Int>
            alreadyOnCoupons.forEach { betId ->
                val indexToRemove = availableBets.indexOf(availableBets.first { it.id == betId })
                availableBets.removeAt(indexToRemove)
            }
            generateCouponsFromBetsAndAddToGroup(couponsGroup, availableBets)
        }

        fun generateCouponsFromBetsAndAddToGroup(couponsGroup: CouponsGroup, availableBets: MutableList<SingleBet>) {
            if (availableBets.size > 0) {
                val coupons = mutableListOf<Coupon>()
                while (availableBets.isNotEmpty()) {
                    var couponSize = Random.nextInt(Main.MIN_COUPON_SIZE, Main.MAX_COUPON_SIZE)
                    var foundProperSize = false
                    while (!foundProperSize) {
                        if (couponSize <= availableBets.size)
                            foundProperSize = true
                        else {
                            couponSize--
                        }
                    }
                    val coupon = Coupon()
                    for (i in 0 until couponSize) {
                        val randomIndex = if (availableBets.size == 1) 0 else Random.nextInt(0, availableBets.size - 1)
                        val bet = availableBets[randomIndex]
                        coupon.bets.add(bet.copy())
                        availableBets.remove(bet)
                    }
                    coupons.add(coupon)
                }
                couponsGroup.coupons.addAll(coupons)
            }
        }
    }
}