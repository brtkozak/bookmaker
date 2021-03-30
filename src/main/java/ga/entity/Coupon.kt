package ga.entity

import data.entity.bets.BetResult
import data.entity.bets.SingleBet

data class Coupon(
        val bets: MutableList<SingleBet> = mutableListOf(),
        var contribution : Double = 100.0
) {

    fun getProb() : Double {
        var prob = 1.0
        bets.forEach {
            prob *= it.myProb
        }
        return prob
    }

    fun getBookProb() : Double {
        var prob = 1.0
        bets.forEach {
            prob *= it.bookProb
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

    fun getOddAfterResults() : Double {
        var odd = 1.0
        bets.forEach {
            if(it.betResult != BetResult.Unknown)
                odd *= it.odd
        }
        return odd
    }

    fun getWinCash () : Double {
//        return (contribution * 0.88) * getOdd()
        return getOdd() * (contribution)
    }

    fun getWinCashAfterResults () : Double {
//        return (contribution * 0.88) * getOddAfterResults()
        return getOddAfterResults() * (contribution)
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

    fun doesSupport(bet : SingleBet) : Boolean {
        this.bets.forEach {
            if(it.name1 ==  bet.name1 || it.name1 == bet.name2 || it.name2 == bet.name1 || it.name2 == bet.name2) {
                return true
            }
        }
        return false
    }
}

