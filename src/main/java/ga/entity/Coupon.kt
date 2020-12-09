package ga.entity

import data.entity.bets.SingleBet

data class Coupon(
        val bets: MutableList<SingleBet> = mutableListOf(),
        var contribution : Double = 1.0
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

    fun getOdd() : Double {
        var odd = 1.0
        bets.forEach {
            odd *= it.odd
        }
        return odd
    }

    fun getWinCash () : Double {
        val o = getOdd()
        val c= contribution
        return getOdd() * contribution
    }

    fun areBetsTheSame(other : Coupon)  : Boolean {
        if(this.bets.size != other.bets.size)
            return false
        this.bets.sortBy { it.id }
        other.bets.sortBy { it.id }
        val ziped = this.bets.zip(other.bets)
        ziped.forEach {
            if(it.first != it.second)
                return false
        }
        return true
    }
}

