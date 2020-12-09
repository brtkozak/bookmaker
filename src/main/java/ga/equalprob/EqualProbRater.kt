package ga.equalprob

import ga.PopulationRater
import ga.entity.CouponsGroup
import org.apache.commons.math3.stat.descriptive.SummaryStatistics
import kotlin.math.abs

class EqualProbRater(val probMean: Double) : PopulationRater() {

    override fun rateCouponsGroup(couponsGroup: CouponsGroup) {
        val stats = SummaryStatistics()
        couponsGroup.coupons.forEach {
            stats.addValue(it.getProb())
        }
        val mean = stats.mean
        val value = abs(probMean - mean) + (stats.max - stats.min)
        couponsGroup.rate = value
    }

}