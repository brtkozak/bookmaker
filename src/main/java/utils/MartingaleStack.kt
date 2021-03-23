package utils

import ga.entity.CouponsGroup
import java.util.*

class MartingaleStack(startBankroll: Double, private val baseStack: Double) : StackStrategy(startBankroll) {

    var twoPower = 1

    override fun modifyContribution(couponsGroup: CouponsGroup, updateBankroll: Boolean): Boolean {
        if (currentBankroll <= 0) {
            modifySingleCouponStackProportional(couponsGroup, 0.0)
            return false
        }
        if(updateBankroll) {
            if (wasPreviousBetWon())
                twoPower = 1
            else
                twoPower++
        }
        var stackToDivide = baseStack * twoPower
        stackToDivide = if (currentBankroll < stackToDivide) currentBankroll else stackToDivide
        modifySingleCouponStackProportional(couponsGroup, stackToDivide)

        return true
    }
}