package utils

import ga.entity.CouponsGroup

class PercentageStack(startBankroll : Double, private val bankrollPercentage : Double) : StackStrategy(startBankroll) {

    override fun modifyContribution(couponsGroup: CouponsGroup, updateBankroll: Boolean): Boolean {
        if(currentBankroll <= 0 ){
            modifySingleCouponStackProportional(couponsGroup, 0.0)
            return false
        }
        var stackToDivide = currentBankroll * bankrollPercentage * 0.01
        // minimum 2 pln na zakład
        if(stackToDivide < 2)
            currentBankroll = 0.0
        stackToDivide = if(currentBankroll < stackToDivide) currentBankroll else stackToDivide
        if(updateBankroll)
            currentBankroll -= stackToDivide
        modifySingleCouponStackProportional(couponsGroup, stackToDivide)
        return true
    }


}