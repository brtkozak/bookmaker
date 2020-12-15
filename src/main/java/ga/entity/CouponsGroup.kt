package ga.entity

data class CouponsGroup(
        var coupons: MutableList<Coupon> = mutableListOf(),
        var rate: Double = 0.0
) {
    fun copy() : CouponsGroup {
        val copy = CouponsGroup()
        this.coupons.forEach {
            copy.coupons.add(it.copy())
        }
        copy.rate = this.rate
        return copy
    }

    fun getContribution() : Double {
        return coupons.sumByDouble { it.contribution }
    }
}