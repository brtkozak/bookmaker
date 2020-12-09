package ga

import ga.entity.Coupon
import ga.entity.CouponsGroup
import kotlin.random.Random

abstract class PopulationCrosser {

    protected abstract fun cross(parents: Pair<CouponsGroup, CouponsGroup>): Pair<CouponsGroup, CouponsGroup>

    protected abstract fun fixPopulation(population: List<CouponsGroup>)

    fun crossPopulation(population: List<CouponsGroup>): List<CouponsGroup> {
        val newPopulation = mutableListOf<CouponsGroup>()
        val oldPopulation = population.toMutableList()
        while (newPopulation.size < population.size) {
            val firstParent = oldPopulation[0]
            val shouldCross = Random.nextDouble(0.0, 1.0)
            if (shouldCross < Main.CROSSING_PROBABLITY && Main.POPULATION_SIZE - Main.ELITE_COUNT - newPopulation.size != 1 ) {
                val secondParent = oldPopulation[1]
                val children = cross(Pair(firstParent, secondParent))
                newPopulation.addAll(listOf(children.first, children.second))
                oldPopulation.remove(secondParent)
            } else {
                newPopulation.add(firstParent.copy())
            }
            oldPopulation.remove(firstParent)
        }
        fixPopulation(newPopulation)
        return newPopulation
    }

    fun onePointCrosser(parents: Pair<CouponsGroup, CouponsGroup>): Pair<CouponsGroup, CouponsGroup> {
        if (parents.first.coupons.size > 1 && parents.second.coupons.size > 1) {

            val firstParentSplitPoint = Random.nextInt(parents.first.coupons.size - 1)
            val secondParentSplitPoint = Random.nextInt(parents.second.coupons.size - 1)

            val firstParentCoupons = parents.first.coupons.toMutableList()
            val secondParentCoupons = parents.second.coupons.toMutableList()

            val firstChild = CouponsGroup()
            firstChild.coupons.addAll(firstParentCoupons.take(firstParentSplitPoint))
            firstChild.coupons.addAll(secondParentCoupons.takeLast(secondParentCoupons.size - secondParentSplitPoint))

            val secondChild = CouponsGroup()
            secondChild.coupons.addAll(firstParentCoupons.takeLast(firstParentCoupons.size - firstParentSplitPoint))
            secondChild.coupons.addAll(secondParentCoupons.take(secondParentSplitPoint))

            return Pair(firstChild, secondChild)

        } else return Pair(parents.first, parents.second)
    }
}