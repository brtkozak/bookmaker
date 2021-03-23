package utils

import ga.entity.CouponsGroup

class FixedStack(startBankroll: Double, private  val fixedStack : Double) : StackStrategy(startBankroll) {

    override fun modifyContribution(couponsGroup: CouponsGroup, updateBankroll: Boolean): Boolean {
        if(currentBankroll <= 0 ){
            modifySingleCouponStackProportional(couponsGroup, 0.0)
            return false
        }
        val stackToDivide = if(currentBankroll < fixedStack) currentBankroll else fixedStack
        modifySingleCouponStackProportional(couponsGroup, stackToDivide)
        return true
    }

}