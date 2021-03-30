package utils

import ga.entity.CouponsGroup

class DeLambertStack(startBankRoll: Double, private val baseStack : Double) : StackStrategy(startBankRoll) {

    var units = 10

    override fun modifyContribution(couponsGroup: CouponsGroup, updateBankroll: Boolean): Boolean {
        if (currentBankroll <= 0) {
            modifySingleCouponStackProportional(couponsGroup, 0.0)
            return false
        }
        if(updateBankroll) {
            if (wasPreviousBetWon() && units > 10)
                units --
            else
                units ++
        }
        var stackToDivide = baseStack * units
        stackToDivide = if (currentBankroll < stackToDivide) currentBankroll else stackToDivide
        modifySingleCouponStackProportional(couponsGroup, stackToDivide)

        return true
    }

}