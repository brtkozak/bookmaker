package ga

import data.entity.bets.SingleBet
import ga.entity.Coupon
import ga.entity.CouponsGroup
import org.apache.commons.math3.analysis.function.Sin
import kotlin.random.Random

class FixUtils {

    companion object {

        fun handleSupportedBets(couponsGroup: CouponsGroup) {
            val supportedMap = mutableMapOf<Int, List<SingleBet>>()
            couponsGroup.coupons.forEach { coupon ->
                var supportedOnSingleCoupon = mutableListOf<SingleBet>()
                coupon.bets.forEach { bet ->
                    val jumper1Bets = coupon.bets.filter { it.name1 == bet.name1 || it.name2 == bet.name2 || it.name1 == bet.name2 || it.name2 == bet.name1}
                    if (jumper1Bets.size > 1) {
                        supportedOnSingleCoupon.addAll(jumper1Bets.takeLast(jumper1Bets.size - 1))
                    }
                }
                supportedOnSingleCoupon = supportedOnSingleCoupon.distinctBy { it.id } as MutableList<SingleBet>
                supportedOnSingleCoupon = supportedOnSingleCoupon.map { it.copy() } as MutableList<SingleBet>
                supportedMap[couponsGroup.coupons.indexOf(coupon)] =  supportedOnSingleCoupon
            }

            // remove supported from every coupon
            supportedMap.forEach { k, v ->
                val ids = v.map { it.id }
                couponsGroup.coupons[k].bets.removeAll { bet -> ids.contains(bet.id) }
            }

            // change map to flatten list
            val supported = supportedMap.values.toList().flatten()

            // idea jest taka, ze probujesz dodac kazdy z zakladow z supported do losowo wybranego kuponu i sprawdzasz czy nie bedzie go wspieral, badz cz
            // nie przekroczy kupon rozmiaru max z main, jeśli nie spełni tych warunkow to dodajesz ten zaklad do drugiej listy z ktorej na koniec generujesz kupony nie wspierajace sie

            val rest = mutableListOf<SingleBet>()
            supported.forEach {
                val randomCoupon = couponsGroup.coupons[Random.nextInt(couponsGroup.coupons.size)]
                if( !randomCoupon.doesSupport(it) && randomCoupon.bets.size < Main.MAX_COUPON_SIZE) {
                    randomCoupon.bets.add(it)
                } else {
                    rest.add(it)
                }
            }

            if (rest.size > 0) {
                val couponsFromRest = mutableListOf<Coupon>()
                couponsFromRest.add(Coupon(mutableListOf(rest[0])))
                rest.removeAt(0)
                rest.forEach { bet ->
                    var added = false
                    couponsFromRest.forEach { coupon ->
                        if (!coupon.doesSupport(bet)) {
                            coupon.bets.add(bet)
                            added = true
                        }
                    }
                    if (!added)
                        couponsFromRest.add(Coupon(mutableListOf(bet)))
                }
                couponsGroup.coupons.addAll(couponsFromRest)
            }
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

            if(alreadyOnCoupons.isNotEmpty())
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