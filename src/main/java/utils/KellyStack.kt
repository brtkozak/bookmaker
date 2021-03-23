package utils

import ga.entity.CouponsGroup

class KellyStack(startBankroll: Double) : StackStrategy(startBankroll){

    override fun modifyContribution(couponsGroup: CouponsGroup, updateBankroll: Boolean): Boolean {
        if(currentBankroll <= 0 ){
            modifySingleCouponStackProportional(couponsGroup, 0.0)
            return false
        }
        val totalProb = couponsGroup.getTotalProb()
        val totalOdd = couponsGroup.getTotalOdd()
        var stackToDivide = currentBankroll * ((totalProb * totalOdd - 1) / (totalOdd - 1))
        stackToDivide = if(currentBankroll < stackToDivide) currentBankroll else stackToDivide
        modifySingleCouponStackProportional(couponsGroup, stackToDivide)
        return true
    }
}