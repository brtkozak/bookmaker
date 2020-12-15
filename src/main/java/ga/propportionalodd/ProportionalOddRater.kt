package ga.propportionalodd

import ga.PopulationRater
import ga.entity.CouponsGroup
import kotlin.math.abs

class ProportionalOddRater : PopulationRater() {

    override fun rateCouponsGroup(couponsGroup: CouponsGroup) {
        couponsGroup.coupons.sortBy { it.getOdd() }
        val baseOdd = Main.BASE_ODD
        val step = Main.ODD_STEP
        var currentOdd = baseOdd
        var rate = 0.0
        couponsGroup.coupons.forEach {
            rate+= abs(it.getOdd() - currentOdd)
            currentOdd += step
        }
        couponsGroup.rate = rate
    }

}