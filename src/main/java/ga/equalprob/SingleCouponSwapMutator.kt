package ga.equalprob

import data.entity.bets.SingleBet
import ga.PopulationMutator
import ga.entity.CouponsGroup
import kotlin.random.Random

class SingleCouponSwapMutator : PopulationMutator() {

    override fun mutate(couponsGroup: CouponsGroup) {
        val mutate = Random.nextDouble(1.0)
        if (mutate > Main.MUTATION_PROBABILITY)
            return
        if(couponsGroup.coupons.size < 2)
            return

        var found = false
        var iteration = 0
        while (!found || iteration < 3) {
            val firstCouponIndex = Random.nextInt(couponsGroup.coupons.size - 1)
            val secondCouponIndex = Random.nextInt(couponsGroup.coupons.size - 1)
            val firstCoupon = couponsGroup.coupons[firstCouponIndex]
            val secondCoupon = couponsGroup.coupons[secondCouponIndex]
            if (firstCoupon != secondCoupon &&
                    ((firstCoupon.bets.size > 1 && secondCoupon.bets.size > 0) ||
                            (firstCoupon.bets.size > 0 && secondCoupon.bets.size > 1))) {

                var firstBet: SingleBet? = null
                var secondBet: SingleBet? = null
                if (firstCoupon.bets.size == 1) {
                    firstBet = firstCoupon.bets[0]
                } else if (secondCoupon.bets.size == 1) {
                    secondBet = secondCoupon.bets[0]
                }
                if (firstBet == null) {
                    firstBet = firstCoupon.bets[Random.nextInt(firstCoupon.bets.size - 1)]
                }
                if (secondBet == null) {
                    secondBet = secondCoupon.bets[Random.nextInt(secondCoupon.bets.size - 1)]
                }
                found = true

                firstCoupon.bets.remove(firstBet)
                secondCoupon.bets.remove(secondBet)

                firstBet?.let {
                    secondCoupon.bets.add(it)
                }
                secondBet?.let {
                    firstCoupon.bets.add(it)

                }
            }
            iteration ++
        }
    }

}