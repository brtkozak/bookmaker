package ga.entity

import data.entity.bets.SingleBet

data class Coupon(
        val bets: MutableList<SingleBet> = mutableListOf()
) {

    fun getProb() : Double {
        var prob = 1.0
        bets.forEach {
            prob *= it.myProb
        }
        return prob
    }

    fun copy() : Coupon {
        val copy =  Coupon()
        this.bets.forEach {
            copy.bets.add(it.copy())
        }
        return copy
    }
}

