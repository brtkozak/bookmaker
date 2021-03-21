package utils

import ga.entity.CouponsGroup

abstract class StackStrategy(startBankRoll : Double) {

    var currentBankroll = startBankRoll

    val bankrollStates = mutableListOf(startBankRoll)

    abstract fun modifyContribution (couponsGroup: CouponsGroup, updateBankroll: Boolean = false) : Boolean

    fun updateBankroll(couponsGroup: CouponsGroup) {
        val result = couponsGroup.getGain()
        val newBankroll = currentBankroll + result
        currentBankroll = if(newBankroll < 0) 0.0 else newBankroll
        bankrollStates.add(currentBankroll)
    }

    fun modifySingleCouponStackProportional(group: CouponsGroup?, totalStack : Double) {
        if (group == null)
            return
        val totalProbs = group.coupons.sumByDouble { it.getProb() }
        group.coupons.forEach {
            it.contribution = ((it.getProb()) / totalProbs) * totalStack
        }
    }

    fun wasPreviousBetWon() : Boolean {
        return if(bankrollStates.size < 2)
            true
        else {
            val difference = bankrollStates[bankrollStates.size - 1] - bankrollStates[bankrollStates.size - 2]
            difference > 0
        }
    }

}