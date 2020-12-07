package ga

import ga.entity.Coupon
import ga.entity.CouponsGroup

interface PopulationSelector  {
    fun select(population : List<CouponsGroup>) : List<CouponsGroup>
}