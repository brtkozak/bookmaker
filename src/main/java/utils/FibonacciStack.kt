package utils

import ga.entity.CouponsGroup

class FibonacciStack(startBankroll: Double, private val baseStack: Double) : StackStrategy(startBankroll) {

    var currentFibonacciWord = 1

    override fun modifyContribution(couponsGroup: CouponsGroup, updateBankroll: Boolean): Boolean {
        if(currentBankroll <= 0 ){
            modifySingleCouponStackProportional(couponsGroup, 0.0)
            return false
        }
        if(wasPreviousBetWon() && currentFibonacciWord > 1)
            currentFibonacciWord --
        else
            currentFibonacciWord ++
        var stackToDivide = baseStack * getFibonacciWord(currentFibonacciWord)
        stackToDivide = if (currentBankroll < stackToDivide) currentBankroll else stackToDivide
        if (updateBankroll)
            currentBankroll -= stackToDivide
        modifySingleCouponStackProportional(couponsGroup, stackToDivide)
        return true
    }

    private fun getFibonacciWord(n : Int) : Int {
        var a = 0
        var b = 1
        var current = 0
        for(i in 0 until n) {
            current = a + b
            a = b
            b = current
        }
        return current
    }
}