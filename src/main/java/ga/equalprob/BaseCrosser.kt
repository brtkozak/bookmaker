package ga.equalprob

import data.entity.bets.SingleBet
import ga.PopulationCrosser
import ga.entity.Coupon
import ga.entity.CouponsGroup
import kotlin.random.Random

class BaseCrosser : PopulationCrosser() {


    override fun cross(parents: Pair<CouponsGroup, CouponsGroup>): Pair<CouponsGroup, CouponsGroup> {
        if (parents.first.coupons.size > 1 && parents.second.coupons.size > 1) {

            val firstParentSplitPoint = Random.nextInt(parents.first.coupons.size - 1)
            val secondParentSplitPoint = Random.nextInt(parents.second.coupons.size - 1)

            val firstParentCoupons = parents.first.coupons.toMutableList()
            val secondParentCoupons = parents.second.coupons.toMutableList()

            val firstChild = CouponsGroup()
            firstChild.coupons.addAll(firstParentCoupons.take(firstParentSplitPoint))
            firstChild.coupons.addAll(secondParentCoupons.takeLast(secondParentCoupons.size - secondParentSplitPoint))

            val secondChild = CouponsGroup()
            secondChild.coupons.addAll(firstParentCoupons.takeLast(firstParentCoupons.size - firstParentSplitPoint))
            secondChild.coupons.addAll(secondParentCoupons.take(secondParentSplitPoint))

            fixChild(firstChild)
            fixChild(secondChild)

            val f1 = firstChild.coupons.sumBy { it.bets.size }
            val f2 = secondChild.coupons.sumBy { it.bets.size }

            if (f1 != f2) {
                val availablebets = Main.AVAILABLE_BETS.size
                val x = 2
            }

            return Pair(firstChild, secondChild)

        } else return Pair(parents.first, parents.second)
    }

    private fun fixChild(child: CouponsGroup) {
        val availableBets = Main.AVAILABLE_BETS.toMutableList()
        val alreadyOnCoupons = mutableListOf<Int>()
        val toDelete = mutableMapOf<Int, MutableList<Int>>()

        var couponsFlatten = mutableListOf<Pair<Int, SingleBet>>()
        for(i in child.coupons.indices) {
            child.coupons[i].bets.forEach { bet ->
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
                alreadyOnCoupons.add(it.second.id)
            }
            newCoupons.add(coupon)
        }

        child.coupons = newCoupons

        child.coupons = child.coupons.filter { it.bets.size > 0 } as MutableList<Coupon>

        alreadyOnCoupons.forEach { betId ->
            val indexToRemove = availableBets.indexOf(availableBets.first { it.id == betId })
            availableBets.removeAt(indexToRemove)
        }

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
                    coupon.bets.add(bet)
                    availableBets.remove(bet)
                }
                coupons.add(coupon)
            }
            child.coupons.addAll(coupons)
        }

        val all = Main.AVAILABLE_BETS.size
        val cop = child.coupons.sumBy { it.bets.size }
        if (all != cop) {
            val z = 2
        }
    }
}
