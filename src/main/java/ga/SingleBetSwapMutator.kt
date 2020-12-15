package ga

import data.entity.bets.SingleBet
import ga.entity.CouponsGroup
import kotlin.random.Random

class SingleBetSwapMutator : PopulationMutator() {

    override fun mutate(couponsGroup: CouponsGroup) {
        if(couponsGroup.coupons.size < 2 )
            return

        var found = false
        var iteration = 0
        while (!found && iteration < 3) {
            val firstCouponIndex = Random.nextInt(couponsGroup.coupons.size - 1)
            val secondCouponIndex = Random.nextInt(couponsGroup.coupons.size - 1)
            val firstCoupon = couponsGroup.coupons[firstCouponIndex]
            val secondCoupon = couponsGroup.coupons[secondCouponIndex]
            if (firstCoupon != secondCoupon) {
                found = true
                var firstBet: SingleBet? = null
                if (firstCoupon.bets.size == 1) {
                    firstBet = firstCoupon.bets[0]
                    couponsGroup.coupons.removeAt(firstCouponIndex)
                }
                else if (firstBet == null) {
                    firstBet = firstCoupon.bets[Random.nextInt(firstCoupon.bets.size)]
                    firstCoupon.bets.remove(firstBet)
                }

                firstBet.let {
                    secondCoupon.bets.add(it)
                }
            }
            iteration ++
        }
    }
}